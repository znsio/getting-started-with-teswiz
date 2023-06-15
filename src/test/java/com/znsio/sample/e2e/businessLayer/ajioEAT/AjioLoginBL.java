package com.znsio.sample.e2e.businessLayer.ajioEAT;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AjioLoginBL {
    private static final Logger LOGGER = Logger.getLogger(AjioLoginBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AjioLoginBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AjioLoginBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public AjioLoginBL searchFor(String product) {
        AjioSearchResultsScreen ajioSearchResultsScreen = AjioHomeScreen.get().searchFor(product);
        String actualSearchWasFor = ajioSearchResultsScreen.getActualSearchString();
        softly.assertThat(actualSearchWasFor).as("Search was for a different value")
                .isEqualTo(product);

        int numberOfProductsFound = ajioSearchResultsScreen.getNumberOfProductsFound();
        assertThat(numberOfProductsFound).as("Insufficient search results retrieved")
                .isGreaterThan(100);
        return this;
    }

    public AjioLoginBL loginAsValidUser(Map userDetails) {
        assertThat(AjioHomeScreen.get().signInUser(
                        userDetails.get("emailId").toString(),
                        userDetails.get("password").toString())
                .isUserSignedIn())
                .as("User is not Signed In")
                .isTrue();
        return this;
    }

    public AjioProductBL logoutUser() {

        return new AjioProductBL();
    }
}
