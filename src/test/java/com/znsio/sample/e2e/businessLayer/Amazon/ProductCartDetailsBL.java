package com.znsio.sample.e2e.businessLayer.Amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.AmazonCart.ProductCartDetailsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductCartDetailsBL {
    private static final Logger LOGGER = Logger.getLogger(ProductCartDetailsBL.class.getName());

    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ProductCartDetailsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ProductCartDetailsBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public ProductCartDetailsBL verifyTheProductInTheCart(){
        String productName = context.getTestState("ExactProductName").toString();
        assertThat(ProductCartDetailsScreen.get().getProductTitleFromCart()).as(String.format("Product in cart should be '%s'", productName))
                .contains(productName);
        String itemsInCartBefore = "1";
        assertThat(ProductCartDetailsScreen.get().getNoOfItemsInCart()).isEqualTo(itemsInCartBefore);
        LOGGER.info(System.out.printf("Item '%s' is present in the cart",productName));
        return this;
    }


}
