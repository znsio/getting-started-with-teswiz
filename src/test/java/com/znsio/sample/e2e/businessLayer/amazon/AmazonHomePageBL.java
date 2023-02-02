package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.List;

public class AmazonHomePageBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonHomePageBL.class.getName());
    private final TestExecutionContext context;

    public AmazonHomePageBL(String userPersona, Platform forPlatform) {

        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonHomePageBL() {

        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
    }

    public AmazonHomePageBL searchForProduct(String product) {

        context.addTestState(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD, product);
        LOGGER.info(System.out.printf("Searching product (%s) and validating search results", product));

        AmazonSearchResultsScreen amazonSearchResultsScreen = AmazonHomeScreen.get().searchProductUsingAmazonSearchBar(product);
        List<String> productTitles = amazonSearchResultsScreen.getTitleOfProductsInSearchResultsList();
        boolean isProductPresentInSearchResults = false;

        for (String productTitle : productTitles) {
            if (productTitle.toLowerCase().contains(product)) {
                isProductPresentInSearchResults = true;
                break;
            }
        }

        Assert.assertTrue(isProductPresentInSearchResults, "Search results do not contain searched product : " + product);
        return this;
    }

    public AmazonHomePageBL selectFirstProduct() {

        LOGGER.info("Selecting the first product from the search results");
        AmazonSearchResultsScreen amazonSearchResultsScreen = AmazonSearchResultsScreen.get();
        amazonSearchResultsScreen.clickOnFirstProductInSearchResultsList();
        return this;
    }

}
