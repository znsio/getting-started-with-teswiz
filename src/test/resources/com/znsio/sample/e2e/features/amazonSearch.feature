@amazonSearch @prod @guestuser @cart
Feature: Search products on amazon

  #  CONFIG=./configs/amazon_search_local_config.properties TAG="@amazonSearch" PLATFORM=web ./gradlew run
  @web
  Scenario: As a guest user i should be able to search for product on amazon search and add it to cart
    Given I, a guest user search for an "Iphone 13 Red" product
    When I, select first item from the list
    Then I, should verify the product details
    When  I, add the product to the shopping cart
    Then I, should be able to see the product in the cart


