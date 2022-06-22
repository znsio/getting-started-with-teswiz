package com.znsio.e2e.businessLayer.amazoncartvalidation;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.amazoncartvalidation.SearchResultsListScreen;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultsListBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public SearchResultsListBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public SearchResultsListBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }


    public SearchResultsListBL verifySearchResult(String productName) {
        boolean isSearchedProductDisplayed=SearchResultsListScreen.get().isSearchedProductDisplayed(productName);
        assertThat(isSearchedProductDisplayed).as(String.format("Searched product: '%s' is not present in search result list",productName)).isTrue();
        return this;
    }

    public ProductDetailsBL selectTheFirstProduct() {
        SearchResultsListScreen.get().selectFirstProduct();
        return new ProductDetailsBL();
    }
}
