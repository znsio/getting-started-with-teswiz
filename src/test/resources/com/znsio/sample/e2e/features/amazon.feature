@amazon
  Feature: Amazon
  @web @prod
  Scenario: validate add to cart functionality for unregistered user
    Given I am in the amazon homepage
    When I searches for "iphone 13"
    Then product should be visible in the search results
    And I add an product to cart
    Then I should see the added item into the cart
