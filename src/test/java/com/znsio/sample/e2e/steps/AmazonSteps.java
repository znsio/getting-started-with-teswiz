package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonHomePageBL;
import com.znsio.sample.e2e.businessLayer.amazon.ProductViewBL;
import com.znsio.sample.e2e.businessLayer.amazon.ShoppingCartBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.*;
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

    @Given("I, a guest user, search for product {string} on amazon")
    public void iAGuestUserSearchForStringProduct(String product) {
        context.addTestState(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD, product);
        LOGGER.info(System.out.printf("Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        new AmazonHomePageBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).searchForProduct(product);
    }

    @When("I select the first product from the search results page")
    public void iSelectTheFirstProductFromTheSearchResultsPage() {
        new AmazonHomePageBL().selectFirstProduct();
    }

    @And("I add the product to the shopping cart")
    public void iAddTheProductFromTheProductViewPageToTheShoppingCart() {
        new ProductViewBL().createShoppingCart();
    }

    @Then("I should be able to see the product in the shopping cart")
    public void iVerifyTheStringProductIsPresentInTheShoppingCart() {
        String product = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCH_KEYWORD);
        new ShoppingCartBL().verifyShoppingCart(product);
    }
}
