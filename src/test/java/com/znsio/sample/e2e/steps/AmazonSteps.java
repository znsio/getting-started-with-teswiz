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
    private static final Logger LOGGER = Logger.getLogger(JioMeetSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSteps () {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("User is on amazon homepage")
    public void userIsOnAmazonHomepage() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
    }

    @When("User search for {string}")
    public void userSearchFor(String productToSearch) {
        new HomePageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).searchProduct(productToSearch);

    }

    @Then("User should see the search results")
    public void userShouldSeeTheSearchResults() {
        new GridWallPageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyTheResultsInTheProductListingPage();
    }

    @And("User navigates to cart")
    public void userNavigatesToCart() {
        new CartPageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).clickCartButton();
    }

    @And("User should see Add to Cart")
    public void userShouldSeeAddToCart() {
        new ProductPageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyAddToCartButton();
    }

    @When("User selects Add to Cart")
    public void userSelectsAddToCart() {
        new ProductPageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).clickAddToCartButton();
    }

    @When("User selects {string} product from the search results")
    public void userSelectsProductFromTheSearchResults(String productToBeSelected) {
        new GridWallPageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).selectFirstProductFromTheSearchResults(productToBeSelected);
    }

    @Then("User should see the product page and product title syncing {string}")
    public void userShouldSeeTheProductPageAndProductTitleSyncing(String expectedProductDesc) {
        new ProductPageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyProductPageAndProductDesc(expectedProductDesc);
    }

    @Then("User should see the added item in the cart {string}")
    public void userShouldSeeTheAddedItemInTheCart(String expectedProductDesc) {
        new CartPageBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyAddedProductIsReflectionInCart(expectedProductDesc);
    }
}
