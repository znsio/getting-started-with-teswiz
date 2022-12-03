package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;

public class BuyGiftVoucherBL {
    private static final Logger LOGGER = Logger.getLogger(IndigoGiftVoucherBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public BuyGiftVoucherBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }
    public BuyGiftVoucherBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }
    public void applyInvalidPromoCode() {
        String promoCode = RandomStringUtils.randomAlphabetic(5);
        LOGGER.info("Apply Invalid PromoCode");
        softly.assertThat(GiftVoucherScreen.get().applyPromoCode(promoCode)).isTrue();
    }
    public void selectDeliveryOptionsAndPay() {
        LOGGER.info("Select Delivery Info");
        boolean landedOnPaymentScreen = GiftVoucherScreen.get().giveDeliveryOptions().proceedToPaymentPage().landOnPaymentPage();
        assertThat(landedOnPaymentScreen).as("Unable to navigate to Payment page").isTrue();
    }
}

