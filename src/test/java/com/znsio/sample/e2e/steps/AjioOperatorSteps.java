package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.ajioOperator.OperatorLoginBL;
import com.znsio.sample.e2e.businessLayer.ajioOperator.OperatorManageSellerBL;
import com.znsio.sample.e2e.businessLayer.ajioOperator.OperatorPreRegisterSellerBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;
import java.util.logging.Logger;

public class AjioOperatorSteps {
    private static final Logger LOGGER = Logger.getLogger(AjioOperatorSteps.class.getName());

    private final TestExecutionContext context;
    public AjioOperatorSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
    }

    @Given( "I login in to seller portal as {string}" )
    public void iLoginInToSellerPortalAs(String userType) {
        Drivers.createDriverFor( SAMPLE_TEST_CONTEXT.SELLER_PLATFORM_USER, Runner.getPlatform(), context);
        Map<String, Object> testData = Runner.getTestDataAsMap(userType);
        new OperatorLoginBL().operatorLoggedOnSellerCentralPage(String.valueOf(testData.get("EMAIL")), String.valueOf(testData.get("PASSWORD")));
        LOGGER.info("operator logged in seller portal successfully");
    }

    @When( "Operator should search a seller email on the EmailId search filed" )
    public void operatorShouldSearchASellerEmailOnTheEmailIdSearchFiled() {
        LOGGER.info("operator land on seller-central page");
        new OperatorManageSellerBL().searchByEmailAddressOnEmailIdFilter();
    }
    @And( "Operator should enable the {string} status on status filter" )
    public void operatorShouldEnableTheStatusOnStatusFilter(String statusItems) {
        new OperatorManageSellerBL().enableThePendingStatusOnStatusFilter(statusItems);
        LOGGER.info("Pending status is enabled on Status Filter");
    }

    @Then( "Operator should be able to see the {string} for searched seller email" )
    public void operatorShouldBeAbleToSeeTheForSearchedSellerEmail(String actionItems) {
        new OperatorManageSellerBL().verifyActionItemsForSearchEmail(actionItems);
        LOGGER.info("Action items is displayed for search seller email Address");
    }

    @When( "Operator create a seller of {string} fulfilment and {string} EAN type for {string} business" )
    public void operatorCreateASellerOfFulfilmentAndEANTypeForBusiness(String fulfilmentType , String EANType, String businessType) throws InterruptedException {
        new OperatorPreRegisterSellerBL().createPreRegisterSeller(fulfilmentType,EANType,businessType);
        LOGGER.info("Operator successfully created new PreRegisterSeller ");
    }

    @Then( "Operator able to see the seller status {string} in Manage Sellers" )
    public void operatorAbleToSeeTheSellerStatusInManageSellers(String status) throws InterruptedException {
        LOGGER.info("Operator on manage seller screen");
        new OperatorManageSellerBL().verifyStatusOfCreatedSellerOnManageSellerPage(status);
    }
}
