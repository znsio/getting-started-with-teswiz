package com.znsio.sample.e2e.businessLayer.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.Indigo.IndigoPromoCodeScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

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

    public PaymentScreen purchaseGiftVoucher() {
        String receiverFirstName = RandomStringUtils.random(6,true,false);
        String recoverLastName = RandomStringUtils.random(6,true,false);
        String receiverMail = RandomStringUtils.random(5,true,false)+"@gmail.com";
        String receiverPhone = RandomStringUtils.randomNumeric(10);
        String senderFirstName = RandomStringUtils.random(6,true,false);
        String senderLastName = RandomStringUtils.random(6,true,false);
        String senderrMail = RandomStringUtils.random(5,true,false)+"@gmail.com";
        String senderPhone = RandomStringUtils.randomNumeric(10);
        IndigoPromoCodeScreen.get()
                .enterReceiverDetail(receiverFirstName,recoverLastName,receiverMail,receiverPhone)
                .enterSenderDetails(senderFirstName,senderLastName,senderrMail,senderPhone);
                .selectTermsAndConditions();
    }
}
