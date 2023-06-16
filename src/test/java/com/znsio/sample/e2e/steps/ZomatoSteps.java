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

    @When("I click on dining option")
    public void clickOnDiningOption() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).clickOnDiningOption();
    }

    @Then("I should be redirected to dine-out page")
    public void verifyRedirectionToDineoutPage() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).verifyRedirectionToDineoutPage();
    }

    @When("I select location as {string}")
    public void selectLocationForRestaurants(String location) {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).selectLocationForRestaurants(location);
    }

    @Then("I should get location selected as {string}")
    public void verifySelectedLocation(String location) {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).verifySelectedLocation(location);
    }

    @When("I select restaurant number 3 from results")
    public void selectSpecificRestaurant() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).selectSpecificRestaurant();
    }

    @Then("I should get same restaurant which was selected")
    public void verifySelectedRestaurant() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).verifySelectedRestaurant();
    }

    @When("I try to book a table for 4 guests on a date day after tomorrow")
    public void bookTableForGuestDayForASpecificDate() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).bookTableForGuestDayForASpecificDate();
    }

    @Then("I should get login pop up message")
    public void verifyLoginPopUpMessage() {
        new ZomatoBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).verifyLoginPopUpMessage();
    }
}
