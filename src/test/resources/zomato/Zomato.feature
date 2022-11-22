@zomato @web
Feature: Search functionality

      #CONFIG=./configs/zomato_config.properties TAG="@zomato and @web" PLATFORM=web  ./gradlew run

  Scenario: Search resturant on zomato as guest user
    Given I,a "guest user" on zomato homepage and select "Meerut" location
    When I search for "Flavour" resturant
    Then I should see resturant details

  Scenario: Search Flavour resturant on zomato as guest user by detect location
    Given I, a "guest user" on zomato homepage and select detect current location
    When I search for "Flavour" resturant
    Then I should see resturant details

  Scenario: Search Delhi restuarant in Meerut location as guest user
    Given I,a "guest user" on zomato homepage and select "Meerut" location
    When I search for resturant
    Then I should see querry warning

  Scenario: Search chilli panner on zomato for dineout and delivery as guest user
    Given I,a "guest user" on zomato homepage and select "Meerut" location
    When I select "Paneer" for "Delivery"
    And I select "Paneer" for "Dine-out"
    Then I should see resturant list
