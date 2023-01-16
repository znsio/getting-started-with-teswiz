package com.znsio.sample.e2e.businessLayer.amzon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.businessLayer.ajio.AjioSearchBL;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonItemsViewScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

public class AmazonHomeBL {
    private static final Logger LOGGER = Logger.getLogger(AjioSearchBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonHomeBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonHomeBL doItemSearch(String itemName) {
        AmazonItemsViewScreen amazonItemsViewScreen = AmazonHomeScreen.get()
                .enterItemNameInSearchbarAndPressEnter(itemName);
        String actualSearchWasFor = amazonItemsViewScreen.getSearchString();
        List<String> actualItemTitles = amazonItemsViewScreen.getItemTitles();
        softly.assertThat(actualSearchWasFor).as(String.format("Expected search was for '%s' but found '%s'",itemName,actualSearchWasFor)).contains(itemName);
        Assertions.assertThat(actualItemTitles).as(String.format("Results are not displayed as per searched: '%s'",itemName))
                .allMatch(actualItemTitle->actualItemTitle.contains(itemName));
        return this;
    }
}
