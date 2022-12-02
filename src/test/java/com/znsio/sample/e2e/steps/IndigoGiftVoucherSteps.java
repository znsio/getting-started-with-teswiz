package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.indigo.BuyGiftVoucherBL;
import com.znsio.sample.e2e.businessLayer.indigo.IndigoGiftVoucherBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class IndigoGiftVoucherSteps {
    private static final Logger LOGGER = Logger.getLogger(JioMeetSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;
    public IndigoGiftVoucherSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }
    public IndigoGiftVoucherSteps(TestExecutionContext context, Drivers allDrivers, TestExecutionContext context1, Drivers allDrivers1) {
        this.context = context;
        this.allDrivers = allDrivers;
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }
    @Given("I, as a Guest user, personalize and preview {string} gift vouchers of price {string}")
    public void iPersonaliseAndPreviewAGiftVoucherWithDenominationAndQuantity(String quantity, String denomination) {
        LOGGER.info("Setting up Driver");
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new IndigoGiftVoucherBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).personaliseAndPreviewVoucher(denomination, quantity);
    }
    @When("I apply invalid promo code")
    public void iApplyInvalidPromoCode() {
        LOGGER.info("Applying Promocode");
        new BuyGiftVoucherBL().applyInvalidPromoCode();
    }
    @Then("I can proceed for the payment at the original amount")
    public void iCanPurchaseTheGiftVoucherAtTheOriginalAmount() {
        LOGGER.info("Proceeding to Payment");
        new BuyGiftVoucherBL().selectDeliveryOptionsAndPay();
    }
}
