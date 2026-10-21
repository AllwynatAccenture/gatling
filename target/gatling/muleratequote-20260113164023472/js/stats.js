var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "59",
        "ok": "0",
        "ko": "59"
    },
    "minResponseTime": {
        "total": "527",
        "ok": "-",
        "ko": "527"
    },
    "maxResponseTime": {
        "total": "9228",
        "ok": "-",
        "ko": "9228"
    },
    "meanResponseTime": {
        "total": "3526",
        "ok": "-",
        "ko": "3526"
    },
    "standardDeviation": {
        "total": "1132",
        "ok": "-",
        "ko": "1132"
    },
    "percentiles1": {
        "total": "3287",
        "ok": "-",
        "ko": "3287"
    },
    "percentiles2": {
        "total": "3330",
        "ok": "-",
        "ko": "3330"
    },
    "percentiles3": {
        "total": "5865",
        "ok": "-",
        "ko": "5865"
    },
    "percentiles4": {
        "total": "8229",
        "ok": "-",
        "ko": "8229"
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
    "count": 59,
    "percentage": 100.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.51",
        "ok": "-",
        "ko": "0.51"
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
        "total": "59",
        "ok": "0",
        "ko": "59"
    },
    "minResponseTime": {
        "total": "527",
        "ok": "-",
        "ko": "527"
    },
    "maxResponseTime": {
        "total": "9228",
        "ok": "-",
        "ko": "9228"
    },
    "meanResponseTime": {
        "total": "3526",
        "ok": "-",
        "ko": "3526"
    },
    "standardDeviation": {
        "total": "1132",
        "ok": "-",
        "ko": "1132"
    },
    "percentiles1": {
        "total": "3287",
        "ok": "-",
        "ko": "3287"
    },
    "percentiles2": {
        "total": "3330",
        "ok": "-",
        "ko": "3330"
    },
    "percentiles3": {
        "total": "5865",
        "ok": "-",
        "ko": "5865"
    },
    "percentiles4": {
        "total": "8229",
        "ok": "-",
        "ko": "8229"
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
    "count": 59,
    "percentage": 100.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.51",
        "ok": "-",
        "ko": "0.51"
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
