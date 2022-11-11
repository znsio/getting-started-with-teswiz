package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.amazon.ProductDetailPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

public class ProductDetailPageBL {

    private static final Logger LOGGER = Logger.getLogger(ProductDetailPageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    Map<String, String> testData = Runner.getTestDataAsMap(System.getProperty("user.name"));
    String product = testData.get("item");


    public ProductDetailPageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ProductDetailPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }


    public ProductDetailPageBL addToCart() {
        LOGGER.info("Validating "+product+" detail page opened:-");
        boolean isProductAddedToCart = ProductDetailPageScreen
                .get()
                .addProductToCart();
        softly.assertThat(isProductAddedToCart).isTrue();
        return this;
    }
}
