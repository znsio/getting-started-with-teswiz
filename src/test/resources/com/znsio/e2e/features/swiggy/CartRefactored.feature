@web
Feature: Verify the cart functionality for Swiggy

  @web @swiggy
  Scenario: Verify Guest User Can Perform Add and Delete Product operations In Cart
    Given User is at Home Page
    When User Searches For The Location "Mumbai" On Home Page
    And User Sorts The Restaurants By Ratings
    Then User Gets a List Of Restaurants
    #When User selects Restaurant at index "3"
    When User selects Restaurant named "GetAWhey - Healthy Ice Creams"
    And Adds food from "Recommended" category Into the Cart
    Then The food item should Be Added To The Cart
    When User Increases the Quantity of Food Items in Cart
    Then The Food Items Quantity Should Be Increased in Cart
    When User deletes the Food item from the Cart
    Then The Cart Should Be Empty
