package com.znsio.sample.e2e.businessLayer.ajioUAT;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.*;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AjioProductBL {
    private static final Logger LOGGER = Logger.getLogger(AjioProductBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AjioProductBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AjioProductBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }


    public AjioProductBL wishlistTheProductFromSearchResult(int itemNumber) {
        LOGGER.info("Adding the Product to Wishlist from Product Details");

        assertThat(AjioSearchResultsScreen.get()
                .goToProductDetails(itemNumber)
                .isProductOpened())
                .as("Product Details not Opened")
                .isTrue();

        AjioProductDetailsScreen ajioProductDetailsScreen = AjioProductDetailsScreen.get();
        String productName = ajioProductDetailsScreen.getProductName();
        context.addTestState(SAMPLE_TEST_CONTEXT.PRODUCT_NAME, productName);
        LOGGER.info("Name of the Product Selected : " + productName);

        if (!(ajioProductDetailsScreen.isProductWishlisted())) {
            assertThat(ajioProductDetailsScreen
                    .wishlistTheProduct()
                    .goToWishList()
                    .isProductPresentInWishlist(productName))
                    .as(" Product is not added to wishlist ")
                    .isTrue();
        }
        return this;
    }

    public AjioProductBL moveTheProductToCart() {
        LOGGER.info("Moving the product to Cart from Details");
        String productSize = "6";
        assertThat(AjioWishlistScreen.get()
                .goBackToProductDetails()
                .removeFromWishlist()
                .selectSize(productSize)
                .addToBag()
                .goToBag()
                .isProductAddedToBag())
                .as(" Product is not added to Cart")
                .isTrue();
        return this;
    }

    public AjioHomeBL removeProductFromCart() {
        LOGGER.info("Removing Product from Cart");
        AjioCartScreen.get()
                .removeProductFromCart();
        return new AjioHomeBL();
    }
}
