package com.znsio.sample.e2e.businessLayer.amzon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonItemsDetailsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class AmazonItemDetailsBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonItemDetailsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonItemDetailsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonCartsBL placeItemInToCart() {
        AmazonItemsDetailsScreen amazonProductDetailsScreen = AmazonItemsDetailsScreen.get().selectAddToCart();
        String addedToCartText = amazonProductDetailsScreen.getAddedToCartText();
        String expectedText = SAMPLE_TEST_CONTEXT.ADDED_TO_CART;
        Assertions.assertThat(addedToCartText)
                .as(String.format("Expected cart confirmation text '%s' but found '%s'",expectedText,addedToCartText))
                .isEqualTo(expectedText);
        amazonProductDetailsScreen.selectCart();
        return new AmazonCartsBL();
    }
}
