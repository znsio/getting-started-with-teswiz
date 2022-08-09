package com.znsio.sample.e2e.businessLayer.Amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AmazonShopping.AMAZON_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.AmazonScreens.CartLandingPageScreen;
import org.assertj.core.api.SoftAssertions;

public class CartLandingPageBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public CartLandingPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = AMAZON_SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }
    public void VerifyCartDetails(String firstProductTitle, String firstProductPrice) {
        boolean validateSameProductAddedToCart = CartLandingPageScreen.get().validateSameProductAddedToCart(firstProductTitle, firstProductPrice);
        softly.assertThat(validateSameProductAddedToCart).as(String.format("Same Product NOT added to cart")).isTrue();
    }
}
