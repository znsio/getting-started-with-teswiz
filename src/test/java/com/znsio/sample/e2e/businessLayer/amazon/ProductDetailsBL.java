package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import org.assertj.core.api.SoftAssertions;

public class ProductDetailsBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ProductDetailsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public ProductDetailsBL userAddsProductToCart() {
        ProductDetailsScreen.get().userAddsProductToCart();
        return this;
    }
}