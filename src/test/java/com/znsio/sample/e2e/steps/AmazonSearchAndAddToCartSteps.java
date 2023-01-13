package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonCartBL;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonProductDetailsBL;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonSearchBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonSearchAndAddToCartSteps {

    private static final Logger LOGGER = Logger.getLogger(AmazonSearchAndAddToCartSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSearchAndAddToCartSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I, as a guest user, search for {string} on amazon")
    public void iAsAGuestUserSearchForProductOnAmazon(String product) {
        LOGGER.info(System.out.printf("iAGuestUserSearchForProducts - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        new AmazonSearchBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).searchFor(product);
    }

    @When("I select the first product from the search results")
    public void iSelectTheFirstItemFromTheSearchResults() {
        new AmazonSearchBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).clickOnFirstItem();
    }

    @Then("I should see the product detail page")
    public void iShouldLandOnItsProductDetailPage() {
        new AmazonProductDetailsBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).verifyProductDetails();
    }

    @When("I add the product to my cart")
    public void iAddToTheCart() {
        new AmazonProductDetailsBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).addToCart();
    }

    @Then("I should be able to see the product in the cart")
    public void iShouldBeAbleToSeeInTheCart() {
        new AmazonCartBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).verifyCartItem();
    }
}
