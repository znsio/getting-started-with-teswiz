package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;


public class HomePageBL {

    private static final Logger LOGGER = Logger.getLogger(HomePageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public HomePageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }
    public HomePageBL productSearch(String product) {
        LOGGER.info(String.format("User searched for a product: '%s'", product));
        AmazonHomeScreen amazonSearchResultsScreen = AmazonHomeScreen.get().searchProductFromSearchbar(product);
        String actualTextInSearchBar = amazonSearchResultsScreen.getActualSearchText();
        LOGGER.info(String.format("Text present in searchbar: '%s'", actualTextInSearchBar));
        assertThat(actualTextInSearchBar).as("Searched product name is present: "+product)
                .isEqualTo(product);
        return this;
    }

}
