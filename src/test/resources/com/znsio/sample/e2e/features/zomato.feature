@zomato @web
Feature: Book a dinner reservation without login in Zomato web application

#    PLATFORM=web TAG=zomato CONFIG=./configs/zomato_local_config.properties ./gradlew clean run
  Scenario: Without login try to book dinner reservation in Zomato web application
    Given I successfully launch homepage of Zomato web application
    When I select dining option
    Then I should be redirected to dine-out page
    When I select a specific location
    Then the same location should be displayed
    When I choose a restaurant from selected location
    Then the same restaurant should be displayed
    When I try to book a table
    Then a login pop-up message should be displayed