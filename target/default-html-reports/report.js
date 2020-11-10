$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/SearchReiseauskunft.feature");
formatter.feature({
  "name": "Search Reiseauskunft functionality",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "User searchs with valid city names, default inputs and gets related summary text",
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
  "name": "the search result topic should show the cities names \"\u003ccityStart\u003e\" \"\u003ccityDest\u003e\"",
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
  "name": "User searchs with valid city names, default inputs and gets related summary text",
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
  "name": "the search result topic should show the cities names \"Leipzig Hbf\" \"Stuttgart Hbf\"",
  "keyword": "Then "
});
formatter.match({
  "location": "de.dbahn.step_definitions.SearchReiseauskunft.theSearchResultTopicShouldShowTheCitiesNames(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});