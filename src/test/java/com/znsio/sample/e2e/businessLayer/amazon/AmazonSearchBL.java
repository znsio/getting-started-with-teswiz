package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonSearchBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonSearchBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonSearchBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonSearchBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public AmazonSearchBL searchForProduct(String product) {
        AmazonSearchResultsScreen amazonSearchResultsScreen = AmazonHomeScreen.get()
                .search(product);
        String actualSearchWasFor = amazonSearchResultsScreen.getActualSearchString();
        assertThat(actualSearchWasFor).as("Search results are different from searched product").isEqualTo(product);
        return this;
    }

    public AmazonSearchBL selectFirstItem() {
        AmazonSearchResultsScreen amazonSearchResultsScreen = AmazonSearchResultsScreen.get()
                .clickOnFirstItem();
        assertThat(amazonSearchResultsScreen.getFirstItemName().contains(context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD))).as("First item is different from searched product").isTrue();
        return this;
    }
}
