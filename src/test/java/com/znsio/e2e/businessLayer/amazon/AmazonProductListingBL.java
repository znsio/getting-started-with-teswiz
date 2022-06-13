package com.znsio.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.amazon.AmazonScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class AmazonProductListingBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonProductListingBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonProductListingBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }
    public AmazonProductListingBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonProductListingBL verifySearchedResults(){
        AmazonScreen.get().verifySearchedResults();
        return this;
    }

    public AmazonProductListingBL selectAnyProductFromListingPage(){
        AmazonScreen.get().selectAnyProductFromListingPage();
        return this;
    }
}
