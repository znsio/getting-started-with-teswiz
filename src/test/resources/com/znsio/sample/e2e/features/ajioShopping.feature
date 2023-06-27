@ajio @prod @ajioLoggedInUser
Feature: Shopping on Ajio

#  CONFIG=./configs/ajio_shopping_config.properties TAG="@ajioLoggedInUser" PLATFORM=web ./gradlew run
  @web
  Scenario: As a logged in user, search and add to Cart on Ajio
    Given User logs-in as "Host", search for "kurtas" products
    And Opens product at position 2 and add it to wishlist
    When Go to wishlist and add the product with the right size to the bag
    Then Apply a coupon from the bag and get the estimated delivery date