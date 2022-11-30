@indigo
Feature: Scenario to personalize and preview gift voucher

  #Customise and preview an Indigo gift voucher with invalid Promo code
 # CONFIG=./configs/jiomeet_local_config.properties TAG="@jiomeet and @single-user" PLATFORM=web ./gradlew run
  @web
  Scenario: Guest user enter invalid promo code on voucher page and proceed to payment page at original amount
    Given I as a guest user,personalize and preview the gift voucher of any amount and quantity
    When  I apply the invalid promo code to check the status of total amount
    Then I should be allowed to proceed to the payment gateway with original amount


