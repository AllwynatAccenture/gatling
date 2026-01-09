package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.io.Source
import scala.util.Random

class MulePingSimulation extends Simulation {

  // ===== Config via -D props =====
  private val clientId     = sys.props.getOrElse("client_id", "0oa2iulmmydCxzERr0h8")
  private val clientSecret = sys.props.getOrElse("client_secret", "YSpZIyHryKlIQlPWJnV1ZIVYgBp1KXfisZJs9Hhg3_dHzLWk0MMZrMs_mKklZ2Ij")

  // ===== HTTP protocol (target) =====
  private val httpProtocol = http
    .baseUrl("https://test-gw-cwul4s.e6nxgs.usa-e2.cloudhub.io/fxfgw-track1/fxfgw")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
    .disableWarmUp
    .disableCaching

  // ===== Helpers =====
  private def loadTrackingNumbers(resourcePath: String): Vector[String] = {
    val is = Option(getClass.getClassLoader.getResourceAsStream(resourcePath))
      .getOrElse(throw new IllegalArgumentException(s"Missing resource: $resourcePath"))
    val all = try Source.fromInputStream(is, "UTF-8").getLines().mkString("\n") finally is.close()
    all.split("""[,\r\n]+""").toVector
      .map(_.trim.stripPrefix("\"").stripSuffix("\""))
      .filter(_.matches("""\d{8,12}"""))
      .ensuring(_.nonEmpty, s"No valid tracking numbers in $resourcePath")
  }

  private val trackingNumbers = loadTrackingNumbers("tackingnumbers325.csv")

  private val feeder = Iterator.continually(
    Map("trackingNumber" -> trackingNumbers(Random.nextInt(trackingNumbers.size)))
  )

  private def buildBody(trk: String): String =
    s"""{
       |  "trackingInfo": [
       |    { "trackingNumberInfo": { "trackingNumber": "$trk" } }
       |  ],
       |  "includeDetailedScans": false
       |}""".stripMargin

  // ===== OAuth token acquisition (client_credentials) =====
  private val acquireToken =
    exec(
      http("oauth_token")
        .post("https://purpleid-test.oktapreview.com/oauth2/aus2iumxuy7tFiXTW0h8/v1/token")
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
  private val callTracking =
    feed(feeder)
      .exec(ensureValidToken)
      .exec(
        http("FXF SINGLE NUMBERS ROUTED FROM FAPI TO FXFT")
          .post("/route/track/v1/trackingnumbers")
          .header("Authorization", (session: Session) => "Bearer " + session("token").as[String])
          .body(StringBody(s => buildBody(s("trackingNumber").as[String]))).asJson
          .check(status.in(200, 201, 202))
          .check(bodyString.saveAs("responseBody"))
      )

  // ====== Scenario: get token once per VU, then make requests ======
  private val scn = scenario("MULE → FXFT for FXF Single Number")
    .exec(acquireToken)
    .pause(200.millis)
    .forever {
      exec(callTracking).pause(200.millis)
    }

  // ====== Injection profile: ramp to 20 users over 30s, then hold for 30m ======
//  setUp(
//    scn.inject(
//      rampConcurrentUsers(0) to 20 during (30.seconds),
//      constantConcurrentUsers(20) during (29.minutes + 30.seconds)
//    )
//  ).protocols(httpProtocol)
//    .maxDuration(30.minutes)
  setUp(
    scn.inject(
      rampConcurrentUsers(0) to 5 during (30.seconds),
      constantConcurrentUsers(10) during (20.minutes + 30.seconds)
    )
  ).protocols(httpProtocol)
    .maxDuration(20.minutes)
}
