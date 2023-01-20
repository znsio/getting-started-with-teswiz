package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonProduct;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AmazonProductBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonSearchBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonProductBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonProductBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public void addProductToCart()
    {
        LOGGER.info("Adding product to cart");
        boolean hasProductAddedInCart=AmazonProduct.get().verifyAddedProductToCart().viewCart();
        softly.assertThat(hasProductAddedInCart).as("Shopping cart navigation problem").isEqualTo(true);

    }

    public void seeProductInCart()
    {
        LOGGER.info("Verifying product in cart");
        assertThat(AmazonProduct.get().verifyProductInCart()).
                as("verification failure in cart").isEqualTo(true);
    }

}
