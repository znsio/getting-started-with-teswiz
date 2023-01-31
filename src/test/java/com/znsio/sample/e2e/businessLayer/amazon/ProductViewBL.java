package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonProductViewPageScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonShoppingCartScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;

public class ProductViewBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonHomePageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ProductViewBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ProductViewBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public ProductViewBL createShoppingCart() {

        AmazonProductViewPageScreen amazonProductViewPageScreen = AmazonProductViewPageScreen.get().addProductToCart();
        String expectedSuccessMessage = SAMPLE_TEST_CONTEXT.ADD_TO_CART_SUCCESS_MESSAGE;
        String actualSuccessMessage = amazonProductViewPageScreen.getAddToCartSuccessMessage().toLowerCase();
        LOGGER.info(System.out.printf("Add To Cart Success Message : %s", actualSuccessMessage));
        Assert.assertEquals(expectedSuccessMessage, actualSuccessMessage, "The product is not successfully added to shopping cart");

        AmazonShoppingCartScreen amazonShoppingCartScreen = AmazonProductViewPageScreen.get().navigateToShoppingCart();
        String expectedPageTitle = SAMPLE_TEST_CONTEXT.SHOPPING_CART_PAGE_HEADING;
        String actualPageTitle = amazonShoppingCartScreen.getShoppingCartPageTitle();
        LOGGER.info(System.out.printf("Page Title : %s", actualPageTitle));
        Assert.assertEquals(expectedPageTitle, actualPageTitle, "The user is not navigated to shopping cart");
        return this;
    }

}
