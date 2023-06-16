package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.zomato.ZomatoBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class ZomatoSteps {
    private static final Logger LOGGER = Logger.getLogger(JioMeetSteps.class.getName());
    private final TestExecutionContext context;

    public ZomatoSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
    }
    @Given("I, as a user should be on the zomato home page and able to select the {string} location")
    public void iAsAUserShouldBeOnTheZomatoHomePageAndAbleToSelectTheLocation(String cityName) {
        LOGGER.info(String.format(
                "iAsAUserShouldBeOnTheZomatoHomePageAndAbleToSelectTheLocation - Persona:'%s', Platform: '%s'",
                SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()));
        Drivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform(), context);
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).selectDiningLocation(cityName);
    }

    @Then("I am able to view login option")
    public void iAmAbleToViewLoginOption() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).verifyLoginOption();
    }

    @When("I book a table for {int} guests in a restaurant for {int} days ahead")
    public void iBookATableForGuestsInARestaurantForDaysAhead(int guestCount, int aheadDays) {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).bookATable(guestCount, aheadDays);
    }
}
