package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.CartPageBL;
import com.znsio.sample.e2e.businessLayer.amazon.HomePageBL;
import com.znsio.sample.e2e.businessLayer.amazon.ProductPageBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonSearchAddtocartSteps {

    private static final Logger LOGGER = Logger.getLogger(AmazonSearchAddtocartSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSearchAddtocartSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());

        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);

        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I, searched for {string} product in amazon search bar")
    public void iSearchedForProductInAmazonSearchBar(String product) {
        LOGGER.info(System.out.printf("iAsAGuestUserSearchedForProductInSearchBarOfAmazon - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        new HomePageBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).productSearch(product);
    }
    @When("I view the first product from search list")
    public void iViewTheFirstProductFromSearchList() {
        new ProductPageBL().viewFirstProduct();
    }
    @Then("I should see the product details page")
    public void iShouldSeeTheProductDetailsPage() {
        new ProductPageBL().verifyProductDetails();
    }
    @When("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        new CartPageBL().prepareCart();
    }
    @Then("I should see the product in the cart")
    public void iShouldSeeTheProductInTheCart() {
        new CartPageBL().verifyProductDetailsInCart();
    }
}
