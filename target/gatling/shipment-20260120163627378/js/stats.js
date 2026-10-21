var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "94724",
        "ok": "94718",
        "ko": "6"
    },
    "minResponseTime": {
        "total": "38",
        "ok": "73",
        "ko": "38"
    },
    "maxResponseTime": {
        "total": "2057",
        "ok": "2057",
        "ko": "54"
    },
    "meanResponseTime": {
        "total": "123",
        "ok": "123",
        "ko": "45"
    },
    "standardDeviation": {
        "total": "25",
        "ok": "25",
        "ko": "7"
    },
    "percentiles1": {
        "total": "119",
        "ok": "119",
        "ko": "45"
    },
    "percentiles2": {
        "total": "128",
        "ok": "128",
        "ko": "51"
    },
    "percentiles3": {
        "total": "165",
        "ok": "165",
        "ko": "54"
    },
    "percentiles4": {
        "total": "195",
        "ok": "195",
        "ko": "54"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 94711,
    "percentage": 99.98627591740214
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 6,
    "percentage": 0.006334191968244585
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 1,
    "percentage": 0.0010556986613740973
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 6,
    "percentage": 0.006334191968244585
},
    "meanNumberOfRequestsPerSecond": {
        "total": "26.31",
        "ok": "26.31",
        "ko": "0"
    }
},
contents: {
"req_fxf-freight-shi--442189272": {
        type: "REQUEST",
        name: "FXF Freight Shipment Create Request",
path: "FXF Freight Shipment Create Request",
pathFormatted: "req_fxf-freight-shi--442189272",
stats: {
    "name": "FXF Freight Shipment Create Request",
    "numberOfRequests": {
        "total": "94724",
        "ok": "94718",
        "ko": "6"
    },
    "minResponseTime": {
        "total": "38",
        "ok": "73",
        "ko": "38"
    },
    "maxResponseTime": {
        "total": "2057",
        "ok": "2057",
        "ko": "54"
    },
    "meanResponseTime": {
        "total": "123",
        "ok": "123",
        "ko": "45"
    },
    "standardDeviation": {
        "total": "25",
        "ok": "25",
        "ko": "7"
    },
    "percentiles1": {
        "total": "119",
        "ok": "119",
        "ko": "45"
    },
    "percentiles2": {
        "total": "128",
        "ok": "128",
        "ko": "51"
    },
    "percentiles3": {
        "total": "165",
        "ok": "165",
        "ko": "54"
    },
    "percentiles4": {
        "total": "195",
        "ok": "195",
        "ko": "54"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 94711,
    "percentage": 99.98627591740214
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 6,
    "percentage": 0.006334191968244585
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 1,
    "percentage": 0.0010556986613740973
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 6,
    "percentage": 0.006334191968244585
},
    "meanNumberOfRequestsPerSecond": {
        "total": "26.31",
        "ok": "26.31",
        "ko": "0"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
