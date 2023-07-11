@ajio @prod @guestUser @coupon
Feature: Ajio B2C Scenarios

#  CONFIG=./configs/ajio_local_config.properties TAG="@ajio" PLATFORM=android ./gradlew run
#  CONFIG=./configs/ajio_local_config.properties TAG="@ajio" PLATFORM=web ./gradlew run
  @android @web
  Scenario: As a guest user, I should be able to apply a coupon to a "qasics" product with size "UK 10" in my shopping bag
    Given I, a guest user, search for "qasics" products
    And I apply filters for gender "men" with size "UK 10"
    When I select and add the 1st product with the right size to the bag
    Then I can apply a coupon from the bag

# CONFIG=./configs/ajio_local_config.properties TAG=@flick PLATFORM=android ./gradlew run
  @android @flick
  Scenario: As a guest user, I should be able to flick and see images in product details
    Given I open "Jackets" from "Topwear" section for "Men"
    When I select the first result
    Then I should be able to perform flick and view images