package com.znsio.sample.e2e.businessLayer.ajio;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.ProductDetailScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDetailBL {
    private static final Logger LOGGER = Logger.getLogger(ProductDetailBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ProductDetailBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ProductDetailBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public ProductDetailBL addProductToCart() {
        LOGGER.info(String.format("addProductToCart: Add product to cart"));
        ProductDetailScreen productDetailScreen = ProductDetailScreen.get().selectProductSize().addToCart();
        assertThat(context.getTestStateAsString(SAMPLE_TEST_CONTEXT.PRODUCT_BRAND))
                .as("Product brand name is different")
                .isEqualToIgnoringCase(productDetailScreen.getProductBrand());
        return this;
    }

    public CartBL navigateToCart(){
        LOGGER.info(String.format("navigateToCart: Navigate to the cart"));
        ProductDetailScreen.get().proceedToCart();
        return new CartBL();
    }
}