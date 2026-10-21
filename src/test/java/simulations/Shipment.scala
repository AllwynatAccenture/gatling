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

    .baseUrl("https://fxf-test-gtwy-cwul4s.4qpgqv.usa-e2.cloudhub.io/fxf-external-ship/fxfgw/route")
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

  val requestPayload = "{ \"labelResponseOptions\":\"LABEL\", \"oneLabelAtATime\":\"false\", \"freightRequestedShipment\":{ \"shipper\":{ \"address\":{ \"streetLines\":[ \"501 THERESA ST\" ], \"city\":\"PERRYVILLE\", \"postalCode\":\"63775\", \"stateOrProvinceCode\":\"MO\", \"countryCode\":\"US\" }, \"contact\":{ \"companyName\":\"RUSTIC FLAGS & KENNELS-FXFD\", \"phoneNumber\":4406539321 } }, \"recipient\":{ \"address\":{ \"streetLines\":[ \"34 CASTLEGROVE BLVD\" ], \"city\":\"NORTH YORK\", \"postalCode\":\"M3A1L1\", \"stateOrProvinceCode\":\"ON\", \"countryCode\":\"CA\" }, \"contact\":{ \"personName\":\"Recipient name\", \"companyName\":\"Recipient Company Name\", \"phoneNumber\":1234567890 }, \"deliveryInstructions\":\"BASIC PICKUP\" }, \"pickupType\":\"CONTACT_FEDEX_TO_SCHEDULE\", \"serviceType\":\"FEDEX_FREIGHT_PRIORITY\", \"packagingType\":\"YOUR_PACKAGING\", \"emailNotificationDetail\":{ \"aggregationType\":\"PER_PACKAGE\", \"emailNotificationRecipients\":[ { \"name\":\"Joe Smith\", \"emailNotificationRecipientType\":\"SHIPPER\", \"emailAddress\":\"799240@fedex.com\", \"notificationFormatType\":\"TEXT\", \"notificationType\":\"EMAIL\", \"locale\":\"en_US\", \"notificationEventType\":[  ] } ], \"personalMessage\":\"your personal message here\" }, \"customsClearanceDetail\":{ \"totalCustomsValue\":{ \"amount\":100, \"currency\":\"USD\" }, \"commodities\":[ { \"description\":\"DSLR Camera\", \"countryOfManufacture\":\"US\", \"numberOfPieces\":1, \"weight\":{ \"value\":150, \"units\":\"LB\" }, \"quantity\":1, \"quantityUnits\":\"PCS\", \"unitPrice\":{ \"amount\":100, \"currency\":\"USD\" }, \"customsValue\":{ \"amount\":100, \"currency\":\"USD\" } } ] }, \"shippingChargesPayment\":{ \"paymentType\":\"SENDER\", \"payor\":{ \"responsibleParty\":{ \"accountNumber\":{ \"value\":\"609430613\" } } } }, \"freightShipmentDetail\":{ \"role\":\"SHIPPER\", \"liabilityCoverageDetail\":{ \"coverageType\":\"USED_OR_RECONDITIONED\", \"coverageAmount\":{ \"amount\":12.45, \"currency\":\"USD\" } }, \"totalHandlingUnits\":\"1\", \"lineItem\":[ { \"pieces\":\"1\", \"handlingUnits\":\"1\", \"freightClass\":\"CLASS_060\", \"subPackagingType\":\"BASKET\", \"description\":\"Line item description\", \"weight\":{ \"units\":\"LB\", \"value\":\"150\" }, \"liabilityCoverageDetail\":{ \"coverageType\":\"USED_OR_RECONDITIONED\", \"coverageAmount\":{ \"amount\":12.45, \"currency\":\"USD\" } }, \"dimensions\":{ \"length\":96, \"width\":75, \"height\":80, \"units\":\"IN\" }, \"id\":\"item-02\" } ], \"fedExFreightBillingContactAndAddress\":{ \"address\":{ \"streetLines\":[ \"47 ARGYLE CRES\" ], \"city\":\"BRAMPTON\", \"stateOrProvinceCode\":\"ON\", \"postalCode\":\"L6T1M9\", \"countryCode\":\"CA\" } }, \"fedExFreightAccountNumber\":{ \"value\":\"609430613\" } }, \"freightShipmentSpecialServices\":{ \"specialServiceTypes\":[ \"FREIGHT_DIRECT\" ], \"freightDirectDetail\":{ \"freightDirectDataDetails\":[ { \"type\":\"BASIC\", \"transportationType\":\"PICKUP\", \"emailAddress\":\"testuser@fedex.com\", \"phoneNumberDetails\":[ { \"phoneNumberType\":\"MOBILE\", \"phoneNumber\":\"4406539321\" } ] } ] } }, \"rateRequestType\":[ \"LIST\" ], \"labelSpecification\":{ \"labelFormatType\":\"COMMON2D\", \"labelStockType\":\"PAPER_LETTER\", \"imageType\":\"PDF\", \"labelPrintingOrientation\":\"TOP_EDGE_OF_TEXT_FIRST\" }, \"shippingDocumentSpecification\":{ \"shippingDocumentTypes\":[ \"FEDEX_FREIGHT_STRAIGHT_BILL_OF_LADING\" ], \"freightBillOfLadingDetail\":{ \"format\":{ \"stockType\":\"PAPER_LETTER\", \"docType\":\"PDF\" } } }, \"requestedPackageLineItems\":[ { \"weight\":{ \"units\":\"LB\", \"value\":\"150\" }, \"liabilityCoverageDetail\":{ \"coverageType\":\"USED_OR_RECONDITIONED\", \"coverageAmount\":{ \"amount\":12.45, \"currency\":\"USD\" } }, \"dimensions\":{ \"length\":10, \"width\":10, \"height\":10, \"units\":\"IN\" }, \"associatedFreightLineItems\":[ { \"id\":\"item-02\" } ], \"subPackagingType\":\"BASKET\" } ] }, \"accountNumber\":{ \"value\":\"709948393\" } }"
