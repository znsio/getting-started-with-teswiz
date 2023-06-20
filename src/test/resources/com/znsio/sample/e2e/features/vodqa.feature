@vodqa
  #  CONFIG=./configs/vodqa_local_config.properties PLATFORM=android TAG=vodqa ./gradlew run
Feature: Vodqa test

  #  CONFIG=./configs/vodqa_local_config.properties PLATFORM=android TAG=appInBackground ./gradlew run
  @android @appInBackground @test
  Scenario: Put app in background
    Given I login to vodqa application using valid credentials
    Then App should work in background for 5 sec

  #  CONFIG=./configs/vodqa_local_config.properties PLATFORM=android TAG=vodqaContextSwitch ./gradlew run
  @vodqaContextSwitch @android
  Scenario: Validate context switching between native and web view context
    Given I login to vodqa application using valid credentials
    Then I am able to view hacker news login button inside web view section
    And I am able to view section header by navigating inside native view section

  #  CONFIG=./configs/vodqa_local_config.properties PLATFORM=android TAG=scrollUsing2Points ./gradlew run
  @android @scrollUsing2Points
  Scenario: Validating scroll functionality using 2 points
    Given I login to vodqa application using valid credentials
    When I scroll from one to another element point on vertical swiping screen
    Then Element text "Jasmine" should be visible

    #  CONFIG=./configs/vodqa_local_config.properties PLATFORM=android TAG=tapInTheMiddleOfTheScreen ./gradlew run
  @tapInTheMiddleOfTheScreen @android
  Scenario: User tap in the middle of the screen
    Given I login to vodqa application using valid credentials
    When I tap in the middle of the screen
    Then I am able to move from "Samples List" page to next page