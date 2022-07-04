package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.entities.TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonHomeBL;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonSearchResultBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonCartSteps {
    private static final Logger LOGGER = Logger.getLogger(AmazonCartSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonCartSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("User is on HomePage")
    public void userIsOnHomePage() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        LOGGER.info(System.out.printf("userIsOnHomePage - Persona:'%s'", SAMPLE_TEST_CONTEXT.ME));
        new AmazonHomeBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).validateUserIsOnTheHomePage();
    }

    @When("User searches the {string}")
    public void userSearchesTheProduct(String productName) {
        new AmazonHomeBL().UserSearchsForProduct(productName);
    }

    @And("Adds First Product to Cart")
    public void addsFirstProductToCart() {
        new AmazonSearchResultBL().userSelectsFirstProductOnSearchResultPage()
                .userAddProductToCart();
        new AmazonHomeBL().UserGoesToTheCart();
    }

}
