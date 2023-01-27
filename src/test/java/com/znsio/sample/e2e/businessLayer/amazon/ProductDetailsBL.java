package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductDetailsBL {

    private static final Logger LOGGER = Logger.getLogger(ProductDetailsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ProductDetailsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ProductDetailsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public ProductDetailsBL verifyProductDetails(){

        String expectedName = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.PRODUCT_NAME);
        String expectedCost = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.PRODUCT_COST);

        ProductDetailsScreen productDetailsScreen = ProductDetailsScreen.get();

        String actualName = productDetailsScreen.getActualProductName();
        String actualCost = productDetailsScreen.getActualProductCost();

        softly.assertThat(actualName).as("Product name on detail page is different").isEqualTo(expectedName);
        softly.assertThat(actualCost).as("Product cost on detail page is different").isEqualTo(expectedCost);

        LOGGER.info(String.format("Searched Product Context '%s'",context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD)));
        assertThat(actualName.contains(context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD))).as("Product item is different from searched string").isTrue();

        return this;
    }

    public ProductDetailsBL createCart(){

        ProductDetailsScreen productDetailsScreen = ProductDetailsScreen.get();
        productDetailsScreen.addToCart();
        assertThat(productDetailsScreen.isItemAddedToCartTextVisible()).as("Add To Cart text is not visible").isTrue();

        return this;
    }
}
