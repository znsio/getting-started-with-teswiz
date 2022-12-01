@indigo @giftVoucher
  #CONFIG=./configs/indigo.properties TAG="@giftVoucher and @web" PLATFORM=web ./gradlew run
  #CONFIG=./configs/indigo.properties TAG="@giftVoucher and @android" PLATFORM=android ./gradlew run
Feature: Indigo Gift Voucher
  @web @android
  Scenario: Personalise and preview gift voucher with invalid promo code
    Given I as a guest user personalise and preview '3' gift vouchers of '3000' each
    When I apply Invalid promo code
    Then I can complete the payment with non discounted amount
