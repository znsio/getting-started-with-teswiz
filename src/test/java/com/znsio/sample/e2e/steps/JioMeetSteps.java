package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.jiomeet.AuthBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import java.util.Map;

public class JioMeetSteps {
    private static final Logger LOGGER = Logger.getLogger(JioMeetSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public JioMeetSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                                                               .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I sign in as a registered {string}")
    public void iSignInAsARegistered(String userSuffix) {
        Map userDetails = Runner.getTestDataAsMap(userSuffix);
        LOGGER.info(System.out.printf("iSignInAsARegistered - Persona:'%s', User details: '%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, userDetails, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        context.addTestState(SAMPLE_TEST_CONTEXT.ME, String.valueOf(userDetails.get("username")));
        new AuthBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).signIn(userDetails);
    }

    @And("I start an instant meeting")
    public void iStartAnInstantMeeting() {
    }

    @When("I {string} myself")
    public void iMyself(String arg0) {
    }

    @Then("I should be able to {string} myself")
    public void iShouldBeAbleToMyself(String arg0) {
    }
}
