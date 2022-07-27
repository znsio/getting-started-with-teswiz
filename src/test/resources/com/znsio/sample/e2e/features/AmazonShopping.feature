Feature: Amazon Shopping

  @web
  Scenario: Search for an product and add to cart.
    Given I login with valid Credentials - "9901982606", "123456789123786"
    When I search for - "IPhone 13"
    Then I verify the search results contain - "IPhone 13"
    And I add first product displayed in search results
    Then I verify the product added in cart



