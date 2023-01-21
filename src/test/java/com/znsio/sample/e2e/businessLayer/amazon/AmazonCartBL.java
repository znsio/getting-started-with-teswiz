package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;

public class AmazonCartBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonCartBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonCartBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }
    public AmazonCartBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonCartBL verifyTheProductInCart() {
        String expectedAddCartProductName = AmazonProductScreen.get().getProductNameInProductScreen();
        AmazonCartScreen.get().navigateToCart();
        String actualProductNameOnCart = AmazonCartScreen.get().getProductNameInCart();
        Assertions.assertEquals(expectedAddCartProductName, actualProductNameOnCart, "Verify the add Cart product is on cart");
        return this;
    }

}
