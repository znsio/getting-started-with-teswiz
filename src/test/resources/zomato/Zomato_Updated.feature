
Feature: Search functionality on Zomato

  Scenario: Guest user can see the resturant reviews
    Given I, a "guest user" provides location from the landing page
    When I sort the resturant list by "Rating" and select "1" resturant from results
    Then I see the resturant reviews and

    Scenario:  Guest user to see resturant directions
      Given I, a logged in user