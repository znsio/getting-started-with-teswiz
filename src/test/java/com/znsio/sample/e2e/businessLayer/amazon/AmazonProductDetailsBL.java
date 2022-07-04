package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ProductScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AmazonProductDetailsBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonProductDetailsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonProductDetailsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonProductDetailsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    private AmazonProductDetailsBL validateUserOnProductDetailsPage() {
        ProductScreen productScreen=ProductScreen.get();
        boolean isUserOnProductsDetailPage=productScreen.isUserOnProductPage();
        assertThat(isUserOnProductsDetailPage).as("User is not on product details page").isTrue();
        return this;
    }


    public AmazonProductDetailsBL validateAddToCartButtonIsPresent() {
        ProductScreen productScreen=ProductScreen.get();
        boolean isAddToCartButtonPresent=productScreen.isAddToCartButtonIPresent();
        assertThat(isAddToCartButtonPresent).as("Add to cart button is not found on Product Details Page").isTrue();
        return this;
    }

    public AmazonProductDetailsBL userAddProductToCart() {
        this.validateUserOnProductDetailsPage().validateAddToCartButtonIsPresent();
        ProductScreen.get().iAddProductToCart();
        return this;
    }
}
