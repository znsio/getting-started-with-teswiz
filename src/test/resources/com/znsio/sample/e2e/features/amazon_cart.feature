@amazon
Feature: Searching product, adding to cart and verifying added to cart or not

  @web
  Scenario: User search for product, add product to cart and verify product added
    Given User has launched Amazon web application
    When User searches for "iphone 13"
    Then User get results related to search
    When User opens first result
    Then User gets navigated to product detail page
    And User validates add to cart
    When User add product to cart
    And User opens cart
    Then User able to see added product in cart