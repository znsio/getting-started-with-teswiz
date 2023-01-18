package com.znsio.sample.e2e.businessLayer.Amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonProductViewScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonProductViewBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonProductViewBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonProductViewBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
        LOGGER.info("AmazonSearchBL created");
    }

    public AmazonProductViewBL selectFirstProduct(){
        LOGGER.info(String.format("Select the first product"));
        String productName = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.PRODUCT_NAME);
        Boolean correct_product = AmazonProductViewScreen.get().selectFirstProduct().getCorrectProductDetails();
        assertThat(correct_product).as("The product details are displayed").isTrue();
        return this;
    }
    
    public AmazonProductViewBL preparingShoppingCart(){
        LOGGER.info(String.format("User add product into the shopping cart"));
        String expectedAddedToCartMessage = "Added to Cart";
        String actualAddedToCartMessage = AmazonProductViewScreen.get().clickAddToCartButton().getAddedToCartMessage();
        assertThat(actualAddedToCartMessage).as("Added to cart message is visible on the screen").isEqualToIgnoringCase(expectedAddedToCartMessage);
        return this;
    }
}
