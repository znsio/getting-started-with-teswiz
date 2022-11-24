@indigo @web
Feature: Customizing Indigo voucher

  Scenario: Validating error message with invalid promo code
    Given I, as a guest user select "Gift Voucher" on homepage
    When I proceed by selecting "5000" denomination and "2" quantity on voucher page
    Then On entering invalid "0000" code, I see error message



