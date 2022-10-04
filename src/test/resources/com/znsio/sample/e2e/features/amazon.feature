@amazon @assignment
Feature: In a meeting scenarios

#  CONFIG=./src/test/resources/configs/amazon_config.properties TAG="@amazon and @single-user" PLATFORM=web ./gradlew run
  @web @debug @single-user
  Scenario: user can add product to cart
    Given user searches for "iPhone 13" product on amazon landing page
    When user selects first product from search results
    And user adds product into cart
    Then added product should be visible into cart
