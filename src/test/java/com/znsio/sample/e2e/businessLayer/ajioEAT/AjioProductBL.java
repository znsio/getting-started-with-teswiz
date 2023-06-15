package com.znsio.sample.e2e.businessLayer.ajioEAT;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AjioProductBL {
    private static final Logger LOGGER = Logger.getLogger(AjioProductBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AjioProductBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AjioProductBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }


    public AjioProductBL wishlistTheProductFromSearchResult(int itemNumber) {
      AjioSearchResultsScreen ajioSearchResultsScreen =  AjioSearchResultsScreen.get();
      ajioSearchResultsScreen.goToProductDetails(itemNumber).wishlistTheProduct();


        return this;
    }

    public AjioProductBL moveTheProductToCart() {

        return this;
    }

    public AjioProductBL removeProductFromCart() {
        return this;
    }

    public AjioProductBL verifyCartIsEmpty() {
        return this;
    }
}
