package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonCartPageBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonCartPageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    public AmazonCartPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }
    public AmazonCartPageBL makingCartReady() {
        LOGGER.info(String.format("Adding product to the cart"));
        AmazonCartScreen.get().addingFirstProductToCart();
        boolean isProductAddedToCart = AmazonCartScreen.get().isCartReady();
        assertThat(isProductAddedToCart).as("Product added in the cart").isTrue();
        return this;
    }
    public AmazonCartPageBL verifyProductDetailsInCart() {
        LOGGER.info(String.format("Navigating to the cart"));
        AmazonCartScreen.get().moveToCart();
        LOGGER.info(String.format("Verifying the correct product in cart"));
       boolean isProductCorrectInCart= AmazonCartScreen.get().isCartProductCorrect();
        assertThat(isProductCorrectInCart).as("Correct product is present in cart").isTrue();
      return this;

    }
}
