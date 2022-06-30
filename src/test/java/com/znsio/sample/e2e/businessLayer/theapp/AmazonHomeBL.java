package com.znsio.sample.e2e.businessLayer.theapp;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.theapp.AmazonLoginScreen;
import com.znsio.sample.e2e.screen.theapp.AmazonResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.junit.Assert.assertEquals;

public class AmazonHomeBL {
    private static final Logger LOGGER = Logger.getLogger(AmazonHomeBL.class.getName());
    private final TestExecutionContext context;

    public static String LoginMessage="Hello, Mohammed";
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonHomeBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonHomeBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }
    public AmazonResultsScreen AmazonLogin(String username, String password) {
        AmazonResultsScreen amazonLoginScreen = AmazonLoginScreen.get().AmazonEnterLoginDetails(username, password).login();
        return AmazonResultsScreen.get();
    }
    public AmazonResultsScreen loginVerification()
    {
        String ActualLoginMessage=AmazonLoginScreen.get().GetLoginMessage();
        assertEquals(LoginMessage,ActualLoginMessage);
        LOGGER.info(System.out.printf("Successfully Logged in"));
        return AmazonResultsScreen.get();
    }




}
