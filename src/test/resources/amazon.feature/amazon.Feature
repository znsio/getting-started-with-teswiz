Feature: Scenario for amazon

  @web
  Scenario: User should be able to add iphone 13 to the cart
    Given I logged in with valid credential
    When I search for "product"
    Then I should be able to see that "product" list
    When I add the "product" in cart
    Then "product" should be added to cart


