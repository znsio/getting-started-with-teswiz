#Personalization and preview an Indigo gift voucher with invalid Promo code

Feature: As a guest user, I can purchase gift vouchers

  Scenario: Guest user can personalise a gift voucher and proceed to pay at original amount with invalid promo code
    Given I, a guest user, personalise and preview a gift voucher with any amount and quantity
    When I apply invalid promo code
    Then I can purchase the gift voucher at the original amount
