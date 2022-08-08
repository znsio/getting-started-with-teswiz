package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomePageScreen;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.ProductSearchResultScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;

public class AmazonBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonBL(){
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonBL launchWebApplication() {
        AmazonHomePageScreen.get().openHomePage();
        return this;
    }

    public AmazonBL searchForProduct(String productName) {
        AmazonHomePageScreen.get().searchForProduct(productName);
        return this;
    }

    public AmazonBL verifyResultsDisplayed() {
        boolean resultDisplayStatus = ProductSearchResultScreen.get().verifyResultsDisplayed();
        Assert. assertTrue(resultDisplayStatus,"There are no search results displayed");
        return this;
    }

    public AmazonBL openFirstResult() {
        ProductSearchResultScreen.get().openFirstResult();
        return this;
    }

    public AmazonBL verifyProductDetails() {
        Assert.assertTrue(ProductDetailsScreen.get().verifyProductDetails(),"Product details page not loaded");
        return this;
    }

    public AmazonBL verifyAddToCartButton(){
        int value = ProductDetailsScreen.get().verifyAddToCartButton();
        if(value==-2)
            Assert.fail("Product out of stock, currently unavailable");
        else if(value==1)
            Assert.assertTrue(true,"Add to Cart Button displayed");
        else
            Assert.fail("Add to cart button not displayed");
        return this;
    }

    public AmazonBL addProductToCart() {
        ProductDetailsScreen.get().addProductToCart();
        return this;
    }

    public AmazonBL openCart() {
        ProductDetailsScreen.get().openCart();
        return this;
    }

    public AmazonBL verifyProductAddedToCart() {
        Assert.assertTrue(ProductDetailsScreen.get().verifyProductAddedToCart());
        return this;
    }
}
