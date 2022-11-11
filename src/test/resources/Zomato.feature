Feature: Scenario for zomato resturant search

  Scenario: User should be able to search resturant on zomato
    Given I should be able to select location - "Meerut"
    When I search for the nearby resturant - "Flavours"
    Then I can see that resturant details

  Scenario: User should be able to search resturant on zomato by detect location
    Given I should be able to select my location by using detect current location
    When I search for the "Hira Sweets" resturant
    Then I can see that resturant details

  Scenario: Verify error message when we enter wrong resturant name
    Given I should be able to select location as "Meerut"
    When I search for the resturant with invalid name - "Bikaneeer"
    Then I can see the error message

  Scenario: