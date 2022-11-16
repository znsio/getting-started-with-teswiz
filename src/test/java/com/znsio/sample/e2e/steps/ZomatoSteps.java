package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.ZomatoCityPageBL;
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

    @Given("I am on the zomato homepage")
    public void iAmOnTheZomatoHomepage() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        LOGGER.info("Validating Zomato Homepage");
        new ZomatoHomePageBL().validateHomepage();
    }

    @And("I select {string} location")
    public void iShouldBeAbleToSelectLocation(String location) {
        new ZomatoHomePageBL().selectLocation(location);
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
    public void iSearchForTheResturant(String resturant) {
        new ZomatoCityPageBL().selectResturant(resturant);
    }

    @Then("I can see the invalid resturant name error message")
    public void iCanSeeTheInvalidResturantErrorMessage() {
        new ZomatoCityPageBL().validateInvalidResturantErrorMessage;
    }

    @Then("I should see querry warning")
    public void itShouldNotAppearOnTheList() {
        new ZomatoCityPageBL().validateResturantSearch();
    }

    @Then("I should see location disbaled message")
    public void iCanSeeTheLocationErrorMessage() {
        new ZomatoHomePageBL().validateLocationErrorMessage();
    }

    @When("I select {string} for {string}")
    public void iSearchForADishFor(String dish, String foodStatus) {
        new ZomatoCityPageBL().validateDishStatus(dish, foodStatus);
    }

    @Then("I can see the resturant details which deliver {string}")
    public void iCanSeeTheResturantDetailsWhichDeliver(String dish) {
        new ZomatoDishPageBL().validateResturantDetails(dish);
    }

    @Then("I should see resturant list")
    public void iCanSeeTheResturantDetailsWhereICanDineout() {
        new ZomatoDishPageBL().validateResturantList(dish);
    }

}
