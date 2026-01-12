package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class Shipment extends Simulation {

  // ===== Config via -D props =====
  private val clientId     = sys.props.getOrElse("client_id", "0oatnayhm1u8kaBLZ1d7")
  private val clientSecret = sys.props.getOrElse("client_secret", "PQLjSfLy2TYdY72TpHt81NLz24nfR5Q9meAg2Ux1k30mnkZDQQlyZRjFvEzNiCOt")

  // ===== HTTP protocol (target) =====
  private val httpProtocol = http
    .baseUrl("https://test-gw-cwul4s.e6nxgs.usa-e2.cloudhub.io/fxf-external-ship/fxfgw/route")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
    .disableWarmUp
    .disableCaching

  // ===== Feeder for random data =====
  private val feeder = Iterator.continually {
    val shipperLoc = LocationData.randomLocation()
    val recipientLoc = LocationData.randomLocation()

    Map(
      "shipperCity" -> shipperLoc.city,
      "shipperState" -> shipperLoc.stateOrProvinceCode,
      "shipperPostalCode" -> shipperLoc.postalCode,
      "recipientCity" -> recipientLoc.city,
      "recipientState" -> recipientLoc.stateOrProvinceCode,
      "recipientPostalCode" -> recipientLoc.postalCode
    )
  }

  private def buildShipmentBody(session: Session): String = {
    val shipperCity = session("shipperCity").as[String]
    val shipperState = session("shipperState").as[String]
    val shipperPostalCode = session("shipperPostalCode").as[String]
    val recipientCity = session("recipientCity").as[String]
    val recipientState = session("recipientState").as[String]
    val recipientPostalCode = session("recipientPostalCode").as[String]

    s"""{
       |  "freightRequestedShipment": {
       |    "shipDatestamp": "2025-02-28",
       |    "pickupType": "CONTACT_FEDEX_TO_SCHEDULE",
       |    "serviceType": "FEDEX_FREIGHT_PRIORITY",
       |    "packagingType": "YOUR_PACKAGING",
       |    "totalWeight": 200,
       |    "preferredCurrency": "USD",
       |    "shipper": {
       |      "address": {
       |        "streetLines": [
       |          "1900 WHITE ARUM LN"
       |        ],
       |        "city": "$shipperCity",
       |        "stateOrProvinceCode": "$shipperState",
       |        "postalCode": "$shipperPostalCode",
       |        "countryCode": "US",
       |        "residential": false
       |      },
       |      "contact": {
       |        "personName": "LTL Shipper",
       |        "phoneNumber": "9012476688",
       |        "phoneExtension": "",
       |        "companyName": "MICHEALRAYMOND"
       |      }
       |    },
       |    "recipient": {
       |      "address": {
       |        "streetLines": [
       |          "Consignee 1 Address1",
       |          "Consignee 1 Address2"
       |        ],
       |        "city": "$recipientCity",
       |        "stateOrProvinceCode": "$recipientState",
       |        "postalCode": "$recipientPostalCode",
       |        "countryCode": "US",
       |        "residential": false
       |      },
       |      "contact": {
       |        "personName": "Consignee 1 Contact",
       |        "phoneNumber": "9012476688",
       |        "phoneExtension": "111111",
       |        "companyName": "Consignee 1 Company"
       |      },
       |      "tins": [
       |        {
       |          "number": "CONSIGNEE111111111",
       |          "tinType": "BUSINESS_NATIONAL",
       |          "effectiveDate": "0001-01-01T00:00:00",
       |          "expirationDate": "0001-01-01T00:00:00"
       |        }
       |      ]
       |    },
       |    "shippingChargesPayment": {
       |      "paymentType": "SENDER",
       |      "payor": {
       |        "responsibleParty": {
       |          "address": {
       |            "streetLines": [
       |              "1900 WHITE ARUM LN"
       |            ],
       |            "city": "$shipperCity",
       |            "stateOrProvinceCode": "$shipperState",
       |            "postalCode": "$shipperPostalCode",
       |            "countryCode": "US",
       |            "residential": false
       |          },
       |          "contact": {
       |            "personName": "LTL Shipper"
       |          },
       |          "accountNumber": {
       |            "value": "604849217"
       |          }
       |        }
       |      }
       |    },
       |    "freightShipmentSpecialServices": {
       |      "specialServiceTypes": [
       |        "CUSTOM_DELIVERY_WINDOW"
       |      ],
       |      "customDeliveryWindowDetail": {
       |        "requestDate": "2025-03-04",
       |        "requestTime": "12:12:00",
       |        "type": "ON"
       |      }
       |    },
       |    "freightShipmentDetail": {
       |      "role": "SHIPPER",
       |      "fedExFreightAccountNumber": null,
       |      "declaredValueUnits": "per lb",
       |      "lineItem": [
       |        {
       |          "handlingUnits": 1,
       |          "subPackagingType": "PALLET",
       |          "description": "Iron Sheets",
       |          "weight": {
       |            "units": "LB",
       |            "value": 200
       |          },
       |          "pieces": 2000,
       |          "freightClass": "CLASS_050",
       |          "purchaseOrderNumber": "12345",
       |          "id": "1",
       |          "dimensions": {
       |            "length": 50,
       |            "width": 40,
       |            "height": 60,
       |            "units": "IN"
       |          },
       |          "classProvidedByCustomer": true
       |        }
       |      ],
       |      "fedExFreightBillingContactAndAddress": {
       |        "address": {
       |          "streetLines": [
       |            "1900 WHITE ARUM LN"
       |          ],
       |          "city": "$shipperCity",
       |          "stateOrProvinceCode": "$shipperState",
       |          "postalCode": "$shipperPostalCode",
       |          "countryCode": "US",
       |          "residential": false
       |        },
       |        "contact": {
       |          "personName": "LTL Shipper",
       |          "companyName": "MICHEALRAYMOND"
       |        }
       |      },
       |      "declaredValuePerUnit": {
       |        "amount": 0,
       |        "currency": "USD"
       |      },
       |      "totalHandlingUnits": 1
       |    },
       |    "labelSpecification": {
       |      "labelFormatType": "COMMON2D",
       |      "customerSpecifiedDetail": {},
       |      "labelStockType": "PAPER_LETTER",
       |      "imageType": "PDF",
       |      "isAutoPrint": false
       |    },
       |    "shippingDocumentSpecification": {
       |      "shippingDocumentTypes": [],
       |      "freightBillOfLadingDetail": {
       |        "format": {
       |          "stockType": "PAPER_LETTER",
       |          "dispositions": [
       |            {
       |              "dispositionType": "RETURNED"
       |            }
       |          ],
       |          "docType": "PDF"
       |        }
       |      }
       |    },
       |    "requestedPackageLineItems": [
       |      {
       |        "sequenceNumber": "1",
       |        "subPackagingType": "PALLET",
       |        "customerReferences": [
       |          {
       |            "customerReferenceType": "P_O_NUMBER",
       |            "value": "12345"
       |          }
       |        ],
       |        "weight": {
       |          "units": "LB",
       |          "value": 200
       |        },
       |        "dimensions": {
       |          "length": 50,
       |          "width": 40,
       |          "height": 60,
       |          "units": "IN"
       |        },
       |        "physicalPackaging": "PALLET",
       |        "associatedFreightLineItems": [
       |          {
       |            "id": "1"
       |          }
       |        ]
       |      }
       |    ]
       |  },
       |  "labelResponseOptions": "LABEL",
       |  "accountNumber": {
       |    "value": "604007038"
       |  },
       |  "meterNumber": "259517830",
       |  "singleShotMPS": true
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
  private val callShipment =
    feed(feeder)
      .exec(ensureValidToken)
      .exec(
        http("FXF Freight Shipment Create Request")
          .post("/fapi/freight/shipment")
          .header("Authorization", (session: Session) => "Bearer " + session("token").as[String])
          .body(StringBody(session => buildShipmentBody(session))).asJson
          .check(status.in(200, 201, 202))
          .check(bodyString.saveAs("responseBody"))
      )

  // ====== Scenario: get token once per VU, then make requests ======
  private val scn = scenario("MULE → Freight Shipment API")
    .exec(acquireToken)
    .pause(200.millis)
    .forever {
      exec(callShipment).pause(200.millis)
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
