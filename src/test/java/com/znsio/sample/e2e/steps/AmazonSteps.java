package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.CartPageBL;
import com.znsio.sample.e2e.businessLayer.amazon.GridWallPageBL;
import com.znsio.sample.e2e.businessLayer.amazon.ProductPageBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.businessLayer.amazon.HomePageBL;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import java.awt.*;

public class AmazonSteps {
    private static final Logger LOGGER = Logger.getLogger(AmazonSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSteps () {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I am in the amazon homepage")
    public void iAmInTheAmazonHomepage() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
    }

    @When("I searches for {string}")
    public void iSearchesFor(String productToSearch) {
        new HomePageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).searchProduct(productToSearch);

    }

    @Then("product should be visible in the search results")
    public void productShouldBeVisibleInTheSearchResults() {
        new GridWallPageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyTheResultsInTheProductListingPage();
    }

    @And("I add an product to cart")
    public void iAddAnProductToCart() {
        new GridWallPageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).selectFirstProductFromTheSearchResults();
        new ProductPageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyProductDetails().verifyAddToCartButton().clickAddToCartButton();
    }

    @Then("I should see the added item into the cart")
    public void iShouldSeeTheAddedItemIntoTheCart() {
        new CartPageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).clickCartButton().verifyAddedProductIsReflectionInCart();
    }
}
