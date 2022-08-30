@amazon
Feature: Amazon test

  @web
  Scenario: User successfully add product to cart
    Given I am on Amazon homepage
    When I search for "IPHONE 13"
    And Add first product to cart
    Then Product gets added to the cart