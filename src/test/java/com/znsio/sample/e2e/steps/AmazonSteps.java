package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.*;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
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
        LOGGER.info(System.out.printf("iloggedIn - Persona: '%s'",SAMPLE_TEST_CONTEXT.ME ));
        new AmazonLoginBL().loginToAmazon();
        LOGGER.info("User is on Homepage");
    }

    @When("I search for iphone")
    public void iSearchForIphone() {
        new HomePageBL().searchForIphone13();
        LOGGER.info("I search for iphone13");

    }

    @Then("I should be able to see that iphone list")
    public void iShouldBeAbleToSeeThatIphoneList() {
        new SearchResultPageBL().iSeeTheListOfIphone13();
        LOGGER.info("I see the list of iphone13");

    }

    @When("I add the iphone in cart")
    public void iAddTheIphoneInCart() {
        new iphoneDetailPageBL()
                .authenticateIphone13DetailPage()
                .addToCart();
        LOGGER.info("iphone13 added to cart");
    }

    @Then("iphone should be added to cart")
    public void iphoneShouldBeAddedToCart() {
        new cartPageBL()
                .iSeeTheIphone13SuccessfullyAddedToCart();
        LOGGER.info("iphone13 successfully added to cart");
    }


}
