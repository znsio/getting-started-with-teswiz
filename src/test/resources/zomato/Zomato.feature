Feature: Search functionality

  Scenario: Search resturant on zomato as guest user
    Given I,a "guest user" on zomato homepage and select "Meerut" location
    When I search for "Flavours" resturant
    Then I should see resturant details

  Scenario: Search Hira Sweets resturant on zomato as guest user by detect location
    Given I as a "guest user" on zomato homepage and select detect current location
    When I search for "Hira Sweets" resturant
    Then I should see resturant details

  Scenario: Search Meerut restuarant in Delhi location as guest user
    Given I,a "guest user" on zomato homepage and select "Delhi" location
    When I search for "Meerut" resturant
    Then I should see querry warning

  Scenario: Detect current location disabled within system
    Given I as a "guest user" on zomato homepage and select detect current location
    Then I should see location disbaled message

  Scenario: Search chilli panner on zomato for dineout and delivery as guest user
    Given I,a "guest user" on zomato homepage and select "Meerut" location
    When I select "Chilli Panner" for "Dine-out"
    And I select "Chilli Panner" for "Delivery"
    Then I should see resturant list
