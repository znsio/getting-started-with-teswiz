@prod
Feature: Ajio operator flow

  Background:
    Given I login in to seller portal as "operator"

    # CONFIG=./configs/ajioOperator_local_config.properties TAG="@op_verifyActionItems" PLATFORM=web ./gradlew run
  @op_verifyActionItems @web
  Scenario Outline: Operator should able to see the Action items for the search seller email
    And Operator should enable the "PENDING" status on status filter
    When Operator should search a seller email on the EmailId search filed
    Then Operator should be able to see the "<Action items>" for searched seller email

    Examples:
      | Action items        |
      | Seller Registration |
      | Approval Status     |

    # CONFIG=./configs/ajioOperator_local_config.properties TAG="@op_createNewSeller" PLATFORM=web ./gradlew run
  @op_createNewSeller @web
  Scenario: As an operator i am able to pre Register a new JIT Fulfillment Type of standard EAN with B2C business type
    When Operator create a seller of "JIT" fulfilment and "standard" EAN type for "B2C" business
    Then Operator able to see the seller status "Register" in Manage Sellers