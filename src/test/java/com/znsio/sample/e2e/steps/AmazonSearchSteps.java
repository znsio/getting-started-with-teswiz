package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazonsearch.AmazonCartBL;
import com.znsio.sample.e2e.businessLayer.amazonsearch.AmazonProductBL;
import com.znsio.sample.e2e.businessLayer.amazonsearch.AmazonSearchBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.NotImplementedException;
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
        new AmazonSearchBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).searchForProduct(productTitle);
    }

    @When("I, select first item from the list")
    public void iSelectFirstItemFromTheList() {
        LOGGER.info(System.out.printf("iSelectFirstItemFromTheList - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER , Runner.platform));
        new AmazonSearchBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).selectFirstItem();
    }

    @Then("I, should verify the product details")
    public void iShouldVerifyTheProductDetails() {
        LOGGER.info(System.out.printf("iShouldVerifyTheProductDetails - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER , Runner.platform));
        new AmazonProductBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).verifyProductDetails();
    }

    @When("I, add the product to the shopping cart")
    public void iAddTheProductToTheShoppingCart() {
        LOGGER.info(System.out.printf("iAddTheProductToTheShoppingCart - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER , Runner.platform));
        new AmazonProductBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).addToCart();
    }

    @Then("I, should be able to see the product in the cart")
    public void iShouldBeAbleToSeeTheProductInTheCart() {
        LOGGER.info(System.out.printf("iShouldBeAbleToSeeTheProductInTheCart - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER , Runner.platform));
        new AmazonCartBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).verifyProductAddedToCart(SAMPLE_TEST_CONTEXT.PRODUCT_NAME);
    }
}
