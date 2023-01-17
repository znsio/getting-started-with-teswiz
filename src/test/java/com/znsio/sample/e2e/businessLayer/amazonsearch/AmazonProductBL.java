package com.znsio.sample.e2e.businessLayer.amazonsearch;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonProductScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class AmazonProductBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonProductBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public AmazonProductBL(String userPersona, Platform onPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = onPlatform;
        Runner.setCurrentDriverForUser(userPersona, onPlatform, context);
    }

    public AmazonProductBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonProductBL verifyProductDetails() {
        AmazonProductScreen.get().changeToNewTab().verifyProductDetails();
        return this;
    }

    public AmazonProductBL addToCart() {
        AmazonProductScreen.get().clickOnAddToCart();
        return this;
    }
}
