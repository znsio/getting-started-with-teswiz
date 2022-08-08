package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonCartSteps {
    private static final Logger LOGGER = Logger.getLogger(AmazonCartSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;
    AmazonBL amazonBL = new AmazonBL();

    public AmazonCartSteps(){
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("User has launched Amazon web application")
    public void webApplicationLaunched() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).launchWebApplication();
    }

    @When("User searches for {string}")
    public void userSearchesFor(String productName) {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).searchForProduct(productName);
    }

    @Then("User get results related to search")
    public void userGetResultsRelatedToSearch() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyResultsDisplayed();
    }

    @When("User opens first result")
    public void userOpensFirstResult() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).openFirstResult();
    }

    @Then("User gets navigated to product detail page")
    public void userGetsNavigatedToProductDetailPage() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyProductDetails();
    }

    @And("User validates add to cart")
    public void userValidatesAddToCart() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyAddToCartButton();
    }

    @When("User add product to cart")
    public void userClicksAddToCart() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).addProductToCart();
    }

    @And("User opens cart")
    public void userGoToCart() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).openCart();
    }

    @Then("User able to see added product in cart")
    public void userAbleToSeeAddedProductInCart() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyProductAddedToCart();
    }
}
