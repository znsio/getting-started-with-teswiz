
Feature: Goindigo flight results

    #CONFIG=./configs/indigo_config.properties TAG="@indigo and @web" PLATFORM=web  ./gradlew run
    @web @indigo
    Scenario: Verify results for Departure time greater then 18:00 Hr
      Given I am in the landing page
      When I add arrival and departure details with dates
      And I should be able to see the flight details
      Then I should be able to add filter for departure time