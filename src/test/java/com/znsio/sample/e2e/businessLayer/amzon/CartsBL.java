package com.znsio.sample.e2e.businessLayer.amzon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AMAZON_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.web.Amazon.CartScreenWeb;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class CartsBL {

    private static final Logger LOGGER = Logger.getLogger(CartsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public CartsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = AMAZON_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public CartsBL verifyCart(){
        String actualItemTitle  = CartScreenWeb.get().getCartItem();
        LOGGER.info(String.format("Item added to cart is: '%s'",actualItemTitle));
        String expectedItemName = context.getTestStateAsString("itemName");
        String expectedItemTitle = context.getTestStateAsString("itemTitle");
        Assertions.
                assertThat(actualItemTitle)
                .as(String.format("Item Title '%s' in the cart does not contain searched item name '%s'",actualItemTitle,expectedItemName))
                .containsIgnoringCase(expectedItemName);
        Assertions.
                assertThat(actualItemTitle)
                .as(String.format("Expected Item Title in the cart '%s' but found '%s'",expectedItemTitle,actualItemTitle))
                .isEqualTo(expectedItemTitle);
        return this;
    }
}
