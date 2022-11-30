
  @indigo
Feature: Indigo gift voucher with invalid promo code

  @web @android
Scenario: As a guest user, Personalise and preview an Indigo gift voucher with invalid Promo code
  Given I as a guest user, preview and personalise "2" gift vouchers of "5000" rupees
  When I apply a "invalid" promo code
  Then I should able to purchase the ticket of "10000" rupees
