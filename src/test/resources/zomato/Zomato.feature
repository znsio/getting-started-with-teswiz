Feature: Search functionality

      #CONFIG=./configs/zomato_config.properties TAG="@zomato and @web" PLATFORM=web  ./gradlew run
  @zomato @web
  Scenario: Search resturant on zomato as guest user
    Given I,a "guest user" on zomato homepage and select "Meerut" location
    When I search for "Flavour" resturant
    Then I should see resturant details

 # @zomato @web
  Scenario: Search Flavour resturant on zomato as guest user by detect location
    Given I, a "guest user" on zomato homepage and select detect current location
    When I search for "Flavour" resturant
    Then I should see resturant details

 # @zomato @web
  Scenario: Search Delhi restuarant in Meerut location as guest user
    Given I,a "guest user" on zomato homepage and select "Meerut" location
    When I search for resturant
    Then I should see querry warning

 # @zomato @web
  Scenario: Detect current location disabled within system
    Given I, a "guest user" on zomato homepage and select detect current location
    Then I should see location disabled message

 # @zomato @web
  Scenario: Search chilli panner on zomato for dineout and delivery as guest user
    Given I,a "guest user" on zomato homepage and select "Meerut" location
    When I select "Chilli Paneer" for "Dine-out"
    And I select "Chilli Paneer" for "Delivery"
    Then I should see resturant list
