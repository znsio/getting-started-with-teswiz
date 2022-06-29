@amazon
  Feature: Add to cart functionality.

    @web @amazonAddSingleItemToCart
    Scenario: User can add products into the cart.
      Given user is on the homepage
      When user searches for "i phone 13"
      And user selects the first item from the search results
      And user add product into the cart
      Then added product should be visible in the cart
