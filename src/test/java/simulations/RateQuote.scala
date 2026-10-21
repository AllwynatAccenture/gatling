package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.util.Random

class RateQuote extends Simulation {

  // ===== Config via -D props =====
  private val clientId     = sys.props.getOrElse("client_id", "0oatnayhm1u8kaBLZ1d7")
  private val clientSecret = sys.props.getOrElse("client_secret", "PQLjSfLy2TYdY72TpHt81NLz24nfR5Q9meAg2Ux1k30mnkZDQQlyZRjFvEzNiCOt")

  // ===== HTTP protocol (target) =====
  private val httpProtocol = http
    .baseUrl("https://test-gw-cwul4s.e6nxgs.usa-e2.cloudhub.io/fxf-external-rate/fxfgw/rate")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
    .disableWarmUp
    .disableCaching

  // ===== Feeder for random data =====
  private val feeder = Iterator.continually {
    val shipperLoc = LocationData.randomLocation()
    val recipientLoc = LocationData.randomLocation()
    val discount = Random.nextInt(71) // 0 to 70

    Map(
      "shipperCity" -> shipperLoc.city,
      "shipperState" -> shipperLoc.stateOrProvinceCode,
      "shipperPostalCode" -> shipperLoc.postalCode,
      "recipientCity" -> recipientLoc.city,
      "recipientState" -> recipientLoc.stateOrProvinceCode,
      "recipientPostalCode" -> recipientLoc.postalCode,
      "clientDiscountPercent" -> discount
    )
  }

