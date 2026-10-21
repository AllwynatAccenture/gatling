var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "717",
        "ok": "0",
        "ko": "717"
    },
    "minResponseTime": {
        "total": "2196",
        "ok": "-",
        "ko": "2196"
    },
    "maxResponseTime": {
        "total": "11772",
        "ok": "-",
        "ko": "11772"
    },
    "meanResponseTime": {
        "total": "3868",
        "ok": "-",
        "ko": "3868"
    },
    "standardDeviation": {
        "total": "1540",
        "ok": "-",
        "ko": "1540"
    },
    "percentiles1": {
        "total": "3382",
        "ok": "-",
        "ko": "3382"
    },
    "percentiles2": {
        "total": "5225",
        "ok": "-",
        "ko": "5225"
    },
    "percentiles3": {
        "total": "6574",
        "ok": "-",
        "ko": "6574"
    },
    "percentiles4": {
        "total": "7252",
        "ok": "-",
        "ko": "7252"
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
    "count": 0,
    "percentage": 0.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 717,
    "percentage": 100.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.39",
        "ok": "-",
        "ko": "2.39"
    }
},
contents: {
"req_fxf-rate-quote---252719241": {
        type: "REQUEST",
        name: "FXF Rate Quote Request",
path: "FXF Rate Quote Request",
pathFormatted: "req_fxf-rate-quote---252719241",
stats: {
    "name": "FXF Rate Quote Request",
    "numberOfRequests": {
        "total": "717",
        "ok": "0",
        "ko": "717"
    },
    "minResponseTime": {
        "total": "2196",
        "ok": "-",
        "ko": "2196"
    },
    "maxResponseTime": {
        "total": "11772",
        "ok": "-",
        "ko": "11772"
    },
    "meanResponseTime": {
        "total": "3868",
        "ok": "-",
        "ko": "3868"
    },
    "standardDeviation": {
        "total": "1540",
        "ok": "-",
        "ko": "1540"
    },
    "percentiles1": {
        "total": "3382",
        "ok": "-",
        "ko": "3382"
    },
    "percentiles2": {
        "total": "5225",
        "ok": "-",
        "ko": "5225"
    },
    "percentiles3": {
        "total": "6574",
        "ok": "-",
        "ko": "6574"
    },
    "percentiles4": {
        "total": "7252",
        "ok": "-",
        "ko": "7252"
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
    "count": 0,
    "percentage": 0.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 717,
    "percentage": 100.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.39",
        "ok": "-",
        "ko": "2.39"
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
