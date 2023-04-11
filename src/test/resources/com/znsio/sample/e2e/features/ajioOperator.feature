@prod
Feature: Ajio operator flow
@op_loginandLogout @web
  Scenario: Operator flow on login and logout
    Given  I as a ajio operator on login page with "avmso2@ril.com" and "Jio1234@"
    When operator land on seller page
    Then operator should logout


