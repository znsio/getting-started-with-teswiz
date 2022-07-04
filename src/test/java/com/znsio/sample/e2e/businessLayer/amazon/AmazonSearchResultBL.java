package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.SearchResultScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AmazonSearchResultBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonSearchResultBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonSearchResultBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonSearchResultBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    private AmazonSearchResultBL validateUserOnSearchResultPage() {
        boolean isUserOnSearchPage=SearchResultScreen.get().isUserOnSearchResultPage();
        assertThat(isUserOnSearchPage).as("User is not on search result page").isTrue();
        return this;
    }

    private AmazonSearchResultBL validateSearchResultNotEmpty() {
        boolean isSearchResultEmpty=SearchResultScreen.get().isSearchResultEmpty();
        assertThat(isSearchResultEmpty).as("the search result is empty").isFalse();
        return this;
    }

    public AmazonProductDetailsBL userSelectsFirstProductOnSearchResultPage() {
        this.validateUserOnSearchResultPage().validateSearchResultNotEmpty();
        SearchResultScreen.get().iClickTheFirstProductInSearchResult();
        return new AmazonProductDetailsBL();

    }
}
