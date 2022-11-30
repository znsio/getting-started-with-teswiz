package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.indigo.IndigoVoucherBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;

public class IndigoSteps {

    private static final Logger LOGGER = Logger.getLogger(IndigoSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public IndigoSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("User personalize and preview the Gift Voucher with any amount {string} and quantity {string}")
    public void userPersonalizeAndPreviewTheGiftVoucherWithAnyAmountAndQuantity(String amount, String quantity) {
        LOGGER.info(System.out.printf("Personalising voucher", SAMPLE_TEST_CONTEXT.ME));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new IndigoVoucherBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).personalize(amount, quantity);
    }

    @And("User enters invalid promo code")
    public void userEntersInvalidPromoCode() {
        LOGGER.info(System.out.printf("Entering invalid promo code", SAMPLE_TEST_CONTEXT.ME));
        new IndigoVoucherBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).enterInvalidCode();
    }

    @Then("User should complete the payment process at the original amount {string}")
    public void userShouldCompleteThePaymentProcessAtTheOriginalAmount(String amount) {
        LOGGER.info(System.out.printf("Completing payment process", SAMPLE_TEST_CONTEXT.ME));
        new IndigoVoucherBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).proceedToPayment(amount);
    }
}
