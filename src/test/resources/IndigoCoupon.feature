#Personalization and preview an Indigo gift voucher with invalid Promo code

Feature: Personalizing Indigo voucher

  Scenario: Guest user enter invalid promo code on voucher page and proceeds to payment page
    Given I, as a guest user personalise and preview gift voucher
    When I apply invalid promo code, and proceeds to payment page
    Then I should complete the payment with non discounted rate and receive payment confirmation



