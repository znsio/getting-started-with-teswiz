@web
Feature Amazon search test

  @web
  Scenario Add to cart
    Given User is logged-in and is on "Home Page"
    When User searches for "Mobile"
    And User adds a product to cart
    Then Product is added to the cart