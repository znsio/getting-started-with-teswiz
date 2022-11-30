package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.indigo.GiftVoucherBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class IndigoFlightSteps {

    private static final Logger LOGGER = Logger.getLogger(IndigoFlightSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public IndigoFlightSteps() {

        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Then("I should able to purchase the ticket of {string} rupees")
    public void iShouldAbleToPurchaseTheTicketOfRupees(String amount) {

        LOGGER.info("Process Payment");
        new GiftVoucherBL().processPayment(amount);
    }

    @Given("I as a guest user, preview and personalise {string} gift vouchers of {string} rupees")
    public void iAsAGuestUserPreviewAndPersonaliseTheGiftVouchersOfRupees(String quantity, String denominations) {
        LOGGER.info("Preview and Personalise Gift Voucher");
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new GiftVoucherBL().previewAndPersonaliseGiftVoucher(quantity, denominations);
    }

    @When("I apply a {string} promo code")
    public void iApplyAPromoCode(String promoCode) {
        LOGGER.info("Apply Promo code with delivery details");
        new GiftVoucherBL().applyAPromoCode(promoCode).withDeliveryOptions();
    }
}
