@web
Feature: Verify the cart functionality for Swiggy

  @web @swiggy_refact
  Scenario: Verify Guest User Can Perform Add and Delete Product operations In Cart
    Given User is at Home Page
    When User Searches For The Location "Mumbai" On Home Page
    And User Sorts The Restaurants By Ratings
    Then User Gets a List Of Restaurants
    #When User selects Restaurant at index "3"
    When User selects Restaurant named "The Quick Bites"
    And Adds food from "Recommended" category Into the Cart
    Then The food item should Be Added To The Cart
    When User Increases the Quantity of Food Items in Cart
    Then The Food Items Quantity Should Be Increased in Cart
    When User deletes the Food item from the Cart
    Then The Cart Should Be Empty


  @web @swiggy_review
  Scenario: Verify Guest User can add and remove products from cart
    Given User is on Home Page
    When User sets location to
        |location|
        |Mumbai|
    Then User should see restaurants available for selected location
    When User sorts the restaurants by ratings
    Then User gets Restaurant list sorted by ratings
    When User selects a restaurant
    And User Adds food item in the cart
    Then Food items should Be Added to the Cart
    When User increments the Quantity of food items in cart
    Then Food items Quantity Should Be Increased in cart
    When User deletes the food item from the cart
    Then The cart should be Empty
