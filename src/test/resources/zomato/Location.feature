Feature: :Location disable functionality

  @zomato @location @web
  Scenario: Detect current location disabled within system
    Given I, as "guest user" on zomato homepage and select detect location
    Then I should see location disabled message