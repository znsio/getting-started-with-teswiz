package com.znsio.sample.e2e.businessLayer.ajioUAT;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import com.znsio.sample.e2e.screen.ajio.AjioProductDetailsScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.sample.e2e.screen.ajio.AjioWishlistScreen;
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
        assertThat(AjioSearchResultsScreen.get()
                .goToProductDetails(itemNumber)
                .isProductOpened())
                .as("Product Details not Opened")
                .isTrue();

        AjioProductDetailsScreen ajioProductDetailsScreen = AjioProductDetailsScreen.get();
        assertThat(ajioProductDetailsScreen
                .wishlistTheProduct()
                .goToWishList()
                .isProductWishlisted(ajioProductDetailsScreen.getProductName()))
                .as(" Product is not added to wishlist ")
                .isTrue();

        return this;
    }

    public AjioProductBL moveTheProductToCart() {
        assertThat(AjioWishlistScreen.get()
                .goToProductDetails()
                .selectSize()
                .addToBag()
                .goToBag()
                .isProductAddedToBag())
                .as(" Product is not added to Cart")
                .isTrue();
        return this;
    }

    public AjioProductBL removeProductFromCart() {
        assertThat(AjioCartScreen.get()
                .removeProductFromCart()
                .isProductRemovedFromCart())
                .as("Product not removed from cart")
                .isTrue();
        return this;
    }

    public AjioProductBL verifyCartIsEmpty() {
        assertThat(AjioCartScreen.get()
                .isCartEmpty())
                .as("Cart is not empty")
                .isTrue();
        return this;
    }
}
