@amazon @prod
  Feature: Search and cart validation on Amazon

#  CONFIG=./configs/amazon_local_config.properties TAG="@amazon" PLATFORM=web ./gradlew run
    @web
    Scenario: Guest User searches and adds the product in cart
      Given I, a guest user, search for the product "iphone 13" in search bar on Amazon
      And I view the first product from the results list
      When I add the product to cart
      Then I should be able to see the product in cart

