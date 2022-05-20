Feature: Verify add to cart

  Scenario: To verify user is able to create cart
    Given User is on Home Page
    When user adds the "location"
    Then user will see Restaurants List for the "Location"
    When User sort the Restaurant List by Rating
    Then User should see sorted Restaurant List
    When user select "Restaurant"
    Then "Restaurant" menu should get displayed
    When adds the "Item"
    Then User should able to see cart created



