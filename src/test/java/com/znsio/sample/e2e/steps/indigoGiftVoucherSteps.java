package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.Indigo.GiftVoucherBL;
import com.znsio.sample.e2e.businessLayer.Indigo.PaymentBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class indigoGiftVoucherSteps {

    private static final Logger LOGGER = Logger.getLogger(indigoGiftVoucherSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public indigoGiftVoucherSteps(){
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I as a guest user personalise and preview {string} gift vouchers of {string} each")
    public void i_as_a_user_personalise_and_preview_gift_vouchers_of_each(String quantity, String amount) {
        LOGGER.info(System.out.printf("User Type as a - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new GiftVoucherBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).personaliseAndPreviewGiftVoucher(quantity, amount);
    }

    @When("I apply Invalid promo code")
    public void i_apply_invalid_promo_code() {
        LOGGER.info("User is applying invalid promo-code");
        new GiftVoucherBL().enterInvalidPromoCode();
    }

    @Then("I can complete the payment with non discounted amount")
    public void i_can_complete_the_payment_with_non_discounted_amount() {
        LOGGER.info("User is on payment screen");
        new PaymentBL().proceedToPayment();
    }
}
