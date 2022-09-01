package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.CONTEXT_AMAZON;
import com.znsio.sample.e2e.screen.amazon.CartPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

public class CartPageBL {
    private static final Logger LOGGER = Logger.getLogger(CartPageBL.class.getName());
    private final SoftAssertions softly;
    private final TestExecutionContext context;

    public CartPageBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        String currentUserPersona = CONTEXT_AMAZON.ME;
        Platform currentPlatform = Runner.platform;
    }

    public CartPageBL validateAddedProduct() {

        Map<String, String> cartProducts = CartPageScreen.get().getCartContents();
        assertThat(cartProducts.get("productName")).isEqualTo(context.getTestState(CONTEXT_AMAZON.PRODUCT_TITLE));
        LOGGER.info("Cart content validated.");
        //assertThat(cartProducts.get("productQuantity")).isEqualTo(context.getTestState(CONTEXT_AMAZON.PRODUCT_QUANTITY));
        return this;

    }
}
