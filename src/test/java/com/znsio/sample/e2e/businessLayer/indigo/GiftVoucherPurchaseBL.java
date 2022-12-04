package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.indigo.IndigoVoucherDeliveryDetailsScreen;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;


public class GiftVoucherPurchaseBL {

    private static final Logger LOGGER = Logger.getLogger(GiftVoucherPurchaseBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;

    public GiftVoucherPurchaseBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public GiftVoucherPurchaseBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }

    public IndigoVoucherDeliveryDetailsScreen giftVoucherPurchase()
    {
        LOGGER.info("Purchasing gift voucher");
        String senderFirstName = RandomStringUtils.randomAlphabetic(5);
        String senderLastName = RandomStringUtils.randomAlphabetic(6);
        String senderMail = RandomStringUtils.randomAlphabetic(4) + "@gmail.com";
        String senderPhone = RandomStringUtils.randomNumeric(10);
        IndigoVoucherDeliveryDetailsScreen.get().enterSenderDetails(senderFirstName,senderLastName,senderMail,senderPhone).checkTermsAndConditions().proceedToPayment();

        return IndigoVoucherDeliveryDetailsScreen.get();
    }
}
