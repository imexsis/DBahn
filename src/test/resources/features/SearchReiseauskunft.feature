
Feature: Search Reiseauskunft functionality

#  As a user,
#  I want to search with city names
#  so that I can see the available trip plans and seats

  Background:
    Given the user is on the homepage

  @wip
  Scenario Outline: User searchs with valid city names, default inputs and gets related summary text
    When the user searchs from "<cityStart>" to "<cityDest>" with default inputs
    Then the search result topic should show the cities names "<cityStart>" "<cityDest>"
    Examples:
      | cityStart   | cityDest      |
      | Leipzig Hbf | Stuttgart Hbf |
#      | Dresden Hbf | Karlsruhe Hbf |
#      | Berlin Hbf  | Hamburg Hbf   |


  Scenario: User searchs with empty inputs
    When the user searchs from "" to "" with default inputs
    Then the page title should be "Deutsche Bahn: bahn.de - Verbindungen - Ihre Anfrage"


  Scenario Outline: Verify Search Reiseauskunft results with API
    When the user searchs from "<cityStart>" to "<cityDest>" with default inputs
    And get the journey datas from UI
    Then verify UI start and arrive hours with API "<cityStart>" "<cityDest>" hours data

    Examples:
      | cityStart     | cityDest      |
      | Leipzig Hbf   | Stuttgart Hbf |
#      | Karlsruhe Hbf | Mannheim Hbf  |
#      | Berlin Hbf    | Hamburg Hbf   |
