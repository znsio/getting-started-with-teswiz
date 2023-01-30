@amazon @prod
Feature: Search and cart validation on Amazon

#  CONFIG=./configs/amazon_local_config.properties TAG="@amazon and @single-user" PLATFORM=web ./gradlew run
#  CONFIG=./configs/amazon2_local_config.properties TAG="@amazon and @single-user" PLATFORM=android ./gradlew run

  @web @android @single-user
  Scenario: User searches and adds the product in cart
    Given I, a guest user, search for the product "iphone 13" in search bar on Amazon
    When I view the first product from the results list and add it to cart
    Then I should be able to see the product in cart

