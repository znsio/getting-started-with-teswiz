
Feature: Personalizing Indigo voucher

  @indigo @web
  Scenario: Validating error message with invalid promo code and proceed to paymemnt page
    Given I, as a guest user personalise gift voucher with denomination as "5000" and quanity as "2"
    When I provide promo code as "000",invalid promo code error appears and no discount provided
    Then On providing receiver and sender details, I proceed to payment page



