package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageBL {

    private static final Logger LOGGER = Logger.getLogger(HomePageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    public ProductPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }
    public ProductPageBL viewFirstProduct() {
        LOGGER.info("Selecting the first product from search results");
        AmazonProductScreen.get().viewProduct();
        return this;
    }
    public ProductPageBL verifyProductDetails() {
        LOGGER.info("Verifying the correct product");
        assertThat(AmazonProductScreen.get().isProductPresent()).as("Correct product is selected").isTrue();
        return this;
    }
}
