@bookMyShow @prod
Feature: BookMyShow Seat Selection

#  CONFIG=./configs/bms_local_config.properties TAG="@BookMyShowScenario1" PLATFORM=web ./gradlew run


  @web @BookMyShowScenario1
  Scenario: As a guest user, I should be able to validate that the percentage of vacant seats is >10
    Given I, a guest user from "Delhi" wants to see movie which is positioned at "2" on day after tomorrow
    When I select seats for "2" for the second last time slot in cinema hall present at position "3"
    Then I check for availability of seats that should be more than "10" percent
