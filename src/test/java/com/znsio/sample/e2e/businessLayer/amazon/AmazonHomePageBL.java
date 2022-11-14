package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import org.apache.log4j.Logger;
import static org.assertj.core.api.Assertions.assertThat;

public class AmazonHomePageBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonHomePageBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonHomePageBL(String userPersona, Platform forPlatform){
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonHomePageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public SearchResultListPageBL VerifyAmazonHomePageAndSearchForIphone13() {
        boolean verifyAmazonLogo = AmazonHomeScreen.get().verifyAmazonHomePage();
        assertThat(verifyAmazonLogo).isTrue();
        LOGGER.info("Have login to amazon Page");
        AmazonHomeScreen.get().searchIphone13();
        LOGGER.info("Have searched Iphone 13");
        return new SearchResultListPageBL();
    }
}
