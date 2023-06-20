package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.vodqa.VodqaBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
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

    @When("I scroll from one to another element point on vertical swiping screen")
    public void scrollToElement() {
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).scrollFromOneElementPointToAnother();
    }

    @Then("Element text {string} should be visible")
    public void elementTextShouldBeVisible(String elementText) {
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).isElementWithTextVisible(elementText);
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

    @When("I scroll down by screen size on vertical swiping screen")
    public void iScrollDownByScreenSizeOnVerticalSwipingScreen() {
        new VodqaBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).scrollDownByScreenSizeOnVerticalSwipingScreen();
    }

}
