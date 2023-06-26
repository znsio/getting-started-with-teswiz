@web @prod @debug @bookMyShow
Feature: Movie booking on BookMyShow

#  CONFIG=./configs/BookMyShow_config.properties TAG="@bookMyShow" PLATFORM=web ./gradlew run
  Scenario: Verify seats are available for a movie or not on BookMyShow
    Given Login with a valid user
    When Select location as "Delhi-NCR"
    Then Open Movies page
    Then Open movie at position 2 from the list of Now Showing movies
    Then Choose the screen type if applicable for the chosen movie
    Then Choose day after tomorrow as date for the show
    Then Choose third cinema hall from the list
    Then Choose the second last time slot out of the available time slots for the chosen cinema hall
    Then Choose number of people as 2
    And Fail the test if the seat availability is less than 10% out of all the available seats.