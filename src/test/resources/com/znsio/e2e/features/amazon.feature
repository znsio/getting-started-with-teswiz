@web @prod
Feature: Add to cart functionality

  @amazon
  Scenario: User can add products to cart
    Given User is on homepage
    When User searches for "Mobile"
    And User adds a product to cart
    Then Product is added to the cart
