package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonProductViewPageScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonShoppingCartScreen;
import org.apache.log4j.Logger;
import org.testng.Assert;

public class ProductViewBL {
    private static final Logger LOGGER = Logger.getLogger(ProductViewBL.class.getName());
    private final TestExecutionContext context;

    public ProductViewBL(String userPersona, Platform forPlatform) {

        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ProductViewBL() {

        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
    }

    public ProductViewBL createShoppingCart() {

        AmazonProductViewPageScreen amazonProductViewPageScreen = AmazonProductViewPageScreen.get().addProductToCart();
        boolean isAddedToCartMessageVisible = amazonProductViewPageScreen.isAddToCartSuccessMessageAvailable();

        if (isAddedToCartMessageVisible) {
            String expectedSuccessMessage = SAMPLE_TEST_CONTEXT.ADD_TO_CART_SUCCESS_MESSAGE;
            String actualSuccessMessage = amazonProductViewPageScreen.getAddToCartSuccessMessage().toLowerCase();
            LOGGER.info(System.out.printf("Add To Cart Success Message : %s", actualSuccessMessage));
            Assert.assertEquals(expectedSuccessMessage, actualSuccessMessage, "The product is yet not added to shopping cart");
            AmazonProductViewPageScreen.get().navigateToShoppingCart();
        }

        boolean isProceedToBuyButtonAvailable = AmazonShoppingCartScreen.get().isProceedToBuyButtonPresent();
        Assert.assertTrue(isProceedToBuyButtonAvailable, "The user is not navigated to shopping cart");
        return this;
    }

}
