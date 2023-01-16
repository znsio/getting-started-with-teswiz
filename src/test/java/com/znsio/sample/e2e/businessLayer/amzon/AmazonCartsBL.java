package com.znsio.sample.e2e.businessLayer.amzon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.web.Amazon.AmazonCartScreenWeb;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class AmazonCartsBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonCartsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonCartsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonCartsBL verifyItemAddedToCart(){
        String actualItemTitle  = AmazonCartScreenWeb.get().getCartItem();
        LOGGER.info(String.format("Item added to cart is: '%s'",actualItemTitle));
        String expectedItemTitle = context.getTestStateAsString("itemTitle");
        Assertions.
                assertThat(actualItemTitle)
                .as(String.format("Expected Item Title in the cart '%s' but found '%s'",expectedItemTitle,actualItemTitle))
                .isEqualTo(expectedItemTitle);
        return this;
    }
}
