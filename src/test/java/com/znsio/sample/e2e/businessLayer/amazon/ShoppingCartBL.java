package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonShoppingCartScreen;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.List;

public class ShoppingCartBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonHomePageBL.class.getName());
    private final TestExecutionContext context;

    public ShoppingCartBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ShoppingCartBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
    }

    public ShoppingCartBL verifyShoppingCart() {

        String productToVerify = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD);
        List<String> productTitles = AmazonShoppingCartScreen.get().getTitleOfAllProductsInShoppingCart();
        LOGGER.info("Checking if the product is present in the shopping cart");

        boolean isProductPresent = false;
        for (String productTitle : productTitles) {
            if (productTitle.toLowerCase().contains(productToVerify)) {
                isProductPresent = true;
                break;
            }
        }
        Assert.assertTrue(isProductPresent, "The product is not present in shopping cart");
        return this;
    }

}
