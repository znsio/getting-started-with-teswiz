@Amazon
Feature: Amazon product cart functionality
@web @prod
  Scenario: User can add products to the cart
    Given user is on homepage
    When user searches for "iPhone 13"
    And user select first product
    And user adds product to the cart
    Then product should be get added to the cart