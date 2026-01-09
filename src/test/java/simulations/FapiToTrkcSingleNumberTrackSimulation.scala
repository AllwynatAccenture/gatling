package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random
import scala.io.Source
import scala.concurrent.duration._

class FapiToTrkcSingleNumberTrackSimulation extends Simulation {

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

  // Randomized feeder (switch to csv(...).circular for sequential)
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
        .post("https://apis-drt.idev.fedex.com/oauth/token")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .formParam("grant_type", "client_credentials")
        .formParam("client_id", clientId)
        .formParam("client_secret", clientSecret)
        .check(status.is(200))
        .check(jsonPath("$.access_token").saveAs("token"))
        .check(jsonPath("$.expires_in").optional.saveAs("expiresIn")) // seconds
        .silent
    ).exec { s =>
      val nowMs  = System.currentTimeMillis()
      val ttlSec = s("expiresIn").asOption[String].flatMap(x => util.Try(x.toLong).toOption).getOrElse(3600L)
      val skew   = 60L
      val expiry = nowMs + math.max(10L, (ttlSec - skew)) * 1000L
      val token  = s("token").as[String].trim
      s.set("token", token).set("tokenExpiryMillis", expiry)
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
        http("SINGLE NUMBERS FROM FAPI TO TRKC EXISTING SERVICE")
          .post("/track/v1/trackingnumbers")
          .header("Authorization", (session: Session) => "Bearer " + session("token").as[String])
          .body(StringBody(s => buildBody(s("trackingNumber").as[String]))).asJson
          .check(status.in(200, 201, 202))
      )

  // ===== Scenario: token once per VU, then loop =====
  private val scn = scenario("Existing FAPI → TRKC Single Number Track")
    .exec(acquireToken)
    .forever { exec(callTracking).pause(200.millis) }

  // ===== Injection: 20 concurrent users for ~30 minutes =====
  setUp(
    scn.inject(
      rampConcurrentUsers(0) to 20 during (30.seconds),
      constantConcurrentUsers(20) during (29.minutes + 30.seconds)
    )
  ).protocols(httpProtocol)
    .maxDuration(30.minutes)
}
