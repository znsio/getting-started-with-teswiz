package com.znsio.sample.e2e.businessLayer.amzon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AMAZON_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.HomeScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

public class HomeBL {
    private static final Logger LOGGER = Logger.getLogger(HomeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public HomeBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public HomeBL searchItem(String itemName) {
        context.addTestState(AMAZON_TEST_CONTEXT.ITEM_NAME, itemName);
        SearchResultsScreen searchResultScreen = HomeScreen.get()
                .enterItemNameInSearch(itemName)
                .pressEnter();
        Assertions.assertThat(searchResultScreen.isScreenLoaded()).as("Search result screen not loaded").isTrue();
        List<String> actualItemTitles = searchResultScreen.getItemTitles();
        softly.assertThat(actualItemTitles).as(String.format("Results are not displayed as per searched: '%s'", itemName))
                .allMatch(actualItemTitle->actualItemTitle.contains(itemName));
        return this;
    }
}
