@amazon @prod
Feature: Validate Amazon search and Add to Cart functionality

  @android @web
  Scenario: Guest user should be able to search item and Add to Cart
    Given I, a guest user, search for the item "iphone 13" in Search bar
    And I select the "first" item from the search results
    When I add an item to the Cart
    Then item should be added to the Cart
