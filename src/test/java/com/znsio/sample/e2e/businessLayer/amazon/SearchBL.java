package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.HomeScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchBL {

    private static final Logger LOGGER = Logger.getLogger(SearchBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public SearchBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public SearchBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public SearchBL searchForProduct(String product) {
        Boolean doSearchResultsMatchSearchedProduct = HomeScreen.get()
                .search(product).matchTopResultsWithSearchedString(product);
        context.addTestState(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD, product);
        assertThat(doSearchResultsMatchSearchedProduct).as("Search results are different from searched product").isTrue();
        return this;
    }

    public SearchBL selectFirstProduct() {
        SearchResultsScreen searchResultsScreen = SearchResultsScreen.get();
        assertThat(searchResultsScreen.getFirstProductName().contains(context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD))).as("First item is different from searched product").isTrue();
        searchResultsScreen.clickOnFirstProduct();
        return this;
    }
}
