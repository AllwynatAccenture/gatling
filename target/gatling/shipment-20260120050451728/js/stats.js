var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "17754",
        "ok": "17750",
        "ko": "4"
    },
    "minResponseTime": {
        "total": "43",
        "ok": "111",
        "ko": "43"
    },
    "maxResponseTime": {
        "total": "9988",
        "ok": "9988",
        "ko": "65"
    },
    "meanResponseTime": {
        "total": "436",
        "ok": "436",
        "ko": "56"
    },
    "standardDeviation": {
        "total": "235",
        "ok": "235",
        "ko": "8"
    },
    "percentiles1": {
        "total": "365",
        "ok": "366",
        "ko": "57"
    },
    "percentiles2": {
        "total": "462",
        "ok": "462",
        "ko": "61"
    },
    "percentiles3": {
        "total": "811",
        "ok": "811",
        "ko": "64"
    },
    "percentiles4": {
        "total": "1341",
        "ok": "1341",
        "ko": "65"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 16825,
    "percentage": 94.76737636588938
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 660,
    "percentage": 3.717472118959108
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 265,
    "percentage": 1.4926213810972175
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 4,
    "percentage": 0.022530134054297626
},
    "meanNumberOfRequestsPerSecond": {
        "total": "21.14",
        "ok": "21.13",
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
        "total": "17754",
        "ok": "17750",
        "ko": "4"
    },
    "minResponseTime": {
        "total": "43",
        "ok": "111",
        "ko": "43"
    },
    "maxResponseTime": {
        "total": "9988",
        "ok": "9988",
        "ko": "65"
    },
    "meanResponseTime": {
        "total": "436",
        "ok": "436",
        "ko": "56"
    },
    "standardDeviation": {
        "total": "235",
        "ok": "235",
        "ko": "8"
    },
    "percentiles1": {
        "total": "365",
        "ok": "366",
        "ko": "57"
    },
    "percentiles2": {
        "total": "462",
        "ok": "462",
        "ko": "61"
    },
    "percentiles3": {
        "total": "811",
        "ok": "811",
        "ko": "64"
    },
    "percentiles4": {
        "total": "1341",
        "ok": "1341",
        "ko": "65"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 16825,
    "percentage": 94.76737636588938
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 660,
    "percentage": 3.717472118959108
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 265,
    "percentage": 1.4926213810972175
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 4,
    "percentage": 0.022530134054297626
},
    "meanNumberOfRequestsPerSecond": {
        "total": "21.14",
        "ok": "21.13",
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
