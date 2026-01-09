package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class FapiRoutingFxcAndFxfMixedSingleNumbers extends Simulation {

  // ===== Config via -D props =====
  private val clientId     = sys.props.getOrElse("client_id", "l7bfeb9beb9fa8441a9f59473281a27cd1")
  private val clientSecret = sys.props.getOrElse("client_secret", "f08c9e9b4e9d4b638725583a3d1eb2ac")

  // ===== HTTP protocol (target) =====
  private val httpProtocol = http
    .baseUrl("https://apis-drt.idev.fedex.com")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
    .disableWarmUp
    .disableCaching

  private val feeder = csv("tackingnumbersFXC_FXF.csv").circular

  // ===== OAuth token acquisition (client_credentials) =====
  private val acquireToken =
    exec(
      http("oauth_token")
        .post("https://apis-drt.idev.fedex.com/oauth/token")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .formParam("grant_type", "client_credentials")
        .formParam("client_id", clientId)
        .formParam("client_secret", clientSecret)
        .check(status.is(200))
        .check(jsonPath("$.access_token").saveAs("token"))
        .check(jsonPath("$.expires_in").optional.saveAs("expiresIn"))
        .silent
    ).exec { s =>
      val nowMs = System.currentTimeMillis()
      val ttlSec = s("expiresIn").asOption[String].flatMap(x => util.Try(x.toLong).toOption).getOrElse(3600L)
      val skewSec = 60L
      val expiryMs = nowMs + math.max(10L, (ttlSec - skewSec)) * 1000L
      val raw = s("token").as[String]
      val token = raw.trim
      s.set("token", token).set("tokenExpiryMillis", expiryMs)
    }

  // ===== Ensure token valid; refresh if expired/near-expired =====
  private val ensureValidToken =
    exec { s =>
      val now = System.currentTimeMillis()
      val exp = s("tokenExpiryMillis").asOption[Long].getOrElse(0L)
      if (now >= exp) s.set("needsRefresh", true) else s.set("needsRefresh", false)
    }.doIf(session => session("needsRefresh").as[Boolean]) {
      exec(acquireToken)
    }

  // ===== API call using the token =====
  private val callTracking =
    feed(feeder)
      .exec(ensureValidToken)
      .exec(
        http("track_request")
          .post("/poc/api/freight/checktracking")
          .header("Authorization", (session: Session) => "Bearer " + session("token").as[String])
          .body(StringBody(
            """{
                "trackingInfo": [ { "trackingNumberInfo": { "trackingNumber": "${trackingNumber}" } } ],
                "includeDetailedScans": false
              }"""
          )).asJson
          .check(status.in(200, 201, 202))
      )

  // ===== Scenario: get token once per VU, then make requests =====
  private val scn = scenario("FAPI (with Routing logic) → FXFT and TRKC Mixed Single Number Track")
    .exec(acquireToken)
    .pause(500.millis)
    .during(30.minutes) {
      exec(callTracking).pause(200.millis)
    }

  // ===== Injection profile: ramp to 20 users over 30s, then hold for 30m =====
  setUp(
    scn.inject(
      rampUsers(20) during (30.seconds)
    )
  ).protocols(httpProtocol)
    .maxDuration(30.minutes + 1.minute)
}