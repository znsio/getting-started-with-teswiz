package com.znsio.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.businessLayer.CalcNewBL;
import com.znsio.e2e.businessLayer.CalculatorBL;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class CalcNewSteps {

    private static final Logger LOGGER = Logger.getLogger(CalcNewSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public CalcNewSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }
    @Given("{string} will start calculator")
    public void willStartCalculator(String userPersona) {
        LOGGER.info(System.out.printf("iStartTheCalculator - Persona:'%s'", userPersona));
        allDrivers.createDriverFor(userPersona, Runner.platform, context);
        new CalcNewBL(userPersona, Runner.platform).startCalculator();
    }

    @When("{string} will select {string}")
    public void willSelect(String userPersona, String inputValue) {
        LOGGER.info("Selecting value: "+inputValue+" from Calculator input pad");
        new CalcNewBL(userPersona, Runner.platform).selectValue(inputValue);
    }

    @When("{string} will press {string}")
    public void willPress(String userPersona, String operator) {
        LOGGER.info("Pressing Operator: "+operator+" from Calculator input pad");
        new CalcNewBL(userPersona, Runner.platform).performOperation(operator);
    }

    @Then("Screen should show {string}")
    public void screenShouldShow(String expectedValue) {
        LOGGER.info("Verifying screen displays expected value: "+expectedValue);
        new CalcNewBL().isValueMatching(expectedValue);
    }

    @Then("{string} should see {string} on screen")
    public void shouldSeeOnScreen(String userPersona, String expectedValue) {
        LOGGER.info("Verifying screen displays expected value for multi device test: "+expectedValue);
        new CalcNewBL(userPersona, Runner.platform).isValueMatching(expectedValue);
    }
}
