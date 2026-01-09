package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.util.Random
import scala.io.Source

/**
 * This will be used only for baseline comparison between TRKC and Track and Resolve.
 */
class FapiToTrkcMultiNumberSimulation extends Simulation {
  private val clientId     = sys.props.getOrElse("client_id", "l7bfeb9beb9fa8441a9f59473281a27cd1")
  private val clientSecret = sys.props.getOrElse("client_secret", "f08c9e9b4e9d4b638725583a3d1eb2ac")

  private val httpProtocol = http
    .baseUrl("https://svma48101.ute.iaas.fedex.com") //use https://svma48101.ute.iaas.fedex.com, "https://apis-drt.idev.fedex.com
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
    // helpful if the target uses self-signed/internal certs
    .disableWarmUp
    .disableCaching

  // ====== Helpers ======
  private def loadTrackingNumbers(resourcePath: String): Vector[String] = {
    val is = Option(getClass.getClassLoader.getResourceAsStream(resourcePath))
      .getOrElse(throw new IllegalArgumentException(s"Missing resource: $resourcePath"))
    val text = try Source.fromInputStream(is, "UTF-8").getLines().mkString("\n") finally is.close()

    text
      .split("""[,\r\n]+""")                    // supports comma-separated or one-per-line
      .toVector
      .map(_.trim.stripPrefix("\"").stripSuffix("\""))
      .filter(_.matches("""\d{8,12}"""))        // keep only 8–12 digit numbers
      .ensuring(_.nonEmpty, s"No valid numbers in $resourcePath")
  }

  private val fxfTrackingNumbers = loadTrackingNumbers("tackingnumbers325.csv")
  private val fxcTrackingNumbers = loadTrackingNumbers("tackingnumbersFXC.csv")

  private def pickRandomN[A](xs: Vector[A], n: Int): Vector[A] =
    if (n <= 0) Vector.empty
    else Random.shuffle(xs).take(math.min(n, xs.size))

  private def buildBody(numbers: Seq[String]): String = {
    val items = numbers.map { n =>
      s"""{ "trackingNumberInfo": { "trackingNumber": "$n" } }"""
    }.mkString(",\n    ")

    s"""{
       |  "trackingInfo": [
       |    $items
       |  ],
       |  "includeDetailedScans": false
       |}""".stripMargin
  }

  // ====== Feeder: 10 numbers total (5 FXF + 5 FXC), shuffled ======
  private val multiFeeder = Iterator.continually {
    val fxf5 = pickRandomN(fxfTrackingNumbers, 5)
    val fxc5 = pickRandomN(fxcTrackingNumbers, 5)
    val all10 = Random.shuffle(fxf5 ++ fxc5)
    Map("numbers" -> all10)
  }

  // ====== OAuth token acquisition ======
  private val acquireToken =
    exec(
      http("oauth_token")
        .post("https://svma48101.ute.iaas.fedex.com/oauth/token")
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
        val t = s("token").as[String].trim
        //println(s"[DEBUG] token(len=${t.length}): $t")
        s.set("token", t)
      }

  // ====== One request using 10 numbers ======
  private val callTrackingMulti =
    feed(multiFeeder)
      .exec { s =>
        val nums = s("numbers").as[Vector[String]]
        val body = buildBody(nums)
        //println(s"[DEBUG] Using ${nums.size} numbers: ${nums.mkString(",")}")
        s.set("requestBody", body)
      }
      .exec(
        http("TRACK_MULTI_NUMBERS_FROM_TRKC")
          .post("/track/v1/trackingnumbers")
          // Session function — avoids EL issues
          .header("Authorization", (session: Session) => "Bearer " + session("token").as[String])
          .body(StringBody(session => session("requestBody").as[String])).asJson
          .check(status.in(200, 201))
          .check(bodyString.saveAs("responseBody"))
      )
      .exec { s =>
        //println(s"[DEBUG] Response:\n${s("responseBody").asOption[String].getOrElse("<empty>")}")
        s
      }

  // ====== Scenario: get token once, make one multi-number call, stop ======
  // ===== Scenario: token once per VU, then loop forever =====
  private val scn = scenario("EXISTING FAPI → TRKC Multi Number Track")
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
