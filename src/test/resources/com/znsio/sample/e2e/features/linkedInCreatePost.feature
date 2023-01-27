@linkedIn @prod
Feature: Create a post on linkedIn

  @multiuser-android-web
  Scenario: LinkedIn User should be able to create a post
    Given "I" am a linkedIn user and "You" are one of my connections
    When "I" create a "blog" post with "Connections Only" visibility
    Then "I" should see the post
    And "You" should see the post
    When "You" add a comment on the post
    Then "I" should see the comment on the post