package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.NavBarScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchResultsBL {
    private static final Logger LOGGER = Logger.getLogger(SearchResultsBL.class.getName());
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

    public SearchResultsBL searchForProductAndVerify(String productName) {
        List<String> productSearchResults = NavBarScreen.get().searchForProduct(productName).getAllSearchResults();
        assertThat(productSearchResults.stream().allMatch(s -> s.toLowerCase().contains(productName.toLowerCase())))
                .as(String.format("Verify search results for '%s'", productName))
                .isTrue();
        return this;
    }


    /**
     * Opens first product from search results.
     */
    public ProductDetailsBL selectProductAndVerifyDetails(int productIndexInSearchResults) {
        context.addTestState("ProductName", SearchResultsScreen.get().openProductByIndex(productIndexInSearchResults));
        return new ProductDetailsBL().verifyProductName();
    }
}
