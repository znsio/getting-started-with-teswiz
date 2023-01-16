package com.znsio.sample.e2e.businessLayer.Amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.businessLayer.notepad.NotepadBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonProductViewScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonHomeBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonHomeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonHomeBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
        LOGGER.info("AmazonSearchBL created");
    }

    public AmazonHomeBL searchProduct(String productName){
        LOGGER.info(String.format("User search product in search option '%s'", productName));
        AmazonProductViewScreen amazonProductViewScreen = AmazonHomeScreen.get().searchProduct(productName);
        String actualProductName = amazonProductViewScreen.get().firstProductName();
        assertThat(actualProductName).as("The searched product name is present").containsIgnoringCase(productName);
        return this;
    }
}
