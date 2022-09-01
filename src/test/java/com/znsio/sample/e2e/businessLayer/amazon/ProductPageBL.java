package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.CONTEXT_AMAZON;
import com.znsio.sample.e2e.screen.amazon.PopupCartPageScreen;
import com.znsio.sample.e2e.screen.amazon.ProductPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductPageBL {
    private static final Logger LOGGER = Logger.getLogger(ProductPageBL.class.getName());
    private final SoftAssertions softly;
    private final TestExecutionContext context;

    public ProductPageBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        String currentUserPersona = CONTEXT_AMAZON.ME;
        Platform currentPlatform = Runner.platform;
    }

    public ProductPageBL addProductToCart() {
        String cartMessage = ProductPageScreen.get().addProductToCart().getAddedToCartSuccessMsg();
        assertThat("Added to Cart").isEqualTo(cartMessage);
        LOGGER.info(String.format("cartMessage '%s'", cartMessage));
        //int CurrentCartCount = ProductPageScreen.get().getNumberOfProductsInCart();
        //context.addTestState(CONTEXT_AMAZON.PRODUCT_QUANTITY,Integer.toString(CurrentCartCount));
        //LOGGER.info(String.format("Current number of products in cart - '%d'", CurrentCartCount));
        return this;
    }

    public ProductPageBL navigateToCart() {
        String cartHeaderText = PopupCartPageScreen.get().navigateToCartFromAddedToCartPopup().getCartHeaderText();
        assertThat("Shopping Cart").isEqualTo(cartHeaderText);
        LOGGER.info(String.format("cartHeaderText '%s'", cartHeaderText));
        return this;
    }
}
