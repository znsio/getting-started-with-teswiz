package com.znsio.e2e.businessLayer.amazoncartvalidation;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.amazoncartvalidation.ProductCartScreen;
import com.znsio.e2e.screen.amazoncartvalidation.ProductDetailsScreen;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class ProductCartBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ProductCartBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ProductCartBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public ProductCartBL get() {
        ProductCartScreen.get();
        return this;
    }

    public ProductCartBL verifyProductIsAddedToTheCart() {
        String productTitle = context.getTestState("Product Title").toString();
        Assertions.assertThat(ProductCartScreen.get().getTitleOfProductPresentInCart())
                .as(String.format("Product in cart should be '%s'", productTitle))
                .contains(productTitle);
        return this;
    }
}
