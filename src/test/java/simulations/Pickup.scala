package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.util.Random

class Pickup extends Simulation {

  // ===== Config via -D props =====
  private val clientId     = sys.props.getOrElse("client_id", "0oatnayhm1u8kaBLZ1d7")
  private val clientSecret = sys.props.getOrElse("client_secret", "PQLjSfLy2TYdY72TpHt81NLz24nfR5Q9meAg2Ux1k30mnkZDQQlyZRjFvEzNiCOt")

  // ===== HTTP protocol (target) =====
  private val httpProtocol = http
    .baseUrl("https://test-gw-cwul4s.e6nxgs.usa-e2.cloudhub.io/fxf-external-pickup/fxfgw/pickup")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
    .disableWarmUp
    .disableCaching

  // ===== Feeder for random data =====
  private val feeder = Iterator.continually {
    val pickupLoc = LocationData.randomLocation()
    val destinationLoc = LocationData.randomLocation()

    Map(
      "pickupCity" -> pickupLoc.city,
      "pickupState" -> pickupLoc.stateOrProvinceCode,
      "pickupPostalCode" -> pickupLoc.postalCode,
      "destinationPostalCode" -> destinationLoc.postalCode
    )
  }

  private def buildPickupBody(session: Session): String = {
    val pickupCity = session("pickupCity").as[String]
    val pickupState = session("pickupState").as[String]
    val pickupPostalCode = session("pickupPostalCode").as[String]
    val destinationPostalCode = session("destinationPostalCode").as[String]

    s"""{
       |    "associatedAccountNumber": {
       |        "value": "605188087"
       |    },
       |    "originDetail": {
       |        "pickupLocation": {
       |            "contact": {
       |                "personName": "Tester",
       |                "phoneNumber": "8634997777",
       |                "companyName": "0059 SONY CHERRY CREEK CO"
       |            },
       |            "address": {
       |                "streetLines": [
       |                    "3000 E 1ST AVE"
       |                ],
       |                "city": "DENVER",
       |                "stateOrProvinceCode": "CO",
       |                "postalCode": "80206",
       |                "countryCode": "US",
       |                "residential": false
       |            }
       |        },
       |        "readyDateTimestamp": "2026-01-21T08:00:00Z",
       |        "customerCloseTime": "17:00:00"
       |    },
       |    "trackingNumber": "884428858830",
       |    "freightPickupDetail": {
       |        "lineItems": [
       |            {
       |                "service": "FEDEX_FREIGHT_PRIORITY",
       |                "sequenceNumber": 1,
       |                "destination": {
       |                    "streetLines": [
       |                        "183 LONE HORSEMAN RD"
       |                    ],
       |                    "city": "$pickupCity",
       |                    "stateOrProvinceCode": "$pickupState",
       |                    "postalCode": "$pickupPostalCode",
       |                    "countryCode": "US",
       |                    "residential": false
       |                },
       |                "packaging": "PALLET",
       |                "pieces": 1,
       |                "weight": {
       |                    "units": "LB",
       |                    "value": 265
       |                },
       |                "totalHandlingUnits": 2,
       |                "shipmentSpecialServicesRequested": {
       |                    "specialServiceTypes": [
       |                        "LIFTGATE_DELIVERY",
       |                        "PRE_DELIVERY_NOTIFICATION",
       |                        "DO_NOT_STACK_PALLETS"
       |                    ],
       |                    "freightDirectDetail": null
       |                },
       |                "description": "DIY MULTI 18  DIY25  1   DIY 06 AH "
       |            }
       |        ],
       |        "role": "SHIPPER",
       |        "payment": "SENDER",
       |        "submittedBy": {
       |            "companyName": "0059 SONY CHERRY CREEK CO",
       |            "personName": "JOSE PINEDA",
       |            "phoneNumber": "8636603048",
       |            "emailAddress": "test@fedex.com"
       |        }
       |    },
       |    "remarks": "Any Additional Services Must Be Approved by Shipper. DO NOT DOUBLE STACK. If consignee is not responsive please contact dispositions@hvac-distributing.com DO NOT RE-STICKER."
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
  private val callPickup =
    feed(feeder)
      .exec(ensureValidToken)
      .exec(
        http("FXF Pickup Create Request")
          .post("/fapi/create")
          .header("Authorization", (session: Session) => "Bearer " + session("token").as[String])
          .body(StringBody(session => buildPickupBody(session))).asJson
          .check(status.in(200, 201, 202))
          .check(bodyString.saveAs("responseBody"))
      )

  // ====== Scenario: get token once per VU, then make requests ======
  private val scn = scenario("MULE → Pickup API")
    .exec(acquireToken)
    .pause(200.millis)
    .forever {
      exec(callPickup).pause(200.millis)
    }

  // ====== Injection profile: ramp to 5 users over 30s, then hold for 20m ======
  setUp(
    scn.inject(
      rampConcurrentUsers(0) to 2 during (10.seconds),
      constantConcurrentUsers(2) during (1.minute + 50.seconds)
    )
  ).protocols(httpProtocol)
    .maxDuration(2.minutes)
}
