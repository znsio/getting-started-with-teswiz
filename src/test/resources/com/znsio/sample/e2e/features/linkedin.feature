@linkedin @prod
Feature: Creating a linkedin post

#  CONFIG=./configs/linkedin_local_config.properties TAG="@linkedin" PLATFORM=web ./gradlew run
@web
Scenario: User2 should be able to view a post, created by User1 on linkedin
  Given "User1" prepare a "text" post on linkedin
  When "User1" updates with the post
  Then Both "User1" and "User2" should be able to see the post
  When "User2" comments on the post
  Then "User1" should be able to see the comment

