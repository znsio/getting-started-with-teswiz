package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.CartBL;
import com.znsio.sample.e2e.businessLayer.amazon.ProductDetailsBL;
import com.znsio.sample.e2e.businessLayer.amazon.SearchBL;
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
        context.addTestState(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD, product);
        new SearchBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).searchForProduct(product);
    }

    @When("I select the first product from the search results")
    public void iSelectTheFirstProductFromTheSearchResults() {
        new SearchBL().selectFirstProduct();
    }

    @Then("I should see the product detail page")
    public void iShouldLandOnItsProductDetailPage() {
        new ProductDetailsBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).verifyProductDetails();
    }

    @When("I add the product to my cart")
    public void iAddToTheCart() {
        new ProductDetailsBL().createCart();
    }

    @Then("I should be able to see the product in the cart")
    public void iShouldBeAbleToSeeInTheCart() {
        new CartBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).verifyCart();
    }
}
