@amazon @prod
Feature: Amazon Search

  #CONFIG=./configs/amazon_local_config.properties TAG="@amazon and @guest-user" PLATFORM=android ./gradlew run

  @web @android @guest-user
  Scenario: As a guest user, I should be able to search and add a product to amazon shopping cart

    Given I, a guest user, search for product "iphone 13" on amazon
    When I select the first product from the search results page
    And I add the product to the shopping cart
    Then I should be able to see the product in the shopping cart


