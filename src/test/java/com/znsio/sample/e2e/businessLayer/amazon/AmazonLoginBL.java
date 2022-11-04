package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonLogInScreen;
import org.apache.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonLoginBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonLoginBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonLoginBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonLoginBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonHomePageBL verifyLoginToAmazon(String username, String password) {
        boolean amazonHomePage = AmazonLogInScreen.get().isLoginToAmazonHomePage(username,password);
        assertThat(amazonHomePage).isTrue();
        return new AmazonHomePageBL();
    }

}