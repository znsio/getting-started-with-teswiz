@indigo
Feature: To buy gift vouchers as a guest user
#  CONFIG=./configs/indigo_config.properties TAG=indigoGiftVoucher PLATFORM=web ./gradlew run

  @web @indigoGiftVoucher
  Scenario: To personalise a gift voucher and preview with invalid promo code
    Given I, as a Guest user, personalize and preview "1" gift vouchers of price "5000"
    When I apply invalid promo code
    Then I can proceed for the payment at the original amount