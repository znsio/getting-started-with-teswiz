package com.znsio.sample.e2e.businessLayer.Amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.AmazonCart.ProductDetailsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDetailsBL {
    private static final Logger LOGGER = Logger.getLogger(ProductDetailsBL.class.getName());

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

    public ProductDetailsBL verifyTheProductDetails(){
        String productName = context.getTestState("ExactProductName").toString();
        assertThat(ProductDetailsScreen.get().getPageTitle()).as(String.format("PageTitle contains '%s'",productName)).contains(productName);
        assertThat(ProductDetailsScreen.get().getProductTitle()).as(String.format("PageTitle contains '%s'",productName)).contains(productName);
        assertThat(ProductDetailsScreen.get().isCartPresent()).isTrue();
        LOGGER.info(System.out.printf("Product '%s' Details are verified",productName));
        return this;

    }

    public ProductCartDetailsBL addItemToCart(){
        ProductDetailsScreen.get().addItemToTheCart();
        return new ProductCartDetailsBL();
    }


}
