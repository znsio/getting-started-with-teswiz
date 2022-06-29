package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.businessLayer.theapp.AppBL;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchResultsBL {


    private static final Logger LOGGER = Logger.getLogger(AppBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public SearchResultsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }


    /**
     * Opens first prodcut from search results.
     */
    public ProductDetailsBL openFirstProduct() {
        context.addTestState("ProductName", SearchResultsScreen.get().openProductByIndex(0));
        return new ProductDetailsBL(currentUserPersona, currentPlatform);
    }

    /**
     * Verifies search results for {@code productName} .
     */
    public SearchResultsBL verifySearchResults(String productName) {
        List<String> products = SearchResultsScreen.get().getAllSearchResults();
        assertThat(products.stream().allMatch(s -> s.toLowerCase().contains(productName.toLowerCase())))
                .as(" Verify search results")
                .isTrue();
        return this;

    }


}
