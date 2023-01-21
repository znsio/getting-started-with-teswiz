package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonHomeBL;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonSearchBL;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonCartBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonSearchSteps {

    private static final Logger LOGGER = Logger.getLogger(AmazonSearchSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSearchSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I as a guest search {string} on Amazon HomePage")
    public void iAsAGuestSearchOnAmazonHomePage(String productName) {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        LOGGER.info(System.out.printf("AsGuestUserOnAmazonSearchInHomePage - Persona:'%s', Platform: '%s', ProductName: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.platform,productName));
       new AmazonHomeBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).searchTheProduct(productName);
    }
    @When("Guest should select the first product in search result")
    public void guestShouldSelectTheFirstProductInSearchResult() {
        new AmazonSearchBL().selectFirstProduct();
    }

    @Then("Guest see the first product in the product page")
    public void guestSeeTheFirstProductInTheProductPage() {
        new AmazonSearchBL().verifyTheSelectedProduct();
    }

    @When("Guest adds the product to the cart")
    public void guestAddsTheProductToTheCart() {
        new AmazonSearchBL().createProductToCart();
    }

    @Then("Guest see the product in the cart")
    public void guestSeeTheProductInTheCart() {
        new AmazonCartBL().verifyTheProductInCart();
    }


}


