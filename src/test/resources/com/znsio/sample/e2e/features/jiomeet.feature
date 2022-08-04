@jiomeet @prod @inMeeting
Feature: In a meeting scenarios

  @android @web
  Scenario: User should be able to change the mic settings
    Given I sign in as a registered "user1"
    And I start an instant meeting
    When I "Unmute" myself
    Then I should be able to "Mute" myself