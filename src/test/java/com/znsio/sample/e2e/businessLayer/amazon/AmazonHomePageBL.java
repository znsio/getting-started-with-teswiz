package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;

import java.util.List;

public class AmazonHomePageBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonHomePageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonHomePageBL(String userPersona, Platform forPlatform) {

        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonHomePageBL() {

        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public AmazonHomePageBL searchForProduct(String product) {

        LOGGER.info("Searching product and validating search results");
        AmazonSearchResultsScreen amazonSearchResultsScreen = AmazonHomeScreen.get().searchProductUsingAmazonSearchBar(product);
        List<String> productTitles = amazonSearchResultsScreen.getTitleOfProductsInSearchResultsList();
        boolean isProductPresentInSearchResults = false;

        for (String productTitle : productTitles) {
            if (productTitle.toLowerCase().contains(product)) {
                isProductPresentInSearchResults = true;
                break;
            }
        }

        Assert.assertTrue(isProductPresentInSearchResults, "Search results do not contain searched product");

        /**
         String actualSearchWasFor = amazonSearchResultsScreen.getActualSearchString();
         Assert.assertEquals(actualSearchWasFor, product, "Search results do not contain searched product");
         int numberOfProductsFound = amazonSearchResultsScreen.getNumberOfProductsFound();
         softly.assertThat(numberOfProductsFound).as("Insufficient search results retrieved").isGreaterThan(0);
         */
        return this;
    }

    public AmazonHomePageBL selectFirstProduct() {

        LOGGER.info("Selecting the product from the search results");
        AmazonSearchResultsScreen amazonSearchResultsScreen = AmazonSearchResultsScreen.get();
        amazonSearchResultsScreen.clickOnFirstProductInSearchResultsList();
        return this;
    }

}
