@jiomeet @prod @inMeeting
Feature: In a meeting scenarios

  @android @single-user
  Scenario: User should be able to change the mic settings
    Given I sign in as a registered "Host"
    And I start an instant meeting
    When I Unmute myself
    Then I should be able to Mute myself

  @multiuser-android-web
  Scenario: Guest (on Web) and host (on Android) can chat in a meeting
    Given "Host" logs-in and starts an instant meeting on "android"
    And "Guest" joins the meeting from "web"
    Then "Host" should be able to get to chat window
    When "Host" sends "Hey" chat message
    Then "Guest" should see the chat message on its chat window

  @multiuser-android @single-app
  Scenario: Guest (on android) and host (on Android) can chat in a meeting
    Given "Host" logs-in and starts an instant meeting on "android"
    And "Guest" joins the meeting from "android"
    Then "Host" should be able to get to chat window
    When "Host" sends "Hey" chat message
    Then "Guest" should see the chat message on its chat window

  @multiuser-android @multi-app
  Scenario: Guest (on android) and host (on Android) can chat in a meeting
    Given "Host" logs-in and starts an instant meeting in "jiomeetLatest" on "android"
    And "Guest" joins the meeting from "jiomeetOldVersion" on "android"
    Then "Host" should be able to get to chat window
    When "Host" sends "Hey" chat message
    Then "Guest" should see the chat message on its chat window