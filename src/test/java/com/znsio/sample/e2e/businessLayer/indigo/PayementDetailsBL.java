package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;

public class PayementDetailsBL {

    private static final Logger LOGGER = Logger.getLogger(PayementDetailsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public PayementDetailsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public PayementDetailsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public void validatePaymentInformationForOriginalAmount(){
     boolean paymentInfoStatus= new GiftVoucherPreviewBL().enterReceiverAndSenderDetailsAndProceed().checkingUserPaymentInformation();
     Assert.assertEquals(paymentInfoStatus,true,"User is not re-directed to payment gateway page with original amount");
    }
}
