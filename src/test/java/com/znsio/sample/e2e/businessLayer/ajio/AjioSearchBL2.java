package com.znsio.sample.e2e.businessLayer.ajio;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio2.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio2.AjioSearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AjioSearchBL2 {
    private static final Logger LOGGER = Logger.getLogger(AjioSearchBL2.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AjioSearchBL2(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AjioSearchBL2() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AjioSearchBL2 searchFor(String product) {
        AjioSearchResultsScreen ajioSearchResultsScreen = AjioHomeScreen.get()
                .searchFor(product);
        String actualSearchWasFor = ajioSearchResultsScreen.getActualSearchString();
        softly.assertThat(actualSearchWasFor).as("Search was for a different value").isEqualToIgnoringCase(product);

        int numberOfProductsFound = ajioSearchResultsScreen
                .getNumberOfProductsFound();
        assertThat(numberOfProductsFound).as("Insufficient search results retrieved")
                .isGreaterThan(100);
        return this;
    }
}
