package com.znsio.sample.e2e.businessLayer.ajio;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.ShoppingCartScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class CartBL {
    private static final Logger LOGGER = Logger.getLogger(CartBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public CartBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public CartBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public CartBL applyCouponCode() {
        String expectedProductBrand = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.PRODUCT_BRAND);
        ShoppingCartScreen shoppingCartScreen = ShoppingCartScreen.get();
        softly.assertThat(shoppingCartScreen.getProductBrand()).as("Product brand in the cart").containsIgnoringCase(expectedProductBrand);
        double orderTotal = shoppingCartScreen.getOrderTotal();
        shoppingCartScreen.selectVoucher().applyVoucher();
        assertThat(orderTotal).as("Coupon applied successfully")
                .isGreaterThan(shoppingCartScreen.getOrderTotal());
        return this;
    }
}