//  private def buildShipmentBody(session: Session): String = {
//    val shipperCity = session("shipperCity").as[String]
//    val shipperState = session("shipperState").as[String]
//    val shipperPostalCode = session("shipperPostalCode").as[String]
//    val recipientCity = session("recipientCity").as[String]
//    val recipientState = session("recipientState").as[String]
//    val recipientPostalCode = session("recipientPostalCode").as[String]
//    s"""{
//       |  "freightRequestedShipment": {
//       | {
//       | "labelResponseOptions": "LABEL",
//       | "oneLabelAtATime": "false",
//       | "freightRequestedShipment": {
//       | "shipper": {
//       | "address": {
//       | "streetLines": [
//       | "501 THERESA ST"
//       | ],
//       | "city": "PERRYVILLE",
//       | "postalCode": "63775",
//       | "stateOrProvinceCode": "MO",
//       | "countryCode": "US"
//       | },
//       | "contact": {
//       | "companyName": "RUSTIC FLAGS & KENNELS-FXFD",
//       | "phoneNumber": 4406539321
//       | }
//       |},
//       | "recipient": {
//       | "address": {
//       | "streetLines": [
//       | "34 CASTLEGROVE BLVD"
//       | ],
//       | "city": "NORTH YORK",
//       | "postalCode": "M3A1L1",
//       | "stateOrProvinceCode": "ON",
//       | "countryCode": "CA"
//       |},
//       | "contact": {
//       | "personName": "Recipient name",
//       | "companyName": "Recipient Company Name",
//       | "phoneNumber": 1234567890
//       |},
//       | "deliveryInstructions": "BASIC PICKUP"
//       |},
//       | "pickupType": "CONTACT_FEDEX_TO_SCHEDULE",
//       | "serviceType": "FEDEX_FREIGHT_PRIORITY",
//       | "packagingType": "YOUR_PACKAGING",
//       | "emailNotificationDetail": {
//       | "aggregationType": "PER_PACKAGE",
//       | "emailNotificationRecipients": [
//       |{
//       | "name": "Joe Smith",
//       | "emailNotificationRecipientType": "SHIPPER",
//       | "emailAddress": "799240@fedex.com",
//       | "notificationFormatType": "TEXT",
//       | "notificationType": "EMAIL",
//       | "locale": "en_US",
//       | "notificationEventType": []
//       | }
//       |   ],
//       | "personalMessage": "your personal message here"
//       |},
//       | "customsClearanceDetail": {
//       | "totalCustomsValue": {
//       | "amount": 100,
//       | "currency": "USD"
//       |},
//       | "commodities": [
//       | {
//       | "description": "DSLR Camera",
//       | "countryOfManufacture": "US",
//       | "numberOfPieces": 1,
//       | "weight": {
//       | "value": 150,
//       | "units": "LB"
//       |},
//       | "quantity": 1,
//       | "quantityUnits": "PCS",
//       | "unitPrice": {
//       | "amount": 100,
//       | "currency": "USD"
//       |},
//       | "customsValue": {
//       | "amount": 100,
//       | "currency": "USD"
//       |  }
//       |  }
//       |   ]
//       | },
//       | "shippingChargesPayment": {
//       | "paymentType": "SENDER",
//       | "payor": {
//       | "responsibleParty": {
//       | "accountNumber": {
//       | "value": "609430613"
//       |}
//       | }
//       | }
//       | },
//       | "freightShipmentDetail": {
//       | "role": "SHIPPER",
//       | "liabilityCoverageDetail": {
//       | "coverageType": "USED_OR_RECONDITIONED",
//       | "coverageAmount": {
//       | "amount": 12.45,
//       | "currency": "USD"
//       |}
//       |},
//       | "totalHandlingUnits": "1",
//       | "lineItem": [
//       | {
//       | "pieces": "1",
//       | "handlingUnits": "1",
//       | "freightClass": "CLASS_060",
//       | "subPackagingType": "BASKET",
//       | "description": "Line item description",
//       | "weight": {
//       | "units": "LB",
//       | "value": "150"
//       |  },
//       | "liabilityCoverageDetail": {
//       | "coverageType": "USED_OR_RECONDITIONED",
//       | "coverageAmount": {
//       | "amount": 12.45,
//       | "currency": "USD"
//       | }
//       | },
//       | "dimensions": {
//       | "length": 96,
//       | "width": 75,
//       | "height": 80,
//       | "units": "IN"
//       | },
//       | "id": "item-02"
//       | }
//       |   ],
//       | "fedExFreightBillingContactAndAddress": {
//       | "address": {
//       | "streetLines": [
//       | "47 ARGYLE CRES"
//       |  ],
//       | "city": "BRAMPTON",
//       | "stateOrProvinceCode": "ON",
//       | "postalCode": "L6T1M9",
//       | "countryCode": "CA"
//       | }
//       | },
//       | "fedExFreightAccountNumber": {
//       | "value": "609430613"
//       | }
//       | },
//       | "freightShipmentSpecialServices": {
//       | "specialServiceTypes": [
//       | "FREIGHT_DIRECT"
//       | ],
//       | "freightDirectDetail": {
//       | "freightDirectDataDetails": [
//       | {
//       | "type": "BASIC",
//       | "transportationType": "PICKUP",
//       | "emailAddress": "testuser@fedex.com",
//       | "phoneNumberDetails": [
//       | {
//       | "phoneNumberType": "MOBILE",
//       | "phoneNumber": "4406539321"
//       |}
//       |   ]
//       | }
//       |   ]
//       | }
//       | },
//       | "rateRequestType": [
//       | "LIST"
//       |  ],
//       | "labelSpecification": {
//       | "labelFormatType": "COMMON2D",
//       | "labelStockType": "PAPER_LETTER",
//       | "imageType": "PDF",
//       | "labelPrintingOrientation": "TOP_EDGE_OF_TEXT_FIRST"
//       | },
//       | "shippingDocumentSpecification": {
//       | "shippingDocumentTypes": [
//       | "FEDEX_FREIGHT_STRAIGHT_BILL_OF_LADING"
//       |   ],
//       | "freightBillOfLadingDetail": {
//       | "format": {
//       | "stockType": "PAPER_LETTER",
//       | "docType": "PDF"
//       | }
//       | }
//       | },
//       | "requestedPackageLineItems": [
//       | {
//       | "weight": {
//       | "units": "LB",
//       | "value": "150"
//       |  },
//       | "liabilityCoverageDetail": {
//       | "coverageType": "USED_OR_RECONDITIONED",
//       | "coverageAmount": {
//       | "amount": 12.45,
//       | "currency": "USD"
//       | }
//       |  },
//       | "dimensions": {
//       | "length": 10,
//       | "width": 10,
//       | "height": 10,
//       | "units": "IN"
//       | },
//       | "associatedFreightLineItems": [
//       | {
//       | "id": "item-02"
//       |    }
//       |      ],
//       | "subPackagingType": "BASKET"
//       |  }
//       |     ]
//       | },
//       | "accountNumber": {
//       | "value": "709948393"
//       |}
//    }""".stripMargin
//  }


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
          .body(StringBody(requestPayload)).asJson
          .check(status.in(200, 201, 202))
          //.check(bodyString.saveAs("responseBody"))
      )
  // ===== API call using the token =====
