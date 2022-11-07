package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonCartBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonCartBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonCartBL(String userPersona, Platform forPlatform){
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonCartBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }
    public AmazonCartBL verifyAmIOnCart() {
        String headingValue = AmazonCartScreen.get().AmIOnCartPage();
        assertThat(headingValue).containsIgnoringCase("Shopping Cart");
        String productName = AmazonCartScreen.get().verifySubTotalText();
        assertThat(productName).containsIgnoringCase("Subtotal");
        return this;
    }
    public AmazonCartBL verifySameProductPresentInCart() {
        boolean isSameProductIsInTheCart = AmazonCartScreen.get().verifyThePresenceOfAddedProductInTheCart();
        assertThat(isSameProductIsInTheCart).isTrue();
        return this;
    }
}
