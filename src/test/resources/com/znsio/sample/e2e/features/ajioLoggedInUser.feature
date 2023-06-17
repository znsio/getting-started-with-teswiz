@ajio @prod @loggedInUser
Feature: Ajio LoggedIn User Scenarios


#  CONFIG=./configs/ajio_local_config.properties TAG="@validateCart" PLATFORM=web ./gradlew run
  @android @web @validateCart
  Scenario: As a loggedIn user,I should be able to Validate the cart is empty
    Given I login to ajio as "ajioTestUser"
    When I search for for "Shoes" product
    And I wishlist the 7th product and move it to cart
    Then I remove the product from cart and verify cart is empty by relog
