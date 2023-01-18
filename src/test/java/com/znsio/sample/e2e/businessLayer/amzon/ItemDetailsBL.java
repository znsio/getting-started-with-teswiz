package com.znsio.sample.e2e.businessLayer.amzon;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AMAZON_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ItemsDetailsScreen;
import com.znsio.sample.e2e.screen.web.Amazon.ItemsDetailsScreenWeb;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

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
        ItemsDetailsScreen itemsDetailsScreen = ItemsDetailsScreenWeb.get().selectAddToCart();
        String actualCartCreationSuccessText = itemsDetailsScreen.getCartCreationSuccessText();
        String expectedCartCreationSuccessText = AMAZON_TEST_CONTEXT.CART_CREATION_SUCCESS_TEXT;
        LOGGER.info(String.format("Actual Cart Creation Success Text: '%s'",actualCartCreationSuccessText));
        Assertions.assertThat(actualCartCreationSuccessText)
                .as(String.format("Expected cart confirmation text '%s' but found '%s'",expectedCartCreationSuccessText,actualCartCreationSuccessText))
                .isEqualTo(expectedCartCreationSuccessText);
        String actualCartScreenTitle = itemsDetailsScreen.selectCart().getScreenTitle();
        String expectedCartScreenTitle = AMAZON_TEST_CONTEXT.CART_SCREEN_TITLE;
        LOGGER.info(String.format("Actual Item details screen title: '%s'", actualCartScreenTitle));
        Assertions.assertThat(actualCartScreenTitle).as("After selecting item expected screen title '%s' but found '%s'",expectedCartScreenTitle,actualCartScreenTitle)
                .contains(expectedCartScreenTitle);
        return this;
    }
}
