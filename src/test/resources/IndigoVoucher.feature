#Personalization and preview an Indigo gift voucher with invalid Promo code

@indigo
Feature: As a guest user, I can purchase gift vouchers

  #CONFIG=./configs/indigo_config.properties TAG="@indigo and @web" PLATFORM=web  ./gradlew run
  #CONFIG=./configs/indigo_android_config.properties TAG="@indigo and @android" PLATFORM=android  ./gradlew run

  @web @android
  Scenario: Guest user can personalise a gift voucher and proceed to pay at original amount with invalid promo code
    Given I, a guest user, personalise and preview a gift voucher with any amount and quantity
    When I apply invalid promo code
    Then I can purchase the gift voucher at the original amount
