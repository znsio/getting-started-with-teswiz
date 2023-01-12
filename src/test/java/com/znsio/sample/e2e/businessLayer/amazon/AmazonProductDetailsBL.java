package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonProductDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class AmazonProductDetailsBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonProductDetailsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonProductDetailsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonProductDetailsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public void verifyProductDetails(){
        AmazonSearchResultsScreen amazonSearchResultsScreen = AmazonSearchResultsScreen.get();
        String expectedName = amazonSearchResultsScreen.getFirstItemName();
        String expectedCost = amazonSearchResultsScreen.getFirstItemCost();

        AmazonProductDetailsScreen amazonProductDetailsScreen = AmazonProductDetailsScreen.get();

        String actualName = amazonProductDetailsScreen.getActualItemName();
        String actualCost = amazonProductDetailsScreen.getActualItemCost();

        softly.assertThat(actualName).as("Product name on detail page is different").isEqualTo(expectedName);
        softly.assertThat(actualCost).as("Product cost on detail page is different").isEqualTo(expectedCost);
    }

    public AmazonCartBL addToCart(){

        AmazonProductDetailsScreen amazonProductDetailsScreen = AmazonProductDetailsScreen.get();
        amazonProductDetailsScreen.addToCart();
        softly.assertThat(amazonProductDetailsScreen.isItemAddedToCartTextVisible()).as("Add To Cart text is not visible");

        return new AmazonCartBL(currentUserPersona,currentPlatform);
    }
}
