@zomato @web
Feature: Book a dinner reservation without login in Zomato web application

#    PLATFORM=web TAG=zomato CONFIG=./configs/zomato_local_config.properties ./gradlew clean run
  Scenario: Without login try to book dinner reservation in Zomato web application
    Given I successfully launch homepage of Zomato web application
    When I click on dining option
    Then I should be redirected to dine-out page
    When I select location as "New Delhi, Delhi, India"
    Then I should get location selected as "New Delhi, Delhi, India"
    When I select restaurant number 3 from results
    Then I should get same restaurant which was selected
    When I try to book a table for 4 guests on a date day after tomorrow