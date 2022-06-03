@web
Feature: User interaction with Cart
  @web @swig
  Scenario: Verify guest user can add items to cart
    Given User sets location to "Mumbai" on home page
    When User sorts the restaurants on the basis of "Rating"
    And User selected a restaurant
    And User adds food item in the cart
    Then Items should be added in the cart

  @web @swig
  Scenario: Verify guest user can remove items from the cart
    Given User sets location to "Pune" on home page
    When User sorts the restaurants on the basis of "Relevance"
    And User selected a restaurant
    And User adds food item in the cart
    Then Items should be added in the cart
    When User removes all items from the cart
    Then The cart should be empty



