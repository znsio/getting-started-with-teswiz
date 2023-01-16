@amazonsearch @prod
Feature: Amazon search and cart validation
#  CONFIG=./configs/amazon_local_config.properties TAG="@amazonsearch" PLATFORM=web ./gradlew clean run

  @web @single-user
  Scenario: I, as a Guest User should be able to search the product and make cart ready
    Given I, searched for "iphone 13" product in amazon search bar
    When I view the first product from search list
    Then I should see the product details page
    When I add the product to the cart and navigate to the cart
    Then I should see the product in the cart