@IndigoGiftvoucherTests
Feature: Indigo Promo Code Tests

#  CONFIG=./configs/indigo_local_config.properties TAG="@IndigoGiftvoucherTests" PLATFORM=web ./gradlew run

  @InvalidPromoCode @web
  Scenario: As a guest user,personalise and preview an Indigo Gift Voucher with invalid promo code
    Given I, as a guest user personalise and preview Indigo gift voucher with denomination "3000" and quantity "2"
    When I apply an invalid promo code
    Then I should be able to make the purchase of the gift voucher at the original amount