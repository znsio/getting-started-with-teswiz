package com.znsio.sample.e2e.steps.BookMyShow;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.bms.BMSHomeBL;
import com.znsio.sample.e2e.businessLayer.bms.BMSMoviesBL;
import com.znsio.sample.e2e.businessLayer.bms.BMSSelectSeatsBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class BMSSteps {
    private static final Logger LOGGER = Logger.getLogger(BMSSteps.class.getName());
    private final TestExecutionContext context;

    public BMSSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
    }

    @Given("I, a guest user from {string} wants to see movie which is positioned at {string} on day after tomorrow")
    public void iAGuestUserFromWantsToSeeMovieWhichIsPositionedAtOnDayAfterTomorrow(String location, String moviePosition) {
        LOGGER.info(System.out.printf("iAGuestUserSearchForMovies - Persona:'%s', Platform: '%s'",SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()));
        Drivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform(), context);

        new BMSHomeBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).loginAsGuestUserForGivenLocation(location).selectMovieCategory();
        new BMSMoviesBL().selectMovie(moviePosition).selectDateAfter2Days();
    }

    @When("I select seats for {string} for the second last time slot in cinema hall present at position {string}")
    public void iSelectSeatsForForTheSecondLastTimeSlotInCinemaHallPresentAtPosition(String noOfSeats, String cinemaPosition) {
        new BMSMoviesBL().selectSecondLastTimeSlotForCinemaHallAtPosition(cinemaPosition).selectNoOfSeats(noOfSeats);

    }

    @Then("I check for availability of seats that should be more than {string} percent")
    public void iCheckForAvailabilityOfSeatsThatShouldBeMoreThanPercent(String validationPercentage) {
        new BMSSelectSeatsBL().validateAvailableSeatsPercentage(validationPercentage);
    }
}
