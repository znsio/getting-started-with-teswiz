package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.zomato.DineOutBL;
import com.znsio.sample.e2e.businessLayer.zomato.RestaurantDetailBL;
import com.znsio.sample.e2e.businessLayer.zomato.ZomatoHomePageBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class ZomatoSteps {
    private static final Logger LOGGER = Logger.getLogger(ZomatoSteps.class.getName());
    private final TestExecutionContext context;

    public ZomatoSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
    }
    @Given("I, as a User have set the location to {string} on the home page")
    public void iAsAUserHaveSetTheLocationToOnTheHomePage(String cityName) {
        LOGGER.info(String.format(
                "iAsAUserHaveSetTheLocationToOnTheHomePage - Persona:'%s', Platform: '%s'",
                SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()));
        Drivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform(), context);
        new ZomatoHomePageBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).selectDiningLocation(cityName);
    }

    @When("I book a table for {int} guests in a restaurant for {int} days ahead")
    public void iBookATableForGuestsInARestaurantForDaysAhead(int guestCount, int aheadDays) {
        new DineOutBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).selectRestaurant().enterBookingDetails(guestCount, aheadDays);
    }

    @Then("I am able to view login option")
    public void iAmAbleToViewLoginOption() {
        new RestaurantDetailBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).LoginPopUpWindow();
    }
}
