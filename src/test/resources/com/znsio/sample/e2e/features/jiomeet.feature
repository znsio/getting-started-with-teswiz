@jiomeet @prod @inMeeting
Feature: In a meeting scenarios

  @android @web
  Scenario: User should be able to change the mic settings
    Given I sign in as a registered "user1"
    And I start an instant meeting
    When I Unmute myself
    Then I should be able to Mute myself

  @multiuser-android-web @wip
  Scenario: Guest (on Web) and host (on Android) can chat in a meeting
    Given "Host" logs-in and starts an instant meeting on "android"
    And "Guest" joins the meeting from "web"
    Then "Host" should be able to get to chat window
    When "Host" sends "Hey" chat message
    Then "Guest" should see the chat message on its chat window

  @multiuser-android
  Scenario: Guest (on android) and host (on Android) can chat in a meeting
    Given "user1" logs-in and starts an instant meeting on "android"
    And "Guest" joins the meeting from "android"
    Then "Host" should be able to get to chat window
    When "Host" sends "Hey" chat message
    Then "Guest" should see the chat message on its chat window

  @multiuser-android @wip
  Scenario: Guest (on android) and host (on Android) can chat in a meeting
    Given "Host" logs-in and starts an instant meeting on "android"
    And "Guest" joins the meeting from "android-oldversion"
    Then "Host" should be able to get to chat window
    When "Host" sends "Hey" chat message
    Then "Guest" should see the chat message on its chat window