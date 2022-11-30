@indigo-app
Feature: Customize and preview Indigo gift voucher.

#  CONFIG=./configs/indigo_config.properties TAG="@indigo-app and @web" PLATFORM=web ./gradlew run
  @web
  Scenario: As a Guest user, personalize and preview Indigo gift voucher with invalid promo code.
    Given User personalize and preview the Gift Voucher with any amount "3000" and quantity "2"
    And User enters invalid promo code
    Then User should complete the payment process at the original amount "INR 6000.00"