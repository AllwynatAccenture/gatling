Gatling plugin for Maven - Java FAPI Testing project
============================================

* USE FOLLOWING COMMAND TO RUN THE SIMULATION AND GENERATE THE REPORT:

```bash
FAPI TESTING
mvn gatling:test -Dgatling.simulationClass=com.fedex.fxf.test.FapiToFxftSingleNumberTrackSimulation
mvn gatling:test -Dgatling.simulationClass=simulations.FapiToTrkcSingleNumberTrackSimulation
mvn gatling:test -Dgatling.simulationClass=simulations.ProxyToFxftSingleNumberTrackSimulation
```