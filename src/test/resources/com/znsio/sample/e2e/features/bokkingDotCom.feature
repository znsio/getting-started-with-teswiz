@bookingDotCom
Feature: Search for flight options

#  CONFIG=./configs/bookingDotCom/bookingDotCom_local_web_config.properties PLATFORM=web TAG=bookingDotCom ./gradlew run
  @web @searchFlights
  Scenario: Search for one way ticket from Pune to Delhi for single passenger
    Given I want to book a flight ticket
    Then I search for a "One way" ticket to "Dubai"
