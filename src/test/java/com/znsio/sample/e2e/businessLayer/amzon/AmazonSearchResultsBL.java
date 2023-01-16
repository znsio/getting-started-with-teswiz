package com.znsio.sample.e2e.businessLayer.amzon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonItemsDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class AmazonSearchResultsBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonSearchResultsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;


    public AmazonSearchResultsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonItemsDetailsScreen viewItemByPosition(String position) {
        String actualScreenTitle = AmazonSearchResultsScreen.get()
                .clickOnItemByPosition(position)
                .getScreenTitle();
        LOGGER.info(String.format("Actual Item details screen title: '%s'", actualScreenTitle));
        String expectedItemTitle = context.getTestStateAsString("itemTitle");
        Assertions.assertThat(actualScreenTitle).as("After selecting item expected screen title '%s' but found '%s'",expectedItemTitle,actualScreenTitle)
                .contains(expectedItemTitle);
        AmazonItemsDetailsScreen amazonItemsDetailsScreen = AmazonItemsDetailsScreen.get();
        String actualProductTitleString = amazonItemsDetailsScreen.getItemsTitle();
        Assertions.assertThat(actualProductTitleString).as(String.format("On item details screen expected item title '%s' but found '%s'",expectedItemTitle,actualScreenTitle))
                .isEqualTo(expectedItemTitle);
        return amazonItemsDetailsScreen;
    }
}
