package com.znsio.sample.e2e.businessLayer.ajioOperator;

import com.context.TestExecutionContext;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorHomeScreen;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorLoginScreen;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class OperatorLoginBL extends SAMPLE_TEST_CONTEXT {

    private static final Logger LOGGER = Logger.getLogger(OperatorLoginBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public OperatorLoginBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }
    public OperatorLoginBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }


    public OperatorHomeBL logOnSellerPage(String userName, String password) {
        LOGGER.info(System.out.printf("Operator on seller page with userName %s: password %s:", userName,password));
        OperatorLoginScreen.get().loginToSellerPage(userName, password);
        //implement assertion
        return new OperatorHomeBL();
    }

    public OperatorLoginBL verifyPageTitle() {
        OperatorHomeScreen.get().logOff();
        //implement assertion
        return this;
    }
}
