package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.assertj.core.api.SoftAssertions;

public class SearchResultsBL {

    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;


    public SearchResultsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public ProductDetailsBL userSelectsFirstProduct() {
        SearchResultsScreen searchResultsScreen=SearchResultsScreen.get();
        context.addTestState("firstProductTitle",searchResultsScreen.getTitleOfFirstProduct());
        String productTitleFromProductDetailsScreen = searchResultsScreen.userSelectsFirstProduct().getProductTitleFromProductDetailsScreen();
        String firstProductTitle = context.getTestStateAsString("firstProductTitle");
        softly.assertThat(firstProductTitle).as(String.format("Searched product %s title is not displayed on product details screen ", firstProductTitle)).isEqualTo(productTitleFromProductDetailsScreen);
        return new ProductDetailsBL();
    }
}