@amazon
  Feature: Amazon
  @web @prod
  Scenario: validate add to cart functionality for unregistered user
    Given User is on amazon homepage
    When  User search for "iphone 13"
    Then  User should see the search results
    When  User selects "Apple iPhone 13 (256GB) - Blue" product from the search results
    Then  User should see the product page and product title syncing "Apple iPhone 13 (256GB) - Blue"
    And   User should see Add to Cart
    When  User selects Add to Cart
    And   User navigates to cart
    Then  User should see the added item in the cart "Apple iPhone 13 (256GB) - Blue"

