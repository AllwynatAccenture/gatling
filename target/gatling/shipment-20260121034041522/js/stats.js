var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "540",
        "ok": "540",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "1262",
        "ok": "1262",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2541",
        "ok": "2541",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1536",
        "ok": "1536",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "141",
        "ok": "141",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1513",
        "ok": "1513",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1602",
        "ok": "1602",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1766",
        "ok": "1766",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1998",
        "ok": "1998",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 0,
    "percentage": 0.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 0,
    "percentage": 0.0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 540,
    "percentage": 100.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.25",
        "ok": "2.25",
        "ko": "-"
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
        "total": "540",
        "ok": "540",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "1262",
        "ok": "1262",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2541",
        "ok": "2541",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1536",
        "ok": "1536",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "141",
        "ok": "141",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1513",
        "ok": "1513",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1602",
        "ok": "1602",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1766",
        "ok": "1766",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1998",
        "ok": "1998",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 0,
    "percentage": 0.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 0,
    "percentage": 0.0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 540,
    "percentage": 100.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.25",
        "ok": "2.25",
        "ko": "-"
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
