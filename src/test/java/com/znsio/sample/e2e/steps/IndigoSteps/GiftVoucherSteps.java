package com.znsio.sample.e2e.steps.IndigoSteps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.indigo.GiftVoucherPurchaseBL;
import com.znsio.sample.e2e.businessLayer.indigo.IndigoGiftVoucherBL;
import com.znsio.sample.e2e.businessLayer.indigo.PromoCodeBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class GiftVoucherSteps {

    private static final Logger LOGGER = Logger.getLogger(GiftVoucherSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public GiftVoucherSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I, as a guest user personalise and preview Indigo gift voucher with denomination {string} and quantity {string}")
    public void iAsAGuestUserPersonaliseAndPreviewIndigoGiftVoucherWithDenominationAndQuantity(String voucherDenomination, String quantity) {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        LOGGER.info(System.out.printf("iAsAGuestUserPersonaliseAndPreviewIndigoGiftVoucherWithDenominationAndQuantity - Persona: '%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.platform));
        new IndigoGiftVoucherBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).personaliseAndPreviewVoucherWithAmount(voucherDenomination,quantity);
    }


    @When("I apply an invalid promo code")
    public void iApplyAnInvalidPromoCode()
    {
        LOGGER.info("Applying invalid promo code");
        new PromoCodeBL().applyInvalidPromoCode();
    }

    @Then("I should be able to make the purchase of the gift voucher at the original amount")
    public void iShouldBeAbleToMakeThePurchaseOfTheGiftVoucherAtTheOriginalAmount()
    {
        LOGGER.info("Purchase gift voucher");
        new GiftVoucherPurchaseBL().giftVoucherPurchase();
    }
}
