package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonProductDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;

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
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public AmazonProductDetailsBL verifyProductDetails(){
        AmazonSearchResultsScreen amazonSearchResultsScreen = AmazonSearchResultsScreen.get();
        String expectedName = amazonSearchResultsScreen.getFirstItemName();
        String expectedCost = amazonSearchResultsScreen.getFirstItemCost();

        AmazonProductDetailsScreen amazonProductDetailsScreen = AmazonProductDetailsScreen.get();

        String actualName = amazonProductDetailsScreen.getActualItemName();
        String actualCost = amazonProductDetailsScreen.getActualItemCost();

        softly.assertThat(actualName).as("Product name on detail page is different").isEqualTo(expectedName);
        softly.assertThat(actualCost).as("Product cost on detail page is different").isEqualTo(expectedCost);
        System.out.println("Searched String" + " " + context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD));
        assertThat(actualName.contains(context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD))).as("Product item is different from searched string").isTrue();

        return this;
    }

    public AmazonProductDetailsBL addToCart(){

        AmazonProductDetailsScreen amazonProductDetailsScreen = AmazonProductDetailsScreen.get();
        amazonProductDetailsScreen.addToCart();
        assertThat(amazonProductDetailsScreen.isItemAddedToCartTextVisible()).as("Add To Cart text is not visible").isTrue();

        return this;
    }
}
