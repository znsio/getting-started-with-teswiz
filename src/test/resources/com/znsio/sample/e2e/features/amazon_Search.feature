@productSearch @amazonSearch @prod
Feature: Amazon Product Search
# CONFIG=./configs/amazon_local_config.properties TAG="@web and @amazonSearch" PLATFORM=web IS_VISUAL=true ./gradlew run
  @android
  Scenario: GuestUser searches for the product and adds the product to the cart on Amazon
    Given I as a guest search "iphone 13" on Amazon HomePage
    When Guest should select the first product in search result
    Then Guest see the first product in the product page
    When Guest adds the product to the cart
    Then Guest see the product in the cart



