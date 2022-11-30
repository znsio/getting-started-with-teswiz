package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.indigo.GiftVoucherPreviewBL;
import com.znsio.sample.e2e.businessLayer.indigo.PayementDetailsBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.Map;

public class IndigoStep {

    private static final Logger LOGGER = Logger.getLogger(IndigoStep.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    private final Map userDetails;

    public IndigoStep() {

        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));

        String userSuffix=System.getProperty("user.name");
        userDetails = Runner.getTestDataAsMap(userSuffix);
    }


    @Given("I as a guest user,personalize and preview the gift voucher of any amount and quantity")
    public void iAsAGuestUserPersonalizeAndPreviewTheGiftVoucherOfAnyAmountAndQuantity() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new GiftVoucherPreviewBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).personalizeAndPreviewTheTotalBasedOnGivenDenominationAndQuantity(String.valueOf(userDetails.get("denomination")),
                String.valueOf(userDetails.get("quantity")));
    }

    @When("I apply the invalid promo code to check the status of total amount")
    public void iApplyTheInvalidPromoCodeToCheckTheStatusOfTotalAmount() {
        new GiftVoucherPreviewBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).setInvalidPromoCode();
    }

    @Then("I should be allowed to proceed to the payment gateway with original amount")
    public void iShouldBeAllowedToProceedToThePaymentGatewayWithOriginalAmount() {
        boolean status=new PayementDetailsBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).validatePaymentInformationForOriginalAmount();
        Assert.assertEquals(status,true,"User is not re-directed to payment gateway page with original amount");
        LOGGER.info("User is re-directed to payment gateway page with original amount");
    }
}
