package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.indigo.VoucherBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class IndigoGiftVoucherSteps {
    private static final Logger LOGGER = Logger.getLogger(IndigoGiftVoucherSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public IndigoGiftVoucherSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I personalize the Indigo {string} Gift Voucher of {string} worth.")
    public void iPersonalizeTheIndigoGiftVoucherOfWorth(String quantity, String amount) {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new VoucherBL().personalizeGiftVoucher(quantity,amount);


    }

    @When("User applies invalid promocode")
    public void userAppliesInvalidPromocode() {
    new VoucherBL().applyPromoCode();
    }

    @Then("User should be able to proceed with orignal amount.")
    public void userShouldBeAbleToProceedWithOrignalAmount() {
        new VoucherBL().validatePurchase();
    }


}
