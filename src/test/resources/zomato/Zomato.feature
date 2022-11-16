Feature: Scenario for zomato resturant search

  Scenario: Search Flavours resturant on zomato
    Given I am on the zomato homepage
    And I select "Meerut" location
    When I search for "Flavours" resturant
    Then I should see resturant details

  Scenario: Search resturant on zomato by detect location
    Given I am on the zomato homepage
    And I select location by using detect current location
    When I search for "Hira Sweets" resturant
    Then I should see resturant details

  Scenario: Search Meerut restuarant in Delhi location
    Given I am on the zomato homepage
    And I select "Delhi" location
    When I search for "Meerut" resturant
    Then I should see querry warning

  Scenario: Detect current location disabled within system
    Given I am on the zomato homepage
    When I select location by using detect current location
    Then I should see location disbaled message

  Scenario: Search chilli panner on zomato for dineout and delivery
    Given I am on the zomato homepage
    And I select "Meerut" location
    When I select "Chilli Panner" for "Dine-out"
    And I select "Chilli Panner" for "Delivery"
    Then I should see resturant list

    #  Scenario: Search invalid resturant on Zomato
#    Given I am on the zomato homepage
#    And I select "Meerut" location
 #   When I search for the "Bikaneeer" resturant
  #  Then I should see