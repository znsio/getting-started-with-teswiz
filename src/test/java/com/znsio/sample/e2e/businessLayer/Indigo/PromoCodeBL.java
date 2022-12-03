package com.znsio.sample.e2e.businessLayer.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.Indigo.IndigoDeliveryScreen;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class PromoCodeBL {

    private static final Logger LOGGER = Logger.getLogger(PromoCodeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;

    public PromoCodeBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public PromoCodeBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }

    public PurchaseVoucherBL applyInvalidPromoCode() {
        String promoCode = RandomStringUtils.randomAlphanumeric(10);
        IndigoDeliveryScreen indigoDeliveryScreen = IndigoDeliveryScreen.get();
        String isErrorMessageAppeared =  indigoDeliveryScreen
                .enterInvalidPromoCode(promoCode)
                .getErrorMessage();
        softly.assertThat(isErrorMessageAppeared).as("PromoCode error message not appears on Delivery Screen").isEqualTo("Invalid Promo Code.");
        int paymentAmountAfterPromoCode = indigoDeliveryScreen
                .getFinalAmount();
        assertThat(paymentAmountAfterPromoCode).as("Price of Gift Voucher after applying PromoCode is different").isEqualTo((context.getTestState(INDIGO_TEST_CONTEXT.TOTALAMOUNT)));
        return new PurchaseVoucherBL();
    }
}