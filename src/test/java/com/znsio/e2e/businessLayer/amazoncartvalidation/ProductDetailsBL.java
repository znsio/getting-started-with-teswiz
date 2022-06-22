package com.znsio.e2e.businessLayer.amazoncartvalidation;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.amazoncartvalidation.AmazonHomeScreen;
import com.znsio.e2e.screen.amazoncartvalidation.ProductDetailsScreen;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDetailsBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ProductDetailsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ProductDetailsBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public ProductDetailsBL get() {
        ProductDetailsScreen.get();
        return this;
    }
    public ProductDetailsBL verifyProductDeatils(){
        String productTitle=context.getTestState("Product Title").toString();
        assertThat(ProductDetailsScreen.get().getTitleOfPage()).as(String.format("Page Title should contain '%s'",productTitle)).contains(productTitle);
        assertThat(ProductDetailsScreen.get().getTitleOfProduct()).as(String.format("Product title should be '%s'",productTitle)).contains(productTitle);
        assertThat(ProductDetailsScreen.get().isElementPresent()).as("Add to cart option should be available").isTrue();
        return this;

    }

    public ProductCartBL addProductToTheCart(){
        ProductDetailsScreen.get().addProductToCart();
        return new ProductCartBL();
    }
}
