package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonShoppingCartScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;

import java.util.List;

public class ShoppingCartBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonHomepageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ShoppingCartBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ShoppingCartBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public void verifyProductIsPresentInShoppingCart(String product){
        List<String> productTitles = AmazonShoppingCartScreen.get().getTitleOfAllProductsInShoppingCart();
        boolean isProductPresent = false;

        for(String productTitle: productTitles){
            if(productTitle.toLowerCase().contains(product)) {
                isProductPresent = true;
                break;
            }
        }
        LOGGER.info(System.out.printf("Is product present in shopping cart ? : %b", isProductPresent));
        Assert.assertTrue(isProductPresent, "The product is not present in shopping cart");
    }

}
