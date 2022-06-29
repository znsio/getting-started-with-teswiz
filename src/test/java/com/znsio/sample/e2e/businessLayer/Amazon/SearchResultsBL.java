package com.znsio.sample.e2e.businessLayer.Amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.businessLayer.theapp.AppBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.AmazonCart.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultsBL {
    private static final Logger LOGGER = Logger.getLogger(SearchResultsBL.class.getName());

    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public SearchResultsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public SearchResultsBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public SearchResultsBL verifyTheResults(String productName){
        boolean isSearchedItemIsPresent = SearchResultsScreen.get().verifyTheSearchedResults(productName);
        softly.assertThat(isSearchedItemIsPresent).as(String.format("Item searched '%s' is not present in the search results", productName))
                .isTrue();
        LOGGER.info(System.out.printf("Searched '%s' items are displayed in the screen",productName));
        return this;
    }

    public ProductDetailsBL selectTheFirstItem(){
        SearchResultsScreen.get().selectTheFirstProduct();
        return new ProductDetailsBL();
    }


}
