var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "569",
        "ok": "569",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "308",
        "ok": "308",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2971",
        "ok": "2971",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1456",
        "ok": "1456",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "192",
        "ok": "192",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1427",
        "ok": "1427",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1496",
        "ok": "1496",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1656",
        "ok": "1656",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2369",
        "ok": "2369",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 1,
    "percentage": 0.17574692442882248
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 2,
    "percentage": 0.35149384885764495
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 566,
    "percentage": 99.47275922671353
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.38",
        "ok": "2.38",
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
        "total": "569",
        "ok": "569",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "308",
        "ok": "308",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2971",
        "ok": "2971",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1456",
        "ok": "1456",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "192",
        "ok": "192",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1427",
        "ok": "1427",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1496",
        "ok": "1497",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1656",
        "ok": "1656",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2369",
        "ok": "2369",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 1,
    "percentage": 0.17574692442882248
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 2,
    "percentage": 0.35149384885764495
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 566,
    "percentage": 99.47275922671353
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.38",
        "ok": "2.38",
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