  private def buildRateQuoteBody(session: Session): String = {
    val shipperCity = session("shipperCity").as[String]
    val shipperState = session("shipperState").as[String]
    val shipperPostalCode = session("shipperPostalCode").as[String]
    val recipientCity = session("recipientCity").as[String]
    val recipientState = session("recipientState").as[String]
    val recipientPostalCode = session("recipientPostalCode").as[String]
    val discount = session("clientDiscountPercent").as[Int]

    s"""{
       |  "rateRequestControlParameters": {
       |    "returnTransitTimes": true,
       |    "rateSortOrder": "COMMITASCENDING"
       |  },
       |  "freightRequestedShipment": {
       |    "shipper": {
       |      "address": {
       |        "streetLines": ["630 AXMINISTER DR"],
       |        "city": "$shipperCity",
       |        "stateOrProvinceCode": "$shipperState",
       |        "postalCode": "$shipperPostalCode",
       |        "countryCode": "US",
       |        "residential": false
       |      }
       |    },
       |    "recipient": {
       |      "address": {
       |        "streetLines": null,
       |        "city": "$recipientCity",
       |        "stateOrProvinceCode": "$recipientState",
       |        "postalCode": "$recipientPostalCode",
       |        "countryCode": "US",
       |        "residential": false
       |      }
       |    },
       |    "serviceType": "",
       |    "preferredCurrency": "USD",
       |    "shippingChargesPayment": {},
       |    "rateRequestType": [""],
       |    "shipDateStamp": "Dec-30-2025",
       |    "requestedPackageLineItems": [
       |      {
       |        "subPackagingType": "PALLET",
       |        "physicalPackaging": "PALLET",
       |        "groupPackageCount": 1,
       |        "contentRecord": [
       |          {
       |            "itemNumber": "1",
       |            "description": "2.3MM RIVING KNIFE T GLIDE FENCE ASSEMBLY INDUSTRIAL"
       |          }
       |        ],
       |        "weight": {
       |          "units": "LB",
       |          "value": 833.0
       |        },
       |        "dimensions": {
       |          "length": 40,
       |          "width": 40,
       |          "height": 40,
       |          "units": "IN"
       |        },
       |        "associatedFreightLineItems": [
       |          {
       |            "id": "1"
       |          }
       |        ]
       |      },
       |      {
       |        "subPackagingType": "PALLET",
       |        "physicalPackaging": "PALLET",
       |        "groupPackageCount": 1,
       |        "contentRecord": [
       |          {
       |            "itemNumber": "1",
       |            "description": "WOOD PALLET"
       |          }
       |        ],
       |        "weight": {
       |          "units": "LB",
       |          "value": 40.0
       |        },
       |        "dimensions": {
       |          "length": 31,
       |          "width": 42,
       |          "height": 30,
       |          "units": "IN"
       |        },
       |        "associatedFreightLineItems": [
       |          {
       |            "id": "2"
       |          }
       |        ]
       |      },
       |      {
       |        "subPackagingType": "PALLET",
       |        "physicalPackaging": "PALLET",
       |        "groupPackageCount": 1,
       |        "contentRecord": [
       |          {
       |            "itemNumber": "1",
       |            "description": "PROFESSIONAL CABINET SAW SINGLE PHASE 60HZ"
       |          }
       |        ],
       |        "weight": {
       |          "units": "LB",
       |          "value": 400.0
       |        },
       |        "dimensions": {
       |          "length": 70,
       |          "width": 4,
       |          "height": 3,
       |          "units": "IN"
       |        },
       |        "associatedFreightLineItems": [
       |          {
       |            "id": "3"
       |          }
       |        ]
       |      },
       |      {
       |        "subPackagingType": "PALLET",
       |        "physicalPackaging": "PALLET",
       |        "groupPackageCount": 1,
       |        "contentRecord": [
       |          {
       |            "itemNumber": "1",
       |            "description": "T GLIDE RAILS ASSEMBLY 36 INCH RAILS ASSEMBLY"
       |          }
       |        ],
       |        "weight": {
       |          "units": "LB",
       |          "value": 52.0
       |        },
       |        "dimensions": {
       |          "length": 70,
       |          "width": 4,
       |          "height": 3,
       |          "units": "IN"
       |        },
       |        "associatedFreightLineItems": [
       |          {
       |            "id": "4"
       |          }
       |        ]
       |      }
       |    ],
       |    "totalPackageCount": 4,
       |    "freightShipmentDetail": {
       |      "role": "SHIPPER",
       |      "lineItem": [
       |        {
       |          "handlingUnits": 1,
       |          "description": "2.3MM RIVING KNIFE T GLIDE FENCE ASSEMBLY INDUSTRIAL",
       |          "id": "1",
       |          "weight": {
       |            "units": "LB",
       |            "value": 833.0
       |          },
       |          "pieces": 1,
       |          "dimensions": {
       |            "length": 40,
       |            "width": 40,
       |            "height": 40,
       |            "units": "IN"
       |          },
       |          "freightClass": "CLASS_077_5",
       |          "subPackagingType": "PALLET",
       |          "volume": {
       |            "units": "CUBIC_FT",
       |            "value": 36.926037
       |          }
       |        },
       |        {
       |          "handlingUnits": 1,
       |          "description": "WOOD PALLET",
       |          "id": "2",
       |          "weight": {
       |            "units": "LB",
       |            "value": 40.0
       |          },
       |          "pieces": 1,
       |          "dimensions": {
       |            "length": 31,
       |            "width": 42,
       |            "height": 30,
       |            "units": "IN"
       |          },
       |          "freightClass": "CLASS_077_5",
       |          "subPackagingType": "PALLET",
       |          "volume": {
       |            "units": "CUBIC_FT",
       |            "value": 22.575
       |          }
       |        },
       |        {
       |          "handlingUnits": 1,
       |          "description": "PROFESSIONAL CABINET SAW SINGLE PHASE 60HZ",
       |          "id": "3",
       |          "weight": {
       |            "units": "LB",
       |            "value": 400.0
       |          },
       |          "pieces": 1,
       |          "dimensions": {
       |            "length": 70,
       |            "width": 4,
       |            "height": 3,
       |            "units": "IN"
       |          },
       |          "freightClass": "CLASS_077_5",
       |          "subPackagingType": "PALLET",
       |          "volume": {
       |            "units": "CUBIC_FT",
       |            "value": 0.480975
       |          }
       |        },
       |        {
       |          "handlingUnits": 1,
       |          "description": "T GLIDE RAILS ASSEMBLY 36 INCH RAILS ASSEMBLY",
       |          "id": "4",
       |          "weight": {
       |            "units": "LB",
       |            "value": 52.0
       |          },
       |          "pieces": 1,
       |          "dimensions": {
       |            "length": 70,
       |            "width": 4,
       |            "height": 3,
       |            "units": "IN"
       |          },
       |          "freightClass": "CLASS_077_5",
       |          "subPackagingType": "PALLET",
       |          "volume": {
       |            "units": "CUBIC_FT",
       |            "value": 0.480975
       |          }
       |        }
       |      ],
       |      "totalHandlingUnits": 4,
       |      "clientDiscountPercent": $discount
       |    },
       |    "freightShipmentSpecialServices": {
       |      "specialServiceTypes": ["DO_NOT_STACK_PALLETS"]
       |    }
       |  }
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
  private val callRateQuote =
    feed(feeder)
      .exec(ensureValidToken)
      .exec(
        http("FXF Rate Quote Request")
          .post("/fapi/getRateQuote")
          .header("Authorization", (session: Session) => "Bearer " + session("token").as[String])
          .body(StringBody(session => buildRateQuoteBody(session))).asJson
          .check(status.in(200, 201, 202))
          .check(bodyString.saveAs("responseBody"))
      )


  // ====== Scenario: get token once per VU, then make requests ======
  val paceDuration = 1.seconds
  private val scn = scenario("MULE → Rate Quote API")
    .exec(acquireToken)
    .pause(100.millis)
    .forever {
      exec(callRateQuote).pace(paceDuration)
    }

  // Configuration
  val usersPerStep = 3
  val Duration = 1.minutes
  val steps = 10 // 4 * 5 = 20 users max
  val soakDuration = 5.minutes
  val rampDownTime = 5.minutes
  val maxUsers = usersPerStep * steps

  setUp(
    scn.inject(
      // Step load: add 4 users every 3 minutes
      incrementConcurrentUsers(usersPerStep)
        .times(steps)
        .eachLevelLasting(Duration)
        .startingFrom(3),

      // Soak phase: keep max users constant for 30 minutes
      constantConcurrentUsers(maxUsers)
        .during(soakDuration),

      //Ramp-down: slowly remove users
      rampConcurrentUsers (maxUsers)
        .to(0)
        .during(rampDownTime)
    )
  ).protocols(httpProtocol)
    .maxDuration(20.minutes)
}