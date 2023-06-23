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
    private static final Logger LOGGER = Logger.getLogger(ZomatoSteps.class.getName());
    private final TestExecutionContext context;

    public ZomatoSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
    }

    @Given("I successfully launch homepage of Zomato web application")
    public void launchApplicationHomePage() {
        Drivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform(), context);
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).launchHomePageAndValidate();
    }

    @When("I select dining option")
    public void clickOnDiningOption() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).selectDiningOption();
    }

    @Then("I should be redirected to dine-out page")
    public void verifyRedirectionToDineoutPage() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).verifyRedirectionToDineoutPage();
    }

    @When("I select a specific location")
    public void selectLocationForRestaurant() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).selectLocationForRestaurant();
    }

    @Then("the same location should be displayed")
    public void verifySelectedLocationWithLocationDisplayed() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).verifySelectedLocationWithLocationDisplayed();
    }

    @When("I choose a restaurant from selected location")
    public void chooseSpecificRestaurant() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).chooseRestaurantFromSelectedLocation();
    }

    @Then("the same restaurant should be displayed")
    public void verifySelectedRestaurantWithRestaurantDisplayed() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).verifySelectedRestaurantWithRestaurantDisplayed();
    }

    @When("I try to book a table")
    public void bookATableOnASpecificDateAndTimeForGuests() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).bookATableOnASpecificDateAndTimeForGuests();
    }

    @Then("a login pop-up message should be displayed")
    public void verifyLoginPopUpMessage() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).verifyLoginPopUpMessage();
    }
}
