package com.znsio.sample.e2e.businessLayer.amazonCart;


import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AMAZON_ASSIGNMENT_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.CartLandingPageScreen;
import org.assertj.core.api.SoftAssertions;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertThat;


public class ShppingCartPageBL {

    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;


    public ShppingCartPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = AMAZON_ASSIGNMENT_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public void VerifyProductInCart(String productTitle, String productPrice) {
        boolean validateproductTitle = productTitle.equalsIgnoreCase(CartLandingPageScreen.get().getProductTitle());
        softly.assertThat(validateproductTitle).as(String.format("Product title does not match")).isTrue();
        boolean validateproductPrice = productPrice.equalsIgnoreCase(CartLandingPageScreen.get().getProductPrice());
        softly.assertThat(validateproductPrice).as(String.format("Product price does not match")).isTrue();
        boolean validateproductquantity = "1".equalsIgnoreCase(CartLandingPageScreen.get().getProductCount());
        softly.assertThat(validateproductquantity).as(String.format("Product price does not match")).isTrue();

        //eg- product not visible in cart at all- hard assertion
    }
}
