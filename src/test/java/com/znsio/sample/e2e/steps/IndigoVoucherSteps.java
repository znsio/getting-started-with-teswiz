package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.indigo.GiftVoucherBL;
import com.znsio.sample.e2e.entities.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class IndigoVoucherSteps {
    private static final Logger LOGGER = Logger.getLogger(IndigoVoucherSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public IndigoVoucherSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I, a guest user, personalize and preview gift vouchers with any denomination and quantity")
    public void iAGuestUserPersonalizeAndPreviewGiftVouchersWithAnyDenominationAndQuantity() {
        allDrivers.createDriverFor(INDIGO_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        LOGGER.info(System.out.printf("User Personalises Gift Voucher with - Persona:'%s', and Platform: '%s'", INDIGO_TEST_CONTEXT.GUEST_USER, Runner.platform));
        new GiftVoucherBL(INDIGO_TEST_CONTEXT.GUEST_USER, Runner.platform).personaliseAndPreviewGiftVoucher();
    }
    @When("I tried applying a promo code comes invalid")
    public void iTriedApplyingAPromoCodeComesInvalid() {
        LOGGER.info(System.out.printf("'%s' User is applying promo code", INDIGO_TEST_CONTEXT.GUEST_USER));
        new GiftVoucherBL(INDIGO_TEST_CONTEXT.GUEST_USER, Runner.platform).applyPromoCode(context.getTestState(INDIGO_TEST_CONTEXT.PROMO_CODES).toString());
    }
    @Then("I should be able to proceed to payment page with original amount")
    public void iShouldBeAbleToProceedToPaymentPageWithOriginalAmount() {
        LOGGER.info("User is on payment screen");
        new GiftVoucherBL(INDIGO_TEST_CONTEXT.GUEST_USER, Runner.platform).fillTheDetailsAndProceedToPayment();
    }
}
