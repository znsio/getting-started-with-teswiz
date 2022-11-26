#Personalization and preview an Indigo gift voucher with invalid Promo code

Feature: Personalizing Indigo voucher

  @indigo @web
  Scenario: Guest user enter invalid promo code on voucher page and proceeds to payment page
    Given I, as a guest user personalise gift voucher and proceeds with single quantity
    When I apply invalid promo code, and proceeds to payment page
    Then I should complete the payment with non discounted rate and receive payment confirmation

Feature: Personalizing Indigo voucher

  @indigo @web
  Scenario: Guest user completes personalised voucher purchase with invalid promo code
    Given I, as a guest user personalise gift voucher and proceeds to cart page    When I apply invalid promo code, and proceeds to payment page
    Then I should complete the payment with non discounted rate and receive payment confirmation


  Scenario: Guest user enter invalid promo code on voucher page and proceeds to payment page
    Given I, as a guest user personalise and preview gift voucher with any amount and quanity
    When I apply invalid promo code, and proceeds to payment page
    Then I should complete the payment with non discounted rate and receive payment confirmation




