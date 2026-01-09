package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.io.Source
import scala.util.Random

class FapiWithFlagToTrackAndResolveMultiNumberSimulation extends Simulation {

  // ===== Config via -D props =====
  private val clientId     = sys.props.getOrElse("client_id", "l7bfeb9beb9fa8441a9f59473281a27cd1")
  private val clientSecret = sys.props.getOrElse("client_secret", "f08c9e9b4e9d4b638725583a3d1eb2ac")

  // ===== HTTP protocol (target) =====
  private val httpProtocol = http
    .baseUrl("https://svma48101.ute.iaas.fedex.com:8443")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
    .disableWarmUp
    .disableCaching

  // ===== Helpers =====
  private def loadTrackingNumbers(resourcePath: String): Vector[String] = {
    val is = Option(getClass.getClassLoader.getResourceAsStream(resourcePath))
      .getOrElse(throw new IllegalArgumentException(s"Missing resource: $resourcePath"))
    val text = try Source.fromInputStream(is, "UTF-8").getLines().mkString("\n") finally is.close()
    text
      .split("""[,\r\n]+""")
      .toVector
      .map(_.trim.stripPrefix("\"").stripSuffix("\""))
      .filter(_.matches("""\d{8,12}"""))
      .ensuring(_.nonEmpty, s"No valid numbers in $resourcePath")
  }

  private val fxfTrackingNumbers = loadTrackingNumbers("tackingnumbers325.csv")
  private val fxcTrackingNumbers = loadTrackingNumbers("tackingnumbersFXC.csv")

  private def pickRandomN[A](xs: Vector[A], n: Int): Vector[A] =
    if (n <= 0) Vector.empty else Random.shuffle(xs).take(math.min(n, xs.size))

  private def buildBody(numbers: Seq[String]): String = {
    val items = numbers.map(n => s"""{ "trackingNumberInfo": { "trackingNumber": "$n" } }""").mkString(",")
    s"""{"trackingInfo":[${items}],"includeDetailedScans":false}"""
  }

  // ===== Feeder: 10 numbers total (5 FXF + 5 FXC), shuffled =====
  private val multiFeeder = Iterator.continually {
    val fxf5 = pickRandomN(fxfTrackingNumbers, 5)
    val fxc5 = pickRandomN(fxcTrackingNumbers, 5)
    val all10 = Random.shuffle(fxf5 ++ fxc5)
    Map("numbers" -> all10)
  }

  // ===== OAuth token acquisition (client_credentials) with expiry tracking =====
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
    )
      .exec { s =>
        val nowMs  = System.currentTimeMillis()
        val ttlSec = s("expiresIn").asOption[String].flatMap(x => util.Try(x.toLong).toOption).getOrElse(3600L)
        val skew   = 60L
        val expiry = nowMs + math.max(10L, (ttlSec - skew)) * 1000L
        val token  = s("token").as[String].trim
        s.set("token", token).set("tokenExpiryMillis", expiry)
      }

  private val ensureValidToken =
    exec { s =>
      val now = System.currentTimeMillis()
      val exp = s("tokenExpiryMillis").asOption[Long].getOrElse(0L)
      if (now >= exp) s.set("needsRefresh", true) else s.set("needsRefresh", false)
    }.doIf(session => session("needsRefresh").as[Boolean]) {
      exec(acquireToken)
    }

  // ===== One request using 10 numbers =====
  private val callTrackingMulti =
    feed(multiFeeder)
      .exec(ensureValidToken)
      .exec { s =>
        val nums = s("numbers").as[Vector[String]]
        s.set("requestBody", buildBody(nums))
      }
      .exec(
        http("TRACK_MULTI_NUMBERS")
          //.post("/fxf/v1/multitrkc/poc")
          .post("/poc/api/freight/checktracking")
          .header("Authorization", (session: Session) => "Bearer " + session("token").as[String])
          .body(StringBody(session => session("requestBody").as[String])).asJson
          .check(status.in(200, 201))
      )

  // ===== Scenario: token once per VU, then loop forever =====
  private val scn = scenario("FAPI (True Flag) → FXFT Resolver Multi Number Track")
    .exec(acquireToken)
    .forever { exec(callTrackingMulti).pause(200.millis) }

  // ===== Injection: 20 concurrent users for ~30 minutes =====
  setUp(
    scn.inject(
      rampConcurrentUsers(0) to 20 during (30.seconds),
      constantConcurrentUsers(20) during (29.minutes + 30.seconds)
    )
  ).protocols(httpProtocol)
    .maxDuration(30.minutes)
}
