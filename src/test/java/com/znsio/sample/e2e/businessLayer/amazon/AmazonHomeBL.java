package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonNavigationScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AmazonHomeBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonHomeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonHomeBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonHomeBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonHomeBL validateUserIsOnTheHomePage(){
        boolean isUserOnHomePage=AmazonHomeScreen.get().isUserOnHomePage();
        assertThat(isUserOnHomePage).as("User is not on Home Page").isTrue();
        AmazonNavigationScreen.get();
        return this;
    }

    public AmazonSearchResultBL UserSearchsForProduct(String productName) {
        AmazonNavigationScreen.get().iSearchForProduct(productName);
        return new AmazonSearchResultBL();
    }

    public AmazonHomeBL UserGoesToTheCart(){
        new AmazonCartBL().validateUserOnCartPage().validateAddedProductIsFoundInCart();
        return this;
    }
}
