@yatra @flightBooking @prod
Feature: Flight booking

#  CONFIG=./configs/yatra_local_config.properties TAG="@yatra" PLATFORM=web ./gradlew run
  @multi-city @web @single-user
  Scenario: As a guest user, I should be able to book a flight ticket for a "business class" "non-stop" multi-city round trip
    Given I, as guest user, search for a "multi-city" trip with source "Mumbai" and destination "Bangalore" for first trip
    And I select flight from source "Goa" and destination "Chennai" for second trip
    When I add 2 adults, 3 children and 1 infant passengers
    Then I choose "Business" class category "non-stop" flight

