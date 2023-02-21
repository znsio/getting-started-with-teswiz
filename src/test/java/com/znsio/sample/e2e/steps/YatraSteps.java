package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.Yatra.HomeBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class YatraSteps {

    private static final Logger LOGGER = Logger.getLogger(AjioSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public YatraSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }
    @Given("I, as guest user, search for a {string} trip with first flight from {string} with destination {string}")
    public void iAsGuestUserSearchForAFightFromSourceAndDestination(String tripType, String sourceCity, String destinationCity) {
        LOGGER.info(String.format("iAsGuestUserSearchForAFightFromSourceAndDestination - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        new HomeBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).addFirstTrip(tripType, sourceCity, destinationCity);
    }

    @And("I select flight from {string} with destination {string} for the second trip")
    public void iSelectSourceAndDestinationForTheRoundTrip(String sourceCity, String destinationCity) {
        LOGGER.info(String.format("iSelectSourceAndDestinationForTheRoundTrip - select source city '%s' and destination city '%s' for second trip", sourceCity, destinationCity));
        new HomeBL().addSecondTrip(sourceCity, destinationCity);
    }

    @When("I add {int} adults, {int} children and {int} infant passengers")
    public void iAddAdultsChildrenAndInfantPassengers(int adultCount, int childrenCount, int infantCount) {
        LOGGER.info("iAddAdultsChildrenAndInfantPassengers - Add passengers for the trip");
        new HomeBL().addPassengers(adultCount, childrenCount, infantCount);
    }

    @Then("I choose {string} class category {string} flight")
    public void thenIChooseClassCategoryFlight(String flightClass, String flightType) {
        LOGGER.info("thenIChooseClassCategoryFlight - Select class and flight category");
        new HomeBL().selectCategory(flightClass, flightType).searchFlights();
    }
}
