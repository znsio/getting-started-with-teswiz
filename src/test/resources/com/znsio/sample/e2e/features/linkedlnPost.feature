@linkedin @prod
Feature: LinkedIn Post

  @web @multi-user
  Scenario: As a registered user, I should be able to create post on LinkedIn

    Given "I" am on LinkedIn homepage
    When "I" create a "Image" post on LinkedIn
    Then "I" should be able to see the post on my post feed
    And "You", one of my connections, should also be able to see the post on your post feed
    And "You" should be able to react with "comment" on my post

