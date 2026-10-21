package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class TrackingNumber extends Simulation {

  // ===== Config via -D props =====
  private val clientId = sys.props.getOrElse("client_id", "0oatnayhm1u8kaBLZ1d7")
  private val clientSecret = sys.props.getOrElse("client_secret", "PQLjSfLy2TYdY72TpHt81NLz24nfR5Q9meAg2Ux1k30mnkZDQQlyZRjFvEzNiCOt")

  // ===== HTTP protocol (target) =====
  private val httpProtocol = http
    .baseUrl("https://test-gw-cwul4s.e6nxgs.usa-e2.cloudhub.io/fxfgw-track1/fxfgw")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
    .disableWarmUp
    .disableCaching

  // ===== Feeder for random tracking numbers =====
  private val feeder = Iterator.continually(
    Map("trackingNumber" -> TrackingData.randomTrackingNumber())
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

  // ===== API call using the token =====
  private val callTracking = feed(feeder).exec(ensureValidToken).exec(http("FXF SINGLE NUMBERS ROUTED FROM FAPI TO FXFT").post("/route/track/v1/trackingnumbers").header("Authorization", (session: Session) => "Bearer " + session("token").as[String]).body(StringBody(s => buildBody(s("trackingNumber").as[String]))).asJson.check(status.in(200, 201, 202)).check(bodyString.saveAs("responseBody")))
  val stepDuration = 1.seconds
  val requestsPerStep = 1
// Dynamic pace calculation
val paceDuration = 1.seconds //FiniteDuration = (stepDuration.toSeconds / requestsPerStep).seconds
// ====== Scenario: get token once per VU, then make requests ======
// private val scn = scenario("MULE → FXFT for FXF Single Number")
// .exec(acquireToken) // .pause(200.millis)
// .forever {
// exec(callTracking).pause(200.millis).pace(paceDuration)
// }
// Calculate number of iterations dynamically
val totalDurationSeconds = 30 * 60 // total planned test time in seconds
val paceSeconds = paceDuration.toSeconds
  val iterations = (totalDurationSeconds / paceSeconds).toInt

  private val scn = scenario("MULE → FXFT for FXF Single Number")
    .exec(acquireToken)
    .pause(200.millis)
    .repeat(iterations) {
      exec(callTracking)
        .pace(paceDuration)
    }
// Configuration
val usersPerStep = 5
  val Duration = 2.minutes
  val steps = 10 // 4 * 5 = 20 users max
val soakDuration = 5.minutes
  val rampDownTime = 5.minutes
  val maxUsers = usersPerStep * steps
// ====== Hard Stop Setup ======//
val rampUpDuration = (maxUsers / steps) * Duration
// ====== Injection profile: ramp to 20 users over 30s, then hold for 30m ======
// setUp( // scn.inject( // rampConcurrentUsers(0) to 20 during (30.seconds),
// constantConcurrentUsers(20) during (29.minutes + 30.seconds) // )
// ).protocols(httpProtocol)
// .maxDuration(30.minutes)
setUp(
  scn.inject(
// Step load: add 4 users every 3 minutes
incrementConcurrentUsers(usersPerStep)
  .times(steps)
  .eachLevelLasting(Duration)
  .startingFrom(10),

// Soak phase: keep max users constant for 30 minutes
constantConcurrentUsers(maxUsers)
  .during(soakDuration),

//Ramp-down: slowly remove users
rampConcurrentUsers (maxUsers)
  .to(0)
  .during(rampDownTime)
  )
)
  .protocols(httpProtocol)
  .maxDuration(rampUpDuration + soakDuration + rampDownTime)
}