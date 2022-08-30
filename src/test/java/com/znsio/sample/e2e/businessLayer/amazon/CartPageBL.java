package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.businessLayer.jiomeet.LandingBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonCartPageScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonGridWallPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPageBL {
    private static final Logger LOGGER = Logger.getLogger(LandingBL.class.getName());
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
        AmazonCartPageScreen.get().clickCartButton();
        return this;
    }

    public CartPageBL verifyAddedProductIsReflectionInCart(String expectedProductDesc) {
        Boolean isProductAddedToTheCart = AmazonCartPageScreen.get().verifyAddedProductIsReflectionInCart(expectedProductDesc);
        assertThat(isProductAddedToTheCart).as(currentUserPersona + "Added product is not reflected in the Cart page")
                .isTrue();
        return this;
    }
}
