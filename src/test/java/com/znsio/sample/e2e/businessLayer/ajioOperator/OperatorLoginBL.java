package com.znsio.sample.e2e.businessLayer.ajioOperator;

import com.context.TestExecutionContext;

import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorHomeScreen;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorLoginScreen;

import com.znsio.sample.e2e.screen.ajioOperator.OperatorManageSellerScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OperatorLoginBL {

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
        this.currentPlatform = Runner.getPlatform();
    }

    public OperatorManageSellerBL operatorLoggedOnSellerCentralPage(String email, String password) {
        OperatorLoginScreen.get().loginToSellerCentralPage(email, password);
        LOGGER.info(String.format("Operator on seller page with email %s: password %s:",email,password));
        assertThat(OperatorManageSellerScreen.get().isManageSellerTitleDisplayed())
                .as("operator is not land on Manage Seller page").isTrue();
        return new OperatorManageSellerBL();
    }
}
