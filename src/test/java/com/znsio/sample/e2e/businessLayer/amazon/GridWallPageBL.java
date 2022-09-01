package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonGridWallPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;

public class GridWallPageBL {
    private static final Logger LOGGER = Logger.getLogger(GridWallPageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public GridWallPageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public GridWallPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public GridWallPageBL verifyTheResultsInTheProductListingPage() {
        Boolean isResultsTextPresent = AmazonGridWallPageScreen.get().checkForResultsHeader();
        assertThat(isResultsTextPresent).as(currentUserPersona + " Results text is not presents")
                .isTrue();
        Boolean isResultsSizeMoreThanOne = AmazonGridWallPageScreen.get().checkForProductResultsSize();
        assertThat(isResultsSizeMoreThanOne).as(currentUserPersona + " Products is not present in GridWallPage")
                .isTrue();
        return this;
    }

    public ProductPageBL selectFirstProductFromTheSearchResults() {
        assertThat(AmazonGridWallPageScreen.get().selectProduct()).as(currentUserPersona + "Not abled to select thr product in GridWallPage")
                .isTrue();
        return new ProductPageBL();
    }
}
