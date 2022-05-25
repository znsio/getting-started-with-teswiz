#noinspection SpellCheckingInspection
@web
Feature: Verify the Swiggy cart functionality

  @swiggy @web
  Scenario: Verify that the cart functionality for a user
    Given I am on restaurants page after selecting location "Mumbai" from home page
    And I sort the restaurants' listings by rating before selecting a restaurant
    And I am able to create cart by adding 2 food items to cart
    Then the corresponding food items are displayed in the cart
    And the cart counter increases
    When I remove the food item from a filled cart
    Then the corresponding food item is removed from the cart
    And the cart counter decreases
    When I remove the only food item from the cart
    Then the corresponding food item is removed from the cart
    And the cart becomes empty