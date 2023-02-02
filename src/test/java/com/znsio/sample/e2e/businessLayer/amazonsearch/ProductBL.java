package com.znsio.sample.e2e.businessLayer.amazonsearch;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.ProductScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductBL {
    private static final Logger LOGGER = Logger.getLogger(ProductBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public ProductBL(String userPersona, Platform onPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = onPlatform;
        Runner.setCurrentDriverForUser(userPersona, onPlatform, context);
    }

    public ProductBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public ProductBL verifyProductDetails() {
        ProductScreen productScreen = ProductScreen.get();
        LOGGER.info("Verifying if product details are present correctly.");
        assertThat(productScreen.isProductDetailsDisplayed()).as("Product details are not present correctly").isTrue();
        return this;
    }

    public ProductBL addToCart() {
        LOGGER.info("Add product to the shopping cart.");
        ProductScreen productScreen = ProductScreen.get();
        assertThat(productScreen.clickOnAddToCart()).as("Add to cart operation was not successful").isTrue();
        return this;
    }
}
