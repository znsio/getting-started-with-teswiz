@ajio @prod @guestUser @coupon
Feature: In a meeting scenarios

#  CONFIG=./configs/ajio_local_config.properties TAG="@ajio" PLATFORM=android ./gradlew run
  @android @web
  Scenario: As a guest user, I should be able to apply a coupon to a "qasics" product with size "UK 10" in my shopping bag
    Given I, a guest user, search for "qasics" products
    And I apply filters for gender "men" with size "UK 10"
    When I select and add the 1st product with the right size to the bag
    Then I can apply a coupon from the bag