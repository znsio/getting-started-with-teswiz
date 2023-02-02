package com.znsio.sample.e2e.businessLayer.amazonsearch;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.HomeScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchBL {

    private static final Logger LOGGER = Logger.getLogger(SearchBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public SearchBL(String userPersona, Platform onPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = onPlatform;
        Runner.setCurrentDriverForUser(userPersona, onPlatform, context);
    }

    public SearchBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public SearchBL searchForProduct(String productTitle) {
        int numberOfProductsFound = HomeScreen.get().searchForProductInSearchBar(productTitle).getNumberOfProductsFound();
        LOGGER.info(String.format("'%s' number of results displayed.", numberOfProductsFound));
        assertThat(numberOfProductsFound).as("Insufficient search results retrieved").isGreaterThan(0);
        return this;
    }

    public SearchBL selectItemFromResults() {
        LOGGER.info("Selecting the first result available");
        HomeScreen homeScreen = HomeScreen.get();
        homeScreen.selectFirstItem();
        homeScreen.changeToNewTab();
        return this;
    }
}
