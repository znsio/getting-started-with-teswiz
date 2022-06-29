package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.businessLayer.theapp.AppBL;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductDetailsBL {
    private static final Logger LOGGER = Logger.getLogger(AppBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ProductDetailsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    /**
     * Verifies the product details.
     */
    public ProductDetailsBL verifyProductName() {
        String actualProductName = ProductDetailsScreen.get().getProductName();
        String expectedProductName = context.getTestStateAsString("ProductName");
        assertThat(actualProductName)
                .as("Verify items in cart")
                .isEqualTo(expectedProductName);
        return this;
    }

    /**
     * Adds product to cart.
     */
    public ShoppingCartBL addItemsToCard() {
        ProductDetailsScreen.get().clickAddToCardButton().clickViewCartButton();
        return new ShoppingCartBL(currentUserPersona, currentPlatform);
    }
}
