package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonSearch;
import com.znsio.sample.e2e.screen.web.Amazon.AmazonSearchWeb;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


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



    public AmazonSearchBL searchProduct(String productName)
    {
        LOGGER.info(String.format("searchingInHomePage: '%s'", productName));
        boolean hasSearchCompleted=AmazonSearch.get().
                searchProductUsingSearchBar(productName).
                isSearchComplete();
        softly.assertThat(hasSearchCompleted).as("Searching Incomplete").isEqualTo(true);
        return this;

    }

    public AmazonSearchBL seeProductResults()
    {
        LOGGER.info(System.out.printf("viewing search results"));
        assertThat(AmazonSearchWeb.get().viewProductResultsAfterSearching()).
                as("Product Results doesnot contains related products").isEqualTo(true);
        return this;

    }


}
