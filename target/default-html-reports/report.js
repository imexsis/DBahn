$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/SearchReiseauskunft.feature");
formatter.feature({
  "name": "Search Reiseauskunft functionality",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Verify Search Reiseauskunft results with API",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@wip"
    }
  ]
});
formatter.step({
  "name": "the user searchs from \"\u003ccityStart\u003e\" to \"\u003ccityDest\u003e\" with default inputs",
  "keyword": "When "
});
formatter.step({
  "name": "get the journey datas from UI",
  "keyword": "And "
});
formatter.step({
  "name": "verify UI start and arrive hours with API \"\u003ccityStart\u003e\" \"\u003ccityDest\u003e\" hours data",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "cityStart",
        "cityDest"
      ]
    },
    {
      "cells": [
        "Leipzig Hbf",
        "Stuttgart Hbf"
      ]
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "the user is on the homepage",
  "keyword": "Given "
});
formatter.match({
  "location": "de.dbahn.step_definitions.SearchReiseauskunft.the_user_is_on_the_homepage()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify Search Reiseauskunft results with API",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@wip"
    }
  ]
});
formatter.step({
  "name": "the user searchs from \"Leipzig Hbf\" to \"Stuttgart Hbf\" with default inputs",
  "keyword": "When "
});
formatter.match({
  "location": "de.dbahn.step_definitions.SearchReiseauskunft.the_user_searchs_from_to_with_default_inputs(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "get the journey datas from UI",
  "keyword": "And "
});
formatter.match({
  "location": "de.dbahn.step_definitions.SearchReiseauskunft.getTheJourneyDatasFromUI()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "verify UI start and arrive hours with API \"Leipzig Hbf\" \"Stuttgart Hbf\" hours data",
  "keyword": "Then "
});
formatter.match({
  "location": "de.dbahn.step_definitions.SearchReiseauskunft.verifyUIStartAndArriveHoursWithAPIHoursData(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});