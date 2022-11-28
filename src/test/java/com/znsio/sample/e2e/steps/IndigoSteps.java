package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.Indigo.PromoCodeBL;
import com.znsio.sample.e2e.businessLayer.Indigo.PurchaseVoucherBL;
import com.znsio.sample.e2e.businessLayer.Indigo.VoucherBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class IndigoSteps {

    private static final Logger LOGGER = Logger.getLogger(IndigoSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public IndigoSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I, a guest user, personalise and preview a gift voucher with any amount and quantity")
    public void iAGuestUserPersonaliseAndPreviewAGiftVoucherWithAnyAmountAndQuantity() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new VoucherBL().personaliseAndPreviewGiftVoucher();
    }

    @When("I apply invalid promo code")
    public void iApplyInvalidPromoCode() {
        new PromoCodeBL().applyInvalidPromoCode();
    }

    @Then("I can purchase the gift voucher at the original amount")
    public void iCanPurchaseTheGiftVoucherAtTheOriginalAmount() {
  //      new PurchaseVoucherBL().purchaseGiftVoucher();
    }
}
