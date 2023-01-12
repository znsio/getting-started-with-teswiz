package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonProductViewPageScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonShoppingCartScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class ProductViewBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonHomepageBL.class.getName());
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

    public ProductViewBL addToCart(){
        AmazonProductViewPageScreen amazonProductViewPageScreen = AmazonProductViewPageScreen.get().addToCart();

        String expectedSuccessMessage = "Added to Cart";
        String actualSuccessMessage = amazonProductViewPageScreen.getAddToCartSuccessMessage();

        softly.assertThat(actualSuccessMessage).as("Add to Cart Success Message").isEqualTo(expectedSuccessMessage);
        return this;
    }

    public ShoppingCartBL navigateToShoppingCart(){
        AmazonShoppingCartScreen amazonShoppingCartScreen = AmazonProductViewPageScreen.get().navigateToShoppingCart();

        String expectedSuccessMessage = "Shopping Cart";
        String actualSuccessMessage = amazonShoppingCartScreen.getShoppingCartPageHeading();

        softly.assertThat(actualSuccessMessage).as("Shopping Cart Page Heading").isEqualTo(expectedSuccessMessage);
        return new ShoppingCartBL();
    }

}
