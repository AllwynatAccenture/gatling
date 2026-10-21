var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "25778",
        "ok": "25776",
        "ko": "2"
    },
    "minResponseTime": {
        "total": "44",
        "ok": "293",
        "ko": "44"
    },
    "maxResponseTime": {
        "total": "5918",
        "ok": "5918",
        "ko": "59"
    },
    "meanResponseTime": {
        "total": "2581",
        "ok": "2581",
        "ko": "52"
    },
    "standardDeviation": {
        "total": "831",
        "ok": "831",
        "ko": "8"
    },
    "percentiles1": {
        "total": "2515",
        "ok": "2515",
        "ko": "52"
    },
    "percentiles2": {
        "total": "3181",
        "ok": "3181",
        "ko": "55"
    },
    "percentiles3": {
        "total": "4020",
        "ok": "4020",
        "ko": "58"
    },
    "percentiles4": {
        "total": "4595",
        "ok": "4595",
        "ko": "59"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 2,
    "percentage": 0.007758553805570642
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 2,
    "percentage": 0.007758553805570642
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 25772,
    "percentage": 99.9767243385833
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 2,
    "percentage": 0.007758553805570642
},
    "meanNumberOfRequestsPerSecond": {
        "total": "7.16",
        "ok": "7.16",
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
        "total": "25778",
        "ok": "25776",
        "ko": "2"
    },
    "minResponseTime": {
        "total": "44",
        "ok": "293",
        "ko": "44"
    },
    "maxResponseTime": {
        "total": "5918",
        "ok": "5918",
        "ko": "59"
    },
    "meanResponseTime": {
        "total": "2581",
        "ok": "2581",
        "ko": "52"
    },
    "standardDeviation": {
        "total": "831",
        "ok": "831",
        "ko": "8"
    },
    "percentiles1": {
        "total": "2515",
        "ok": "2515",
        "ko": "52"
    },
    "percentiles2": {
        "total": "3181",
        "ok": "3181",
        "ko": "55"
    },
    "percentiles3": {
        "total": "4019",
        "ok": "4020",
        "ko": "58"
    },
    "percentiles4": {
        "total": "4595",
        "ok": "4595",
        "ko": "59"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 2,
    "percentage": 0.007758553805570642
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 2,
    "percentage": 0.007758553805570642
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 25772,
    "percentage": 99.9767243385833
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 2,
    "percentage": 0.007758553805570642
},
    "meanNumberOfRequestsPerSecond": {
        "total": "7.16",
        "ok": "7.16",
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
