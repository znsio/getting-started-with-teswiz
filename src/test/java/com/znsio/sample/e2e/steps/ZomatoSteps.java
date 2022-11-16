package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.ZomatoCityPageBL;
import com.znsio.sample.e2e.businessLayer.ZomatoDishPageBL;
import com.znsio.sample.e2e.businessLayer.ZomatoHomePageBL;
import com.znsio.sample.e2e.businessLayer.ZomatoResturantPageBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.*;
import org.apache.log4j.Logger;

public class ZomatoSteps {
    private static final Logger LOGGER = Logger.getLogger(ZomatoSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public ZomatoSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @When("I search for the nearby resturant - {string}")
    public void iSearchForTheNearbyResturant(String resturant) {
        new ZomatoCityPageBL().selectResturant(resturant);
    }

    @Then("I should see resturant details")
    public void iCanSeeThatResturantDetails() {
        new ZomatoResturantPageBL().validateResturantPage();
    }

    @And("I select location by using detect current location")
    public void iShouldBeAbleToSelectMyLocationByUsingDetectCurrentLocation() {
        new ZomatoHomePageBL().selectFromDetectLocation();
    }

    @When("I search for {string} resturant")
    public void iSearchForResturant(String resturant) {
        new ZomatoCityPageBL().selectResturant(resturant);
    }

    @Then("I should see querry warning")
    public void iShouldSeeWarning() {
        new ZomatoCityPageBL().validateResturantWarning();
    }

    @Then("I should see location disbaled message")
    public void iCanSeeTheLocationDisabledMessage() {
        new ZomatoHomePageBL().validateLocationDisabledMessage();
    }

    @When("I select {string} for {string}")
    public void iSearchForADishFor(String dish, String foodStatus) {
        new ZomatoCityPageBL().validateDishStatus(dish, foodStatus);
    }

    @Then("I should see resturant list")
    public void iCanSeeTheResturantDetailsWhereICanDineout() {
        new ZomatoDishPageBL().validateResturantList();
    }

    @Given("I,a {string} on zomato homepage and select {string} location")
    public void iAsAOnZomatoHomepageAndSelectLocation(String arg0, String location) {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        LOGGER.info("Validating Zomato Homepage");
        new ZomatoHomePageBL().selectLocation(location);
    }

    @Given("I as a {string} on zomato homepage and select detect current location")
    public void iAsAOnZomatoHomepageAndSelectDetectCurrentLocation(String arg0) {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        LOGGER.info("Validating Zomato Homepage");
        new ZomatoHomePageBL().selectFromDetectLocation();
    }
}
