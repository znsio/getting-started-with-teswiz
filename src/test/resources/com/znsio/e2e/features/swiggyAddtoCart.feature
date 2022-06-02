Feature: Verify add item to cart

  @swiggy @web
  Scenario: To verify guest user is able to create cart
    Given user selects the "Nagpur, Maharashtra, India" location from the landing page
    When user sort the restaurant List by "Rating"
    And user selects restaurant from the result
    Then user adds the items in the cart
    And user should able to see cart created



