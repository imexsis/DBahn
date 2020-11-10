$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/SearchReiseauskunft.feature");
formatter.feature({
  "name": "Search Reiseauskunft functionality",
  "description": "",
  "keyword": "Feature"
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
  "name": "User searchs with empty inputs",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@smoke"
    }
  ]
});
formatter.step({
  "name": "the user searchs from \"\" to \"\" with default inputs",
  "keyword": "When "
});
formatter.match({
  "location": "de.dbahn.step_definitions.SearchReiseauskunft.the_user_searchs_from_to_with_default_inputs(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the page title should be \"Deutsche Bahn: bahn.de - Verbindungen - Ihre Anfrage\"",
  "keyword": "Then "
});
formatter.match({
  "location": "de.dbahn.step_definitions.SearchReiseauskunft.thePageTitleShouldBe(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});