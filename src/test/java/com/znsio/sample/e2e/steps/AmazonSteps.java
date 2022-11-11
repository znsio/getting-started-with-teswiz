package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.*;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonSteps {

    private static final Logger LOGGER = Logger.getLogger(AmazonSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }


    @Given("I logged in with valid credentials")
    public void iLoggedInWithValidCredentials() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new AmazonLoginBL().loginToAmazon();
    }

    @When("I search for iphone")
    public void iSearchForIphone() {
        new HomePageBL().searchForIphone13();
    }

    @And("I add the iphone in cart")
    public void iAddTheIphoneInCart() {
        new IphoneDetailPageBL()
                .addToCart();
    }

    @Then("iphone should be added to cart")
    public void iphoneShouldBeAddedToCart() {
        new cartPageBL()
                .validatingIphoneOnCart();
    }

}
