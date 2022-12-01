package com.znsio.sample.e2e.businessLayer.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.web.PaymentScreenWeb;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentBL {
    private static final Logger LOGGER = Logger.getLogger(PaymentBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public PaymentBL(String userPersona, Platform forPlatform){
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public PaymentBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public PaymentBL proceedToPayment(){
        LOGGER.info("On Payment screen");
        String expectedPaymentScreenHeading = "Payment Information";
        String paymentHeading = PaymentScreenWeb.get().getPaymentPageHeading();
        assertThat(paymentHeading).as("Invalid promocode message should be 'Invalid Promo Code.'").isEqualTo(expectedPaymentScreenHeading);
        return this;
    }
}
