@web
Feature: Verify the cart functionality for Swiggy

  Scenario : Verify Guest User Can Perform Add Product, Edit Product And Delete Product In Cart
    Given User is at Home Page
    When User Searches For The Location "Hitech City, Hyderabad" On Home Page
    Then User Gets a List Of Restaurants
    When User Sorts The Restaurants By Ratings
    Then User Get A List Of Restaurants
    When User Select A Restaurant
    And Adds Food Into the Cart
    Then The Food Should Be Added To The Cart
    When User Increases the Quantity of Food Items in Cart
    Then The Food Items Quantity Should Be Increased
    When User deletes the Food From the Cart
    Then The Cart Should Be Empty

  Scenario: Guest user is able to create cart
    Given User is on homepage and sets location to "Hitech City, Hyderabad, Telangana, India"
    Then User should see all
    When User sorts restaurants by ratings on Restaurant listing page
    And User selects "The Hunger stories" from Restaurant List
    And User adds two units of food items to cart
    Then Cart should have two food items added