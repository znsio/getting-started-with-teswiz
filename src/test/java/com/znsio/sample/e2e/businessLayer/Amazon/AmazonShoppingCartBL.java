package com.znsio.sample.e2e.businessLayer.Amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.businessLayer.notepad.NotepadBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonShoppingCartScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonShoppingCartBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonShoppingCartBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonShoppingCartBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
        LOGGER.info("AmazonSearchBL created");
    }

    public AmazonShoppingCartBL navigateToTheShoppingCart(){
        LOGGER.info(String.format("User navigate to the shopping cart"));
        boolean shoppingCart = AmazonShoppingCartScreen.get().navigateToTheShoppingCart().verifyTheShoppingCart();
        assertThat(shoppingCart).as("User Navigated to the shopping cart").isTrue();
        return this;
    }
    public AmazonShoppingCartBL VerifyTheProductIsVisibleInShoppingCart(String productName){
        LOGGER.info(String.format("Verify the product has been successfully added to the shopping cart"));
        String getProductName = AmazonShoppingCartScreen.get().verifyTheProductDetails();
        assertThat(getProductName).as("Verify the product is present in the shopping cart").containsIgnoringCase(productName);
        return this;
    }
}
