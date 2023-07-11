package com.znsio.sample.e2e.businessLayer.ajio;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.AjioProductScreen;
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


    public AjioProductBL selectTheFirstResultFromList() {
        assertThat(AjioSearchResultsScreen.get()
                .selectProduct()
                .isProductDetailsLoaded())
                .as("Product Details is not loaded")
                .isTrue();
        return this;
    }

    public AjioProductBL flickAndViewImages() {
        String finalElementId = AjioProductScreen.get().flickImage().isElementIdChanged();
        String initialElementId = (String) context.getTestState(SAMPLE_TEST_CONTEXT.INITIAL_ELEMENT_ID);
        assertThat(initialElementId).as("Unable to perform flick action").isNotEqualTo(finalElementId);
        return this;
    }
}