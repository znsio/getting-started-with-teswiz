@vodqa
Feature: Vodqa test

  #  CONFIG=./configs/vodqa_local_config.properties PLATFORM=android TAG=appInBackground ./gradlew run
  @android @appInBackground @test
  Scenario: Put app in background
    Given I login to vodqa application using valid credentials
    Then App should work in background for 5 sec