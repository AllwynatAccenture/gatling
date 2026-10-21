var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "102944",
        "ok": "43502",
        "ko": "59442"
    },
    "minResponseTime": {
        "total": "27",
        "ok": "303",
        "ko": "27"
    },
    "maxResponseTime": {
        "total": "60002",
        "ok": "6686",
        "ko": "60002"
    },
    "meanResponseTime": {
        "total": "268",
        "ok": "525",
        "ko": "79"
    },
    "standardDeviation": {
        "total": "353",
        "ok": "195",
        "ko": "323"
    },
    "percentiles1": {
        "total": "81",
        "ok": "488",
        "ko": "58"
    },
    "percentiles2": {
        "total": "472",
        "ok": "557",
        "ko": "70"
    },
    "percentiles3": {
        "total": "649",
        "ok": "769",
        "ko": "104"
    },
    "percentiles4": {
        "total": "986",
        "ok": "1071",
        "ko": "266"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 41661,
    "percentage": 40.46957569163817
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1582,
    "percentage": 1.5367578489275722
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 259,
    "percentage": 0.2515930991607087
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 59442,
    "percentage": 57.74207336027355
},
    "meanNumberOfRequestsPerSecond": {
        "total": "36.5",
        "ok": "15.43",
        "ko": "21.08"
    }
},
contents: {
"req_fxf-pickup-crea--469236733": {
        type: "REQUEST",
        name: "FXF Pickup Create Request",
path: "FXF Pickup Create Request",
pathFormatted: "req_fxf-pickup-crea--469236733",
stats: {
    "name": "FXF Pickup Create Request",
    "numberOfRequests": {
        "total": "102944",
        "ok": "43502",
        "ko": "59442"
    },
    "minResponseTime": {
        "total": "27",
        "ok": "303",
        "ko": "27"
    },
    "maxResponseTime": {
        "total": "60002",
        "ok": "6686",
        "ko": "60002"
    },
    "meanResponseTime": {
        "total": "268",
        "ok": "525",
        "ko": "79"
    },
    "standardDeviation": {
        "total": "353",
        "ok": "195",
        "ko": "323"
    },
    "percentiles1": {
        "total": "81",
        "ok": "488",
        "ko": "58"
    },
    "percentiles2": {
        "total": "472",
        "ok": "557",
        "ko": "70"
    },
    "percentiles3": {
        "total": "649",
        "ok": "769",
        "ko": "104"
    },
    "percentiles4": {
        "total": "986",
        "ok": "1071",
        "ko": "266"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 41661,
    "percentage": 40.46957569163817
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1582,
    "percentage": 1.5367578489275722
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 259,
    "percentage": 0.2515930991607087
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 59442,
    "percentage": 57.74207336027355
},
    "meanNumberOfRequestsPerSecond": {
        "total": "36.5",
        "ok": "15.43",
        "ko": "21.08"
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
