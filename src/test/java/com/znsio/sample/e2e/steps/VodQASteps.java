package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.vodqa.VodqaBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.entities.Direction;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class VodQASteps {

    private static final Logger LOGGER = Logger.getLogger(VodQASteps.class.getName());
    private final TestExecutionContext context;

    public VodQASteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
    }

    @Given("I login to vodqa application using valid credentials")
    public void loginToApplication() {
        Drivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform(), context);
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).login();
    }


    @Then("App should work in background for {int} sec")
    public void appShouldWorkInBackgroundForDefinedTime(int time) {
        new VodqaBL().verifyAppWorksInBackground(time);
    }

    @Then("I am able to view hacker news login button inside web view section")
    public void iAmAbleToViewHackerNewsLoginButtonInsideWebViewSection() {
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).enterAndVerifyLoginOptionUnderWebViewSection();
    }

    @And("I am able to view section header by navigating inside native view section")
    public void iAmAbleToViewSectionHeaderByNavigatingInsideNativeViewSection() {
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).enterIntoNativeViewSection();
    }

    @When("I swipe left on {string} screen")
    public void selectScreenAndSwipeLeft(String screenName) {
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).selectScreenAndSwipeLeft(screenName);
    }

    @When("I swipe right on {string} screen")
    public void selectScreenAndSwipeRight(String screenName) {
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).selectScreenAndSwipeRight(screenName);
    }

    @Then("I am able to see element with text {string} on the screen")
    public void iAmAbleToSeeElementWithTextOnTheScreen(String elementText) {
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).verifySwipe(elementText);
    }

    @When("I swipe at {int} percent height from {int} percent width to {int} percent width on {string} screen")
    public void iSwipeAtPercentHeightFromPercentWidthToPercentWidthOnScreen(int atPercentileHeight, int fromPercentageWidth, int toPercentageWidth, String screenName) {
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform())
                .selectScreenAndSwipeByPassingPercentageAttributes(atPercentileHeight, fromPercentageWidth, toPercentageWidth, screenName);
    }

    @Then("Element text {string} should be visible")
    public void elementTextShouldBeVisible(String elementText) {
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).isElementWithTextVisible(elementText);
    }

    @When("I scroll {string} in dynamic layer on vertical swiping screen")
    public void iScrollInDynamicLayerOnVerticalSwipingScreen(String direction) {
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform())
                .scrollInDynamicLayerOnVerticalSwipingScreen(Direction.valueOf(direction.toUpperCase()));
    }
}
