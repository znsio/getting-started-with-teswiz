package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.indigo.IndigoFlightDetailsBL;
import com.znsio.sample.e2e.businessLayer.indigo.IndigoHomePageBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.*;
import org.apache.log4j.Logger;

public class IndigoSteps {

    private static final Logger LOGGER = Logger.getLogger(IndigoSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public IndigoSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }


    @Given("I am in the landing page")
    public void iAmInTheLandingPage() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        LOGGER.info("Browser is successfull opened");
        new IndigoHomePageBL()
                .isLandingPageOpened();
    }

    @When("I add arrival and departure details with dates")
    public void iAddArrivalAndDepartureDetailsWithDates() {
        new IndigoHomePageBL()
                .addArrivalDepartureDetails();
    }

    @And("I should be able to see the flight details")
    public void iShouldBeAbleToSeeTheFlightDetails() {
        new IndigoFlightDetailsBL()
                .isFlightDetailsAvailable();
    }

    @Then("I should be able to add filter for departure time")
    public void iShouldBeAbleToAddFilterForDepartureTimeGreaterThanHrs() {
        new IndigoFlightDetailsBL()
                .applyFilters();
    }
}
