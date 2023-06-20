@vodqa
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