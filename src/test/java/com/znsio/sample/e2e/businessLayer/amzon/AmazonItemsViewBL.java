package com.znsio.sample.e2e.businessLayer.amzon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonItemsDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonItemsViewScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class AmazonItemsViewBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonItemsViewBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;


    public AmazonItemsViewBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonItemsDetailsScreen viewItemByPosition(String position) {
        AmazonItemsViewScreen.get().clickOnItemByPosition(position);
        AmazonItemsDetailsScreen amazonItemsDetailsScreen = AmazonItemsDetailsScreen.get();
        String actualScreenTitle = amazonItemsDetailsScreen.getScreenTitle();
        String expectedItemTitle = context.getTestStateAsString("itemTitle");
        Assertions.assertThat(actualScreenTitle).as("Search Result screen did not navigate to Items details screen")
                .contains(expectedItemTitle);
        String actualProductTitleString = amazonItemsDetailsScreen.getItemsTitle();
        Assertions.assertThat(actualProductTitleString).as(String.format("On item details screen expected item title '%s' but found '%s'",expectedItemTitle,actualScreenTitle))
                .isEqualTo(expectedItemTitle);
        return amazonItemsDetailsScreen;
    }
}
