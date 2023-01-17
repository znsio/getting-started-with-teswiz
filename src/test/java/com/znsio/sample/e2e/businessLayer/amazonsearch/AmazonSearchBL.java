package com.znsio.sample.e2e.businessLayer.amazonsearch;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonHomeScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonSearchBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonSearchBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public AmazonSearchBL(String userPersona, Platform onPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = onPlatform;
        Runner.setCurrentDriverForUser(userPersona, onPlatform, context);
    }

    public AmazonSearchBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonSearchBL searchForProduct(String productTitle) {
        int numberOfProductsFound = AmazonHomeScreen.get()
                .searchForProductInSearchBar(productTitle)
                .getNumberOfProductsFound();
        LOGGER.info(String.format("'%s' number of results displayed.", numberOfProductsFound));
        assertThat(numberOfProductsFound).as("Insufficient search results retrieved")
                .isGreaterThan(10);
        return this;
    }

    public AmazonSearchBL selectFirstItem() {
        AmazonHomeScreen.get().selectFirstItem();
        return this;
    }
}
