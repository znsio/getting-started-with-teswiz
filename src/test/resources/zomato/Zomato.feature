Feature: Scenario for zomato resturant search

  Scenario: User should be able to search resturant on zomato
    Given I am on the zomato homepage
    And I should be able to select location - "Meerut"
    When I search for the nearby resturant - "Flavours"
    Then I can see that resturant details

  Scenario: Search resturant on zomato by using detect location
    Given I am on the zomato homepage
    And I should be able to select my location by using detect current location
    When I search for the "Hira Sweets" resturant
    Then I can see that resturant details - "Hira Sweets"

  Scenario: Verify error message when we enter wrong resturant name
    Given I am on the zomato homepage
    And I should be able to select location - "Meerut"
    When I search for the resturant with invalid name - "Bikaneeer"
    Then I can see the invalid resturant name error message

  Scenario: Validate other Meerut restuarant in Delhi location
    Given I am on the zomato homepage
    And I should be able to select location - "Delhi"
    When I search for the resturant of other location - "Flavours" "Meerut"
    Then It should not appear on the list

  Scenario: Validate error message when detect current location in disabled within system
    Given I am on the zomato homepage
    When I should be able to select my location by using detect current location
    Then I can see the location error message

  Scenario: User should be able to search dish on zomato for delivery
    Given I am on the zomato homepage
    And I should be able to select location - "Meerut"
    When I search for a dish "Chilli Panner" for "Delivery"
    Then I can see the resturant details which deliver "Chilli Panner"

  Scenario: User should be able to search dish on zomato for dineout
    Given I am on the zomato homepage
    And I should be able to select location - "Meerut"
    When I search for a dish "Chilli Panner" for "Dine-out"
    Then I can see the resturant details where I can dineout "Chilli Panner"