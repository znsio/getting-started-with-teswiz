@amazon
Feature: Amazon

  @web @amazon @amazon-test
  Scenario: Add to cart
    Given I launch amazon application
    And I search for "Iphone 13" and verify results
    When I Open first search result details
    And I add above item to cart
    Then I verify details in cart