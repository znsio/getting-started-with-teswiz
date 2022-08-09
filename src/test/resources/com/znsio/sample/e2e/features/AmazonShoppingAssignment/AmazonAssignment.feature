@web @prod @debug @amazon
Feature: Shopping on Amazon

  Scenario: Search and add to Cart on Amazon
    Given User launches Amazon HomePage
    When User searches for "iPhone 13"
    Then User should see valid list of Product links for "iPhone 13"
    When User selects the first Product Link
    Then User lands on Product Landing Page
    When User add the Product to Cart
    And User goes to Cart
    Then User lands on Cart Page


#  Web implementation
#  - Launch amazon.in
#  - search for 'iphone 13'
#  - validate the results
#  - click on 1st product
#  - validate the product details page and the product title
#  - validate add to cart
#  - click on add to cart
#  - go to cart
#  - validate the same product is in the cart
