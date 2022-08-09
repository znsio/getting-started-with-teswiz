package com.znsio.sample.e2e.businessLayer.Amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AmazonShopping.AMAZON_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.AmazonScreens.ProductLandingPageScreen;
import org.assertj.core.api.SoftAssertions;

public class ProductsLandingPageBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ProductsLandingPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = AMAZON_SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }
    public ProductsLandingPageBL validateProductDetailsAndTitle(String firstProductTitle, String firstProductPrice) {
        boolean isProdDetailsAndTitleCorrect = ProductLandingPageScreen.get().isProdDetailsAndTitleCorrect(firstProductTitle, firstProductPrice);
        softly.assertThat(isProdDetailsAndTitleCorrect).as(String.format("Mismatch in Product Details on Landing Page")).isTrue();
        return new ProductsLandingPageBL();
    }

    public ProductsLandingPageBL addProductToCart() {
        ProductLandingPageScreen.get().addProductToCart();
        return this;
    }

    public CartLandingPageBL goToCartPage() {
        ProductLandingPageScreen.get().goToCartPage();
        return new CartLandingPageBL();
    }
}
