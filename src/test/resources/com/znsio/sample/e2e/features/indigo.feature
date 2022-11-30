@indigo
Feature: To buy gift vouchers as a guest user
#  CONFIG=./configs/indigo_config.properties TAG=indigoGiftVoucher PLATFORM=web ./gradlew run

  @web @indigoGiftVoucher
  Scenario: To personalise a gift voucher and preview with invalid promo code
    Given I personalise and preview a gift voucher of denomination "5000" and quantity "1"
    When I apply invalid promo code
    Then I can proceed for the payment at the original amount