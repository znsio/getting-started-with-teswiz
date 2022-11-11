Feature: Scenario for amazon cart functionality

    #CONFIG=./configs/amazon_config.properties TAG="@amazon and @web" PLATFORM=web  ./gradlew run
  @web @amazon
  Scenario: User should be able to add iphone 13 to the cart
    Given I logged in with valid credentials
    When I search for iphone
    And I add the iphone in cart
    Then iphone should be added to cart
