package com.znsio.sample.e2e.businessLayer.amzon;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AMAZON_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ItemsDetailsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

public class ItemDetailsBL {
    private static final Logger LOGGER = Logger.getLogger(ItemDetailsBL.class.getName());
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ItemDetailsBL() {
        long threadId = Thread.currentThread()
                .getId();
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = AMAZON_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public ItemDetailsBL createCart() {
        ItemsDetailsScreen itemsDetailsScreen = ItemsDetailsScreen.get().selectAddToCart();
        String actualCartCreationSuccessText = itemsDetailsScreen.getCartCreationSuccessText();
        List<String> expectedCartCreationSuccessText = AMAZON_TEST_CONTEXT.CART_CREATION_SUCCESS_TEXT;
        LOGGER.info(String.format("Actual Cart Creation Success Text: '%s'", actualCartCreationSuccessText));
        Assertions.assertThat(expectedCartCreationSuccessText)
                .as(String.format("Expected cart confirmation text to be one of '%s' but found '%s'", expectedCartCreationSuccessText, actualCartCreationSuccessText))
                .contains(actualCartCreationSuccessText);
        boolean cartScreenLoaded = itemsDetailsScreen.selectCart().isScreenLoaded();
        Assertions.assertThat(cartScreenLoaded).as("Cart screen not loaded").isTrue();
        return this;
    }
}
