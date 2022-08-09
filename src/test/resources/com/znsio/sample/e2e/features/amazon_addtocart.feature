@web
Feature: add to cart functionality
  @web @amazon @debug
  Scenario: user can add product to cart
     Given user searches for "iPhone 13" product on amazon landing page
     When user selects first product from search results
     And user adds product into cart
     Then added product should be visible into cart