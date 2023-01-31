package com.znsio.sample.e2e.businessLayer.amzon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AMAZON_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ItemsDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class SearchResultsBL {
    private static final Logger LOGGER = Logger.getLogger(SearchResultsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;


    public SearchResultsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = AMAZON_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public SearchResultsBL selectItem(String position) {
        SearchResultsScreen searchResultsScreen = SearchResultsScreen.get();
        String itemTitle = searchResultsScreen.getItemText(position);
        LOGGER.info(String.format("Clicking on item title: '%s' present at position '%s'", itemTitle,position));
        context.addTestState(AMAZON_TEST_CONTEXT.ITEM_TITLE, itemTitle);
        ItemsDetailsScreen itemsDetailsScreen = searchResultsScreen.clickOnItemByPosition(position);
        Assertions.assertThat(itemsDetailsScreen.isScreenLoaded()).as("Items Details screen not loaded").isTrue();
        String actualItemTitle = itemsDetailsScreen.getItemsTitle();
        Assertions.assertThat(actualItemTitle).as(String.format("On item details screen expected item title '%s' but found '%s'",itemTitle,actualItemTitle))
                .isEqualTo(itemTitle);
        return this;
    }
}
