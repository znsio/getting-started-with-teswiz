@indigo
Feature: Personalize and preview the gift voucher

 # CONFIG=./configs/indigo_local_config.properties TAG="@indigo and @android" PLATFORM=android ./gradlew run
   @web @android
  Scenario: Guest user enters invalid promo code and proceed to payment at original amount
    Given I, as a guest user personalize and preview the gift voucher with amount and quantity
    When  I apply the invalid promo code to check the status of total amount
    Then I should be allowed to proceed to the payment gateway with original amount


