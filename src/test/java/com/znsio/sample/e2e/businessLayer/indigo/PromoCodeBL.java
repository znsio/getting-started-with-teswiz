package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.indigo.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.IndigoVoucherDeliveryDetailsScreen;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;


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

    public GiftVoucherPurchaseBL applyInvalidPromoCode()
    {
        String promoCode = RandomStringUtils.randomAlphanumeric(6);
        String invalidVoucherMessage=IndigoVoucherDeliveryDetailsScreen.get().enterInvalidPromoCode(promoCode).getInvalidVoucherMessage();
        softly.assertThat(invalidVoucherMessage).as("Invalid promo code message not found").isEqualTo("Invalid Promo Code.");
        String amountAfterPromoCode = IndigoVoucherDeliveryDetailsScreen.get().fetchFinalAmount();
        softly.assertThat(amountAfterPromoCode).isEqualTo(context.getTestState(INDIGO_TEST_CONTEXT.TOTALVOUCHERAMOUNT).toString());
        return new GiftVoucherPurchaseBL();
    }
}