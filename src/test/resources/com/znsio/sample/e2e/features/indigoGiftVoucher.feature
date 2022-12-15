@indigo @prod @guestUser @coupon
Feature: GoIndigo personalise gift voucher
#  CONFIG=./configs/indigo_local_config.properties TAG="@indigo" PLATFORM=web ./gradlew run
  @android @web
  Scenario: As a guest user, I should be able purchase gift voucher at original amount after promo code provided is invalid
    Given I, a guest user, personalize and preview gift vouchers with any denomination and quantity
    When I tried applying a promo code comes invalid
    Then I should be able to proceed to payment page with original amount