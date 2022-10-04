package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.CartScreen;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class CartBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public CartBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public CartBL verifyAddedProductInCart() {
        String firstProductTitle = context.getTestStateAsString("firstProductTitle");
        String addedProductTitle = CartScreen.get().getAddedProductTitleFromShoppingCartScreen(firstProductTitle);
        assertThat(firstProductTitle)
                .as(String.format("added product %s is not displayed in shopping cart", firstProductTitle))
                .isEqualTo(addedProductTitle);
        return this;
    }
}
