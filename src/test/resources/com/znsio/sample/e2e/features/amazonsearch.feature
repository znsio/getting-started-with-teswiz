@amazonsearch @prod
Feature: Amazon Search

#  CONFIG=./configs/amazonsearch_local_config.properties TAG="@amazonsearch" PLATFORM=web ./gradlew run

  @web @single-user @cart
  Scenario: Guest User search product on the amazon and add product to the cart
    Given I, as a guest user, search for "Iphone 13" in amazon search
    When I select the first product from the result list
    And I add the selected product to the shopping cart
    Then I should be able to see "Iphone 13" product in the cart