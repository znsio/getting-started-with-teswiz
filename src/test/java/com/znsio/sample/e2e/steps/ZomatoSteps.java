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

    @And("I should be able to select location - {string}")
    public void iShouldBeAbleToSelectLocation(String location) {
        new ZomatoHomePageBL().selectLocation(location);
    }

    @When("I search for the nearby resturant - {string}")
    public void iSearchForTheNearbyResturant(String resturant) {
        new ZomatoCityPageBL().selectResturant(resturant);
    }

    @Then("I can see that resturant details")
    public void iCanSeeThatResturantDetails() {
        new ZomatoResturantPageBL().validateResturantPage();
    }

    @And("I should be able to select my location by using detect current location")
    public void iShouldBeAbleToSelectMyLocationByUsingDetectCurrentLocation() {
        new ZomatoHomePageBL().selectFromDetectLocation();
    }

    @When("I search for the {string} resturant")
    public void iSearchForTheResturant(String resturant) {
        new ZomatoCityPageBL().selectResturant(resturant);
    }

    @When("I search for the resturant with invalid name - {string}")
    public void iSearchForTheResturantWithInvalidName(String invalidResturantName) {
        new ZomatoCityPageBL().validateInvalidResturantName(invalidResturantName);
    }

    @Then("I can see the invalid resturant name error message")
    public void iCanSeeTheInvalidResturantErrorMessage() {
        new ZomatoCityPageBL().validateInvalidResturantErrorMessage;
    }

    @When("I search for the resturant of other location - {string} {string}")
    public void iSearchForTheResturantOfOtherLocation(String otherLocationResturant, String currentLocation) {
        new ZomatoCityPageBL().searchForOtherLocationResturant(currentLocation, otherLocationResturant);
    }

    @Then("It should not appear on the list")
    public void itShouldNotAppearOnTheList() {
        new ZomatoCityPageBL().validateResturantSearch();
    }

    @Then("I can see the location error message")
    public void iCanSeeTheLocationErrorMessage() {
        new ZomatoHomePageBL().validateLocationErrorMessage();
    }

    @When("I search for a dish {string} for {string}")
    public void iSearchForADishFor(String dish, String foodStatus) {
        new ZomatoCityPageBL().validateDishStatus(dish, foodStatus);
    }

    @Then("I can see the resturant details which deliver {string}")
    public void iCanSeeTheResturantDetailsWhichDeliver(String dish) {
        new ZomatoDishPageBL().validateResturantDetails(dish);
    }

    @When("I search for a dish {string} for {string}")
    public void iSearchForADishFor(String dish, String foodStatus) {
        new ZomatoCityPageBL().validateDishStatus(dish, foodStatus);
    }

    @Then("I can see the resturant details where I can dineout {string}")
    public void iCanSeeTheResturantDetailsWhereICanDineout(String dish) {
        new ZomatoDishPageBL().validateResturantDetails(dish);
    }
}
