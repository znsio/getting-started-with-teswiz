package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class AmazonHomepageBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonHomepageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonHomepageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonHomepageBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public AmazonHomepageBL searchForProduct(String product) {
        AmazonSearchResultsScreen amazonSearchResultsScreen = AmazonHomeScreen.get().searchProductUsingAmazonSearchBar(product);

        String actualSearchWasFor = amazonSearchResultsScreen.getActualSearchString();
        softly.assertThat(actualSearchWasFor).as("Search was for a different value").isEqualTo(product);

        int numberOfProductsFound = amazonSearchResultsScreen.getNumberOfProductsFound();
        softly.assertThat(numberOfProductsFound).as("Insufficient search results retrieved").isGreaterThan(100);
        return this;
    }

    public AmazonHomepageBL selectFirstProductInSearchResultsList() {
        AmazonSearchResultsScreen amazonSearchResultsScreen = AmazonSearchResultsScreen.get();
        amazonSearchResultsScreen.clickOnFirstProductInSearchResultsList();
        return this;
    }

}
