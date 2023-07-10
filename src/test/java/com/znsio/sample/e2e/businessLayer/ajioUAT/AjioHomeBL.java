package com.znsio.sample.e2e.businessLayer.ajioUAT;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import com.znsio.sample.e2e.screen.ajio.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.sample.e2e.screen.ajio.AjioWishlistScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AjioHomeBL {
    private static final Logger LOGGER = Logger.getLogger(AjioHomeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AjioHomeBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AjioHomeBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public AjioHomeBL signinAsValidUser(Map userDetails) {
        assertThat(AjioHomeScreen.get().signInUser(
                        userDetails.get("emailId").toString(),
                        userDetails.get("password").toString())
                .isUserSignedIn())
                .as("User is not Signed In")
                .isTrue();
        return this;
    }

    public AjioProductBL searchFor(String product) {
        clearWishlistForUser();
        clearCartForUser();
        LOGGER.info("Searching in home page for the product");
        AjioSearchResultsScreen ajioSearchResultsScreen = AjioHomeScreen.get().searchFor(product);
        String actualSearchWasFor = ajioSearchResultsScreen.getActualSearchString();
        softly.assertThat(actualSearchWasFor.toUpperCase()).as("Search was for a different value")
                .isEqualTo(product.toUpperCase());

        int numberOfProductsFound = ajioSearchResultsScreen.getNumberOfProductsFound();
        assertThat(numberOfProductsFound).as("Insufficient search results retrieved")
                .isGreaterThan(10);
        return new AjioProductBL();
    }

    private void clearWishlistForUser() {
        AjioWishlistScreen ajioWishlistScreen = AjioHomeScreen.get().goToWishList();
        if (!(ajioWishlistScreen.isWishlistEmpty())) {
            assertThat(ajioWishlistScreen
                    .clearWishlist()
                    .isWishlistEmpty())
                    .as("User wishlist is not cleared")
                    .isTrue();
        }
    }

    private void clearCartForUser() {
        AjioCartScreen ajioCartScreen = AjioHomeScreen.get().goToCart();
        if (!(ajioCartScreen.isCartEmpty())) {
            assertThat(ajioCartScreen
                    .clearCart()
                    .isCartEmpty())
                    .as("User Cart is not cleared")
                    .isTrue();
        }
        ajioCartScreen.continueShopping();
    }

    public AjioHomeBL verifyCartIsEmpty() {
        LOGGER.info("Verifying in Cart Page for Empty Cart");
        assertThat(AjioHomeScreen.get()
                .goToCart()
                .isCartEmpty())
                .as("Cart is not empty")
                .isTrue();
        return this;
    }

    public AjioHomeBL signOutUser() {
        LOGGER.info("SignOut User");
        assertThat(AjioCartScreen.get()
                .signOut()
                .isUserSignedOut())
                .as("User is not signed out")
                .isTrue();

        return this;
    }
}
