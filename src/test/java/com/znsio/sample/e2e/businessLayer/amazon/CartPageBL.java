package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonCartPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPageBL {
    private static final Logger LOGGER = Logger.getLogger(CartPageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    String selectedProductDesc;

    public CartPageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public CartPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public CartPageBL clickCartButton() {
        assertThat(AmazonCartPageScreen.get().clickCartButton()).as(currentUserPersona + "Add to cart button is not clicked")
                .isTrue();
        return this;
    }

    public CartPageBL verifyAddedProductIsReflectionInCart() {
        Boolean isProductAddedToTheCart = AmazonCartPageScreen.get().verifyAddedProductIsReflectionInCart();
        assertThat(isProductAddedToTheCart).as(currentUserPersona + "Added product is not reflected in the Cart page")
                .isTrue();
        return this;
    }
}