//  private val callTracking = feed(feeder).exec(ensureValidToken).exec(http("FXF SINGLE NUMBERS ROUTED FROM FAPI TO FXFT").post("/route/track/v1/trackingnumbers").header("Authorization", (session: Session) => "Bearer " + session("token").as[String]).body(StringBody(s => buildBody(s("trackingNumber").as[String]))).asJson.check(status.in(200, 201, 202)).check(bodyString.saveAs("responseBody")))
  val stepDuration = 1.seconds
  val requestsPerStep = 1
  // Dynamic pace calculation
  val paceDuration = 1.seconds

  val totalDurationSeconds = 60 * 60 // total planned test time in seconds
  val paceSeconds = paceDuration.toSeconds
  val iterations = (totalDurationSeconds / paceSeconds).toInt

  private val scn = scenario("MULE → Freight Shipment API")
    .exec(acquireToken)
    .pause(200.millis)
    .repeat(iterations) {
      exec(callShipment)
        .pace(paceDuration)
    }
  // Configuration
  val usersPerStep = 2
  val Duration = 1.minutes
  val steps = 1 // 4 * 5 = 20 users max
  val soakDuration = 1.minutes
  val rampDownTime = 1.minutes
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
        .startingFrom(usersPerStep),

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
    .maxDuration(2.minutes)
}