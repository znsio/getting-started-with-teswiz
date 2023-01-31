package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.UnsupportedCommandException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AmazonSearchBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonSearchBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;



    public AmazonSearchBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonSearchBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonSearchBL selectFirstProduct() {
        LOGGER.info(System.out.printf("select the first Product in search "));
        AmazonSearchScreen.get().clickFirstProductOnSearchResult();
        return this;
    }

    public AmazonSearchBL verifyTheSelectedProduct() {
        LOGGER.info(System.out.printf("verifying the selected product"));
       String actualProductNameOnProductPage = AmazonProductScreen.get().getProductNameInProductScreen();
        String expectedProductName = SAMPLE_TEST_CONTEXT.PRODUCT_NAME;
        Assertions.assertThat(actualProductNameOnProductPage.matches("(.*)expectedProductName(.*)"));
        return this;
    }

    public AmazonSearchBL createProductToCart() {
        LOGGER.info(System.out.printf("Create the product cart"));
        AmazonProductScreen.get().click_AddToCart_Button();
         String expectedSuccessMsgForAddToCart = SAMPLE_TEST_CONTEXT.ADDCART_SUCCESS;
         String actualSuccessMsgForAddToCart = AmazonProductScreen.get().getAddToCartSuccessMessage();
       Assertions.assertThat(actualSuccessMsgForAddToCart).containsIgnoringCase(expectedSuccessMsgForAddToCart);
        return this;
    }
}
