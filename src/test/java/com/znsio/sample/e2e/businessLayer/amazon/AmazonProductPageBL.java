package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonProductPageBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonHomePageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    public AmazonProductPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }
    public AmazonProductPageBL viewFirstProduct() {
        LOGGER.info(String.format("Selecting the first product from search results"));
        AmazonProductScreen.get().viewProduct();
        return this;
    }
    public AmazonProductPageBL verifyProductDetails() {
        LOGGER.info(String.format("Verifying the correct product"));
       boolean isProductDetailsCorrect= AmazonProductScreen.get().isCorrectProduct();
        assertThat(isProductDetailsCorrect).as("Correct product is selected").isTrue();
        return this;
    }
}
