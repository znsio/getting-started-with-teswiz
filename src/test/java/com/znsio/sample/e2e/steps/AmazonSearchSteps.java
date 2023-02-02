package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazonsearch.CartBL;
import com.znsio.sample.e2e.businessLayer.amazonsearch.ProductBL;
import com.znsio.sample.e2e.businessLayer.amazonsearch.SearchBL;
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

    @Given("I, a guest user search for an {string} product")
    public void iAGuestUserSearchForProducts(String productTitle) {
        LOGGER.info(System.out.printf("iAGuestUserSearchForProducts - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER , Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        new SearchBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).searchForProduct(productTitle);
    }

    @When("I, select first item from the list")
    public void iSelectFirstItemFromTheList() {
        LOGGER.info("In iSelectFirstItemFromTheList");
        new SearchBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).selectItemFromResults();
    }

    @Then("I, should be able to see the product details")
    public void iShouldBeAbleToSeeTheProductDetails() {
        LOGGER.info("In iShouldVerifyTheProductDetails");
        new ProductBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).verifyProductDetails();
    }

    @When("I, add the product to the shopping cart")
    public void iAddTheProductToTheShoppingCart() {
        LOGGER.info("In iAddTheProductToTheShoppingCart");
        new ProductBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).addToCart();
    }

    @Then("I, should be able to see the product in the cart")
    public void iShouldBeAbleToSeeTheProductInTheCart() {
        LOGGER.info("iShouldBeAbleToSeeTheProductInTheCart");
        new CartBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).verifyProductAddedToCart(SAMPLE_TEST_CONTEXT.PRODUCT_NAME);
    }
}
