Feature: Creating a post on linkedIn

  @web @linkedin
  Scenario: Creating a post containing text and document
    Given I as a logged in user create a post containing text and document
    When I submit the post containing text and document
    Then the post containing text and document should be visible on user timeline

  @web @linkedin
  Scenario: Creating a post containing text and video
    Given I as a logged in user create a post containing text and video
    When I submit the post containing text and video
    Then the post containing text and video should be visible on user timeline

  @web @linkedin
  Scenario: Creating a post containing poll
    Given I as a logged in user create a post containing poll
    When I submit the post containing poll
    Then the post containing text and poll should be visible on user timeline

  @web @linkedin
  Scenario: Creating a post where only my connection people can see my post
    Given I as a logged in user create a post with view permission to my connections only
    When I submit the post containing text
    Then user2 in my connection can view my post

  @web @linkedin
  Scenario: Creating a post where only connection people can comment
    Given  I as a logged in user create a post with comment permission to my connections only
    When I submit the post containing text
    Then user2 in my connection can comment on my post



