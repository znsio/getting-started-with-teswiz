Feature: Verify add to cart

  @swiggy @web
  Scenario: To verify user is able to create cart
    Given User is on Home Page
    When user adds the location
    |location|
    |Nagpur, Maharashtra, India|
    Then user will see Restaurants List for the Location
    When User sort the Restaurant List by Rating
    Then User should see Restaurant List
    When user select Restaurant
    Then Restaurant menu should get displayed
    When Search and adds the Item
    Then User should able to see cart created



