package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.amazon.AmazonLandingScreen;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonLandingBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonLandingBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;

        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public SearchResultsBL search(String productName) {
        boolean isSearchedResultsDisplayed = AmazonLandingScreen.get()
                .search(productName).
                isSearchedResultsDisplayed(productName);

        assertThat(isSearchedResultsDisplayed)
                .as(String.format("productName '%s' is not present in the search results", productName))
                .isTrue();
        return new SearchResultsBL();
    }
}
