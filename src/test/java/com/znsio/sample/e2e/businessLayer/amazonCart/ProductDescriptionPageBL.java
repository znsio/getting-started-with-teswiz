package com.znsio.sample.e2e.businessLayer.amazonCart;


import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AMAZON_ASSIGNMENT_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ProductLandingPageScreen;
import org.assertj.core.api.SoftAssertions;

import java.util.List;


public class ProductDescriptionPageBL {

    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;


    public ProductDescriptionPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = AMAZON_ASSIGNMENT_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public void validateProductDetails(String productTitle, String productPrice) {
        boolean validateproductTitle = productTitle.equalsIgnoreCase(ProductLandingPageScreen.get().getProductTitle());
        softly.assertThat(validateproductTitle).as(String.format("Product title does not match")).isTrue();
        boolean validateproductPrice = productPrice.equalsIgnoreCase(ProductLandingPageScreen.get().getProductPrice());
        softly.assertThat(validateproductPrice).as(String.format("Product price does not match")).isTrue();
    }

    public ProductDescriptionPageBL addProductToShoppingCart() {
        ProductLandingPageScreen.get().addProductToCart();
        return this;
    }

    public ShppingCartPageBL openCartPage() {
        ProductLandingPageScreen.get().goToCartPage();
        return new ShppingCartPageBL();
    }
}
