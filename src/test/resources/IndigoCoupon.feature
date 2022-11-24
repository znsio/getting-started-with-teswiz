
#Personalization and preview an Indigo gift voucher with invalid Promo code

Feature: Personalizing Indigo voucher

  @indigo @web
  Scenario: Guest user enter invalid promo code on voucher page and proceeds to payment page
    Given I, as a guest user personalise gift voucher and proceeds with single quantity
    When I applies invalid promo code, and proceeds to payment page
    Then I should complete the payment and receive payment confirmation



    #able not use




