package com.znsio.sample.e2e.businessLayer.ajio;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import com.znsio.sample.e2e.screen.ajio.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio.AjioProductScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AjioSearchBL {
    private static final Logger LOGGER = LogManager.getLogger(AjioSearchBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AjioSearchBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AjioSearchBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public AjioSearchBL searchFor(String product) {
        AjioSearchResultsScreen ajioSearchResultsScreen = AjioHomeScreen.get().searchFor(product);
        String actualSearchWasFor = ajioSearchResultsScreen.getActualSearchString();
        softly.assertThat(actualSearchWasFor).as("Search was for a different value")
              .isEqualTo(product);

        int numberOfProductsFound = ajioSearchResultsScreen.getNumberOfProductsFound();
        assertThat(numberOfProductsFound).as("Insufficient search results retrieved")
                                         .isGreaterThan(100);
        return this;
    }
    public AjioSearchBL searchProduct(Map searchData) {
        LOGGER.info("searchProduct" + searchData);
        AjioSearchResultsScreen searchScreen = AjioHomeScreen.get().attachFileToDevice(searchData).searchByImage();
        assertThat(searchScreen.numberOfProductFound()).as("Number of results found for product")
                .isGreaterThan(0);
        searchScreen.selectProduct();
        return this;
    }


    public AjioSearchBL prepareCart() {
        AjioProductScreen productScreen = AjioProductScreen.get();
        context.addTestState("productName", productScreen.getProductName());
        LOGGER.info("productName: " + context.getTestState("productName"));
        productScreen.addProductToCart();
        return this;
    }


    public AjioSearchBL verifyCart() {
        String actualProductName = AjioCartScreen.get().getActualProductName();
        LOGGER.info("Actual product name in the cart" + actualProductName);
        assertThat(actualProductName).as("Product in the Cart")
                .isEqualTo(context.getTestState("productName"));
        return this;
    }
}
