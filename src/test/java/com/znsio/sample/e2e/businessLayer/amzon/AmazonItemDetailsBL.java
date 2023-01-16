package com.znsio.sample.e2e.businessLayer.amzon;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonItemsDetailsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class AmazonItemDetailsBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonItemDetailsBL.class.getName());

    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonItemDetailsBL() {
        long threadId = Thread.currentThread()
                .getId();
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonCartsBL placeItemInToCart() {
        AmazonItemsDetailsScreen amazonItemsDetailsScreen = AmazonItemsDetailsScreen.get().selectAddToCart();
        String actualCartCreationSuccessText = amazonItemsDetailsScreen.getCartCreationSuccessText();
        String expectedCartCreationSuccessText = SAMPLE_TEST_CONTEXT.CART_CREATION_SUCCESS_TEXT;
        LOGGER.info(String.format("Actual Cart Creation Success Text: '%s'",actualCartCreationSuccessText));
        Assertions.assertThat(actualCartCreationSuccessText)
                .as(String.format("Expected cart confirmation text '%s' but found '%s'",expectedCartCreationSuccessText,actualCartCreationSuccessText))
                .isEqualTo(expectedCartCreationSuccessText);
        amazonItemsDetailsScreen.selectCart();
        return new AmazonCartsBL();
    }
}
