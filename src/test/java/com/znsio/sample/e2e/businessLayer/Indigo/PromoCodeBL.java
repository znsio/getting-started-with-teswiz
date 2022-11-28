package com.znsio.sample.e2e.businessLayer.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
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

    public void applyInvalidPromoCode() {
    }
}
