package com.znsio.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.businessLayer.amazon.*;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import org.apache.log4j.Logger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AmazonSteps {
    private static final Logger LOGGER = Logger.getLogger(AmazonSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("User is logged-in and is on {string}")
    public void userIsLoggedInAndIsOn(String page) {
        LOGGER.info(System.out.printf("iLoggedInAndOnHomePage - Persona:'%s'", SAMPLE_TEST_CONTEXT.ME));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new AmazonLoginSignUpBL().login();
        new AmazonLoginSignUpBL().navigateToPage(page);
    }

    @When("User searches for {string}")
    public void userSearchesFor(String keyWord) {
        new AmazonHomeBL().searchForItem(keyWord);
        new AmazonProductListingBL().verifySearchedResults(keyWord);
    }

    @And("User adds a product to cart")
    public void userAddsAProductToCart() {
        new AmazonProductListingBL().selectAnyProductFromListingPage();
        new AmazonProductDetailsBL().validateProductOnDetailsPage();
        new AmazonProductDetailsBL().addToCart();
    }

    @Then("Product is added to the cart")
    public void productIsAddedToTheCart() {
        new AmazonCartBL().verifyCart();
    }

}
