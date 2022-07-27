@amazon
Feature: Amazon

  @web @amazon @amazon-test
  Scenario: Add to cart
    Given I launch amazon application
    And I search for "Iphone 13"
    When I select the first product from the search results
    Then I add above item to cart