package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.ajio.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;


public class AmazonHomePageBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonHomePageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public AmazonHomePageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }
    public AmazonHomePageBL productSearch(String product) {
        AmazonHomeScreen amazonSearchResultsScreen = AmazonHomeScreen.get().searchProduct(product);
        String actualSearchOfProduct = amazonSearchResultsScreen.getActualSearchString();
        assertThat(actualSearchOfProduct).as("Search is for product: "+product)
                .isEqualTo(product);
        return this;

    }

}
