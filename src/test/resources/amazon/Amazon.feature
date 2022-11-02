
Feature: Scenario for amazon

  @web
  Scenario: User should be able to add iphone 13 to the cart
    Given I logged in with valid credentials
    When I search for iphone
    Then I should be able to see that iphone list
    When I add the iphone in cart
    Then iphone should be added to cart
