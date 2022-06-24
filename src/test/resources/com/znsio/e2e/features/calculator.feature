@calculator
Feature: Calculator test

  @android
  Scenario: Calculations
    Given I start the calculator
    When I select "2"
    And I press "plus"
    When I select "5"
    And I press "equals"
    Then I should see "7"

  @android @single-device-android-test
  Scenario: Single device calculator practice
    Given "I" will start calculator
    When "I" will select "4"
    And "I" will press "multiply"
    And "I" will select "2"
    And "I" will press "equals"
    Then Screen should show "8"

  @android @multiuser-android @multiple-device-android-test
  Scenario: Single device calculator practice
    Given "I" will start calculator
    And "You" will start calculator
    When "I" will select "4"
    And "You" will select "5"
    And "I" will press "multiply"
    And "You" will press "plus"
    And "I" will select "2"
    And "You" will select "6"
    And "I" will press "equals"
    And "You" will press "equals"
    Then "I" should see "8" on screen
    Then "You" should see "11" on screen



  @multiuser-android
  Scenario: Verify 2 different calculator apps orchestration
    Given "I" start "Calculator2-Android"
    And "you" start "Calculator-Android"
    And "you" select "2"
    And "you" press "plus"
    And "I" select "5"
    And "you" press "plus"