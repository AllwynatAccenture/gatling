var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "24273",
        "ok": "24272",
        "ko": "1"
    },
    "minResponseTime": {
        "total": "53",
        "ok": "325",
        "ko": "53"
    },
    "maxResponseTime": {
        "total": "2684925",
        "ok": "2684925",
        "ko": "53"
    },
    "meanResponseTime": {
        "total": "3143",
        "ok": "3143",
        "ko": "53"
    },
    "standardDeviation": {
        "total": "42167",
        "ok": "42168",
        "ko": "0"
    },
    "percentiles1": {
        "total": "2429",
        "ok": "2429",
        "ko": "53"
    },
    "percentiles2": {
        "total": "3052",
        "ok": "3052",
        "ko": "53"
    },
    "percentiles3": {
        "total": "3928",
        "ok": "3928",
        "ko": "53"
    },
    "percentiles4": {
        "total": "4420",
        "ok": "4420",
        "ko": "53"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 68,
    "percentage": 0.2801466650187451
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 410,
    "percentage": 1.6891195979071394
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 23794,
    "percentage": 98.02661393317678
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 1,
    "percentage": 0.004119803897334487
},
    "meanNumberOfRequestsPerSecond": {
        "total": "4.07",
        "ok": "4.07",
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
        "total": "24273",
        "ok": "24272",
        "ko": "1"
    },
    "minResponseTime": {
        "total": "53",
        "ok": "325",
        "ko": "53"
    },
    "maxResponseTime": {
        "total": "2684925",
        "ok": "2684925",
        "ko": "53"
    },
    "meanResponseTime": {
        "total": "3143",
        "ok": "3143",
        "ko": "53"
    },
    "standardDeviation": {
        "total": "42167",
        "ok": "42168",
        "ko": "0"
    },
    "percentiles1": {
        "total": "2429",
        "ok": "2429",
        "ko": "53"
    },
    "percentiles2": {
        "total": "3052",
        "ok": "3052",
        "ko": "53"
    },
    "percentiles3": {
        "total": "3928",
        "ok": "3928",
        "ko": "53"
    },
    "percentiles4": {
        "total": "4420",
        "ok": "4420",
        "ko": "53"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 68,
    "percentage": 0.2801466650187451
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 410,
    "percentage": 1.6891195979071394
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 23794,
    "percentage": 98.02661393317678
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 1,
    "percentage": 0.004119803897334487
},
    "meanNumberOfRequestsPerSecond": {
        "total": "4.07",
        "ok": "4.07",
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
