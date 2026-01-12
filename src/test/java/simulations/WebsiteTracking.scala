package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class WebsiteTracking extends Simulation {

  // ===== Config via -D props =====
  private val clientId     = sys.props.getOrElse("client_id", "0oatnayhm1u8kaBLZ1d7")
  private val clientSecret = sys.props.getOrElse("client_secret", "PQLjSfLy2TYdY72TpHt81NLz24nfR5Q9meAg2Ux1k30mnkZDQQlyZRjFvEzNiCOt")

  // ===== HTTP protocol (target) =====
  private val httpProtocol = http
    .baseUrl("https://test-gw-cwul4s.e6nxgs.usa-e2.cloudhub.io/fxfgw-track1/fxfgw/route")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
    .disableWarmUp
    .disableCaching

  // ===== Feeder for random tracking numbers =====
  private val feeder = Iterator.continually {
    Map("trackingNumber" -> TrackingData.randomTrackingNumber())
  }

  private def buildWebsiteTrackingBody(session: Session): String = {
    val trackingNumber = session("trackingNumber").as[String]

    s"""{
       |  "appDeviceType": "WTRK",
       |  "appType": "WTRK",
       |  "supportHTML": true,
       |  "supportCurrentLocation": true,
       |  "trackingInfo": [
       |    {
       |      "trackNumberInfo": {
       |        "trackingCarrier": "",
       |        "trackingNumber": "$trackingNumber",
       |        "trackingQualifier": ""
       |      }
       |    }
       |  ],
       |  "uniqueKey": "",
       |  "guestAuthenticationToken": ""
       |}""".stripMargin
  }

  // ===== OAuth token acquisition (client_credentials) =====
  private val acquireToken =
    exec(
      http("oauth_token")
        .post("https://fxfreight-test.oktapreview.com/oauth2/austnaxl5xDx7SWyh1d7/v1/token")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .formParam("grant_type", "client_credentials")
        .formParam("client_id", clientId)
        .formParam("client_secret", clientSecret)
        .formParam("scope", "Custom_Scope")
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
  private val callWebsiteTracking =
    feed(feeder)
      .exec(ensureValidToken)
      .exec(
        http("Website Track V2 Shipments Request")
          .post("/track/v2/shipments")
          .header("Authorization", (session: Session) => "Bearer " + session("token").as[String])
          .header("x-loggedin", "LOGGEDIN")
          .body(StringBody(session => buildWebsiteTrackingBody(session))).asJson
          .check(status.in(200, 201, 202))
          .check(bodyString.saveAs("responseBody"))
      )

  // ====== Scenario: get token once per VU, then make requests ======
  private val scn = scenario("Website Tracking → V2 Shipments API")
    .exec(acquireToken)
    .pause(200.millis)
    .forever {
      exec(callWebsiteTracking).pause(200.millis)
    }

  // ====== Injection profile: ramp to 5 users over 30s, then hold for 20m ======
/*  setUp(
    scn.inject(
      rampConcurrentUsers(0) to 5 during (30.seconds),
      constantConcurrentUsers(10) during (20.minutes + 30.seconds)
    )
  ).protocols(httpProtocol)
    .maxDuration(20.minutes)*/

  setUp(
    scn.inject(
      rampConcurrentUsers(0) to 2 during (10.seconds),
      constantConcurrentUsers(2) during (1.minute + 50.seconds)
    )
  ).protocols(httpProtocol)
    .maxDuration(2.minutes)
}
