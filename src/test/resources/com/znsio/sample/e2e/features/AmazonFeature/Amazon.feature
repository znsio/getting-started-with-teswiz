Feature: Amazon feature

  @web
  Scenario: Add iphone 13 to the cart
    Given I login to amazon with "username" and "password"
    When I searched for iphone13 and selected the first item result
    And I add the iphone13 to the cart
    Then iphone13 is added to the cart