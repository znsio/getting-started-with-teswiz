package com.znsio.sample.e2e.businessLayer.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.Indigo.IndigoPaymentScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoDeliveryScreen;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseVoucherBL {

    private static final Logger LOGGER = Logger.getLogger(PurchaseVoucherBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;

    public PurchaseVoucherBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public PurchaseVoucherBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }

    public IndigoPaymentScreen purchaseGiftVoucher() {
        String receiverFirstName = RandomStringUtils.randomAlphabetic(6);
        String recoverLastName = RandomStringUtils.randomAlphabetic(6);
        String receiverMail = RandomStringUtils.randomAlphabetic(5) + "@gmail.com";
        String receiverPhone = RandomStringUtils.randomNumeric(10);
        String senderFirstName = RandomStringUtils.randomAlphabetic(6);
        String senderLastName = RandomStringUtils.randomAlphabetic(6);
        String senderrMail = RandomStringUtils.randomAlphabetic(5) + "@gmail.com";
        String senderPhone = RandomStringUtils.randomNumeric(10);
        boolean isPaymentPageVisible = IndigoDeliveryScreen.get()
                .enterReceiverDetail(receiverFirstName, recoverLastName, receiverMail, receiverPhone)
                .enterSenderDetails(senderFirstName, senderLastName, senderrMail, senderPhone)
                .selectTermsAndConditions()
                .clickOnProceedBtn()
                .validatePaymentScreen();
        assertThat(isPaymentPageVisible).as("Payment Page is not Visisble").isTrue();
        return IndigoPaymentScreen.get();
    }
}
