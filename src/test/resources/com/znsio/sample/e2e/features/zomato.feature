@zomato
Feature: Zomato test

  #  CONFIG=./configs/zomato_local_config.properties TAG="@zomato" PLATFORM=web ./gradlew run
  @web @single-user
  Scenario: User should be able to book a table
    Given I, as a User have set the location to "NCR" on the home page
    When I book a table for 4 guests in a restaurant for 2 days ahead
    Then I am able to view login option