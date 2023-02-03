package com.znsio.sample.e2e.businessLayer.amazon;

import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPageBL {
    private static final Logger LOGGER = Logger.getLogger(CartPageBL.class.getName());


    public CartPageBL prepareCart() {
        LOGGER.info("Adding product to the cart");
        AmazonCartScreen.get().addingFirstProductToCart();
        assertThat(AmazonCartScreen.get().isCartReady()).as("Product added in the cart").isTrue();
        return this;
    }
    public CartPageBL verifyProductDetailsInCart() {
        LOGGER.info("Navigating to the cart");
        AmazonCartScreen.get().viewCart();
        LOGGER.info("Verifying the correct product in cart");
        assertThat(AmazonCartScreen.get().isCartProductPresent()).as("Correct product is present in cart").isTrue();
      return this;
    }
}
