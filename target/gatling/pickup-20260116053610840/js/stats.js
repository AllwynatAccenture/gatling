var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "2367",
        "ok": "2343",
        "ko": "24"
    },
    "minResponseTime": {
        "total": "306",
        "ok": "306",
        "ko": "1750"
    },
    "maxResponseTime": {
        "total": "4200",
        "ok": "4200",
        "ko": "3243"
    },
    "meanResponseTime": {
        "total": "474",
        "ok": "452",
        "ko": "2621"
    },
    "standardDeviation": {
        "total": "281",
        "ok": "167",
        "ko": "644"
    },
    "percentiles1": {
        "total": "430",
        "ok": "430",
        "ko": "3134"
    },
    "percentiles2": {
        "total": "472",
        "ok": "471",
        "ko": "3151"
    },
    "percentiles3": {
        "total": "605",
        "ok": "587",
        "ko": "3192"
    },
    "percentiles4": {
        "total": "1900",
        "ok": "845",
        "ko": "3232"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 2313,
    "percentage": 97.71863117870723
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 21,
    "percentage": 0.8871989860583016
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 9,
    "percentage": 0.38022813688212925
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 24,
    "percentage": 1.0139416983523446
},
    "meanNumberOfRequestsPerSecond": {
        "total": "3.94",
        "ok": "3.9",
        "ko": "0.04"
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
        "total": "2367",
        "ok": "2343",
        "ko": "24"
    },
    "minResponseTime": {
        "total": "306",
        "ok": "306",
        "ko": "1750"
    },
    "maxResponseTime": {
        "total": "4200",
        "ok": "4200",
        "ko": "3243"
    },
    "meanResponseTime": {
        "total": "474",
        "ok": "452",
        "ko": "2621"
    },
    "standardDeviation": {
        "total": "281",
        "ok": "167",
        "ko": "644"
    },
    "percentiles1": {
        "total": "430",
        "ok": "430",
        "ko": "3134"
    },
    "percentiles2": {
        "total": "472",
        "ok": "470",
        "ko": "3151"
    },
    "percentiles3": {
        "total": "605",
        "ok": "587",
        "ko": "3192"
    },
    "percentiles4": {
        "total": "1900",
        "ok": "845",
        "ko": "3232"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 2313,
    "percentage": 97.71863117870723
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 21,
    "percentage": 0.8871989860583016
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 9,
    "percentage": 0.38022813688212925
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 24,
    "percentage": 1.0139416983523446
},
    "meanNumberOfRequestsPerSecond": {
        "total": "3.94",
        "ok": "3.9",
        "ko": "0.04"
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
