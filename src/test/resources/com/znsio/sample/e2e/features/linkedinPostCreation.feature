@linkedinpost @prod
  Feature: Test scenario for creating a LinkedIn post
  @multiuser-web
    Scenario Outline: User-one can create a new post and the post is visible to User-two
    Given User-one makes a new "Text" post visible to "Anyone"
    Then User-one can see a post on personal feed
    And User-two, a connection,can see a post
    When User-two, provides a "<reaction>" on the post
    Then User-one can see a "<reaction>" on post
    Examples:
      | reaction |
      |Like      |
      |Celebrate |
      |Love      |
      |Support   |
      |Funny     |
