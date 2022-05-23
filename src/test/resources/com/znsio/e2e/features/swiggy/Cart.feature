@web
Feature: Verify the cart functionality for Swiggy

  @web @swiggy
  Scenario: Guest user is able to create cart
    Given User is on Home Page and sets location to "Hitech City, Hyderabad, Telangana, India"
    When User sorts restaurants by ratings on Restaurant listing page
    And User selects "Roast Bowl" from Restaurant List
    And User adds "4" units of food items from "Recommended" category to cart
    Then Cart should have "4 ITEMS" added





