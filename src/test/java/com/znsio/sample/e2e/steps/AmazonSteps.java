package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.CartPageBL;
import com.znsio.sample.e2e.businessLayer.amazon.HomePageBL;
import com.znsio.sample.e2e.businessLayer.amazon.ProductPageBL;
import com.znsio.sample.e2e.businessLayer.amazon.SearchPageBL;
import com.znsio.sample.e2e.entities.CONTEXT_AMAZON;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonSteps {

    private static final Logger LOGGER = Logger.getLogger(AmazonSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;
    private int previousCartValue;

    public AmazonSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(CONTEXT_AMAZON.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I am on Amazon homepage")
    public void iLoadAmazonHomePage() {
        allDrivers.createDriverFor(CONTEXT_AMAZON.ME, Runner.platform, context);
        new HomePageBL();
    }

    @When("I search for {string}")
    public void iSearchForAProduct(String searchProductQuery) {
        context.addTestState(searchProductQuery, searchProductQuery);
        new HomePageBL().searchForAProduct(searchProductQuery);
    }

    @And("Add first product to cart")
    public void iAddProductToCart(){
        new SearchPageBL().selectProduct();
        new ProductPageBL().addProductToCart().navigateToCart();
    }

    @Then("Product gets added to the cart")
    public void iVerifyProductsInTheCart(){
        new CartPageBL().validateAddedProduct();
    }
}
