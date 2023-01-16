@amazon @prod
Feature: Amazon search and cart validation

#  CONFIG=./configs/amazon_local_config.properties TAG="@amazon" PLATFORM=web ./gradlew run
  @web
  Scenario: Guest User should be able to search for iPhone 13 and add it to the cart
    Given I, as a guest user, search for "iPhone 13" on amazon
    When I select the first product from the search results
    Then I should see the product detail page
    When I add the product to my cart
    Then I should be able to see the product in the cart