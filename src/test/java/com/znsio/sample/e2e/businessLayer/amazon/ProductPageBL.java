package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ProductPageScreen;
import org.apache.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageBL {
    private static final Logger LOGGER = Logger.getLogger(ProductPageBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ProductPageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ProductPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public ProductPageBL verifyAndAddProductToCart() {
        String checkForProductName = ProductPageScreen.get().verifyProductName();
        assertThat(checkForProductName).containsIgnoringCase("iphone 13");
        ProductPageScreen.get().clickAddToCart();
        return this;
    }

    public AmazonCartBL verifyCartAndClickOnCart() {
        String successMessage = ProductPageScreen.get().checkSuccessMsgForAddToCart();
        assertThat(successMessage).containsIgnoringCase("Added to Cart");
        ProductPageScreen.get().moveToCart();
        return new AmazonCartBL();
    }
}
