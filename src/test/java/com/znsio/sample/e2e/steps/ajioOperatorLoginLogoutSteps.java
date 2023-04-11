package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;

import com.znsio.e2e.entities.TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.ajioOperator.OperatorLoginBL;
import com.znsio.sample.e2e.businessLayer.ajioOperator.OperatorHomeBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class ajioOperatorLoginLogoutSteps {

    private static final Logger LOGGER = Logger.getLogger(ajioOperatorLoginLogoutSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public ajioOperatorLoginLogoutSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());

        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ME);
       // allDrivers = (Drivers) context.getTestState(TEST_CONTEXT.CURRENT_USER_PERSONA);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given( "I as a ajio operator on login page with {string} and {string}" )
    public void iAsAAjioOperatorOnLoginPageWithAnd(String userName, String password) {
        LOGGER.info(System.out.printf("operatorOnLoginPage - Persona:'%s', Platform: '%s', userName: '%s',password: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.platform, userName, password));
        new OperatorLoginBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).logOnSellerPage(userName, password);
    }

    @When( "operator land on seller page" )
    public void operatorLandOnSellerPage() {
        new OperatorHomeBL().logoutFromsellerPage();
    }

    @Then( "operator should logout" )
    public void operatorShouldLogout() {
        new OperatorLoginBL().verifyPageTitle();
    }

}
