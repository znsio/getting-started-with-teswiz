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
//        amazonBL.launchWebApplication();
    }

    @When("User searches for {string}")
    public void userSearchesFor(String productName) {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).searchForProduct(productName);
//        amazonBL.searchForProduct(productName);
    }

    @Then("User get results related to search")
    public void userGetResultsRelatedToSearch() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyResultsDisplayed();
//        boolean resultDisplayStatus = amazonBL.verifyResultsDisplayed();
//        Assert.assertTrue(resultDisplayStatus,"There are no search results displayed");
    }

    @When("User opens first result")
    public void userOpensFirstResult() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).openFirstResult();
//        amazonBL.openFirstResult();
    }

    @Then("User gets navigated to product detail page")
    public void userGetsNavigatedToProductDetailPage() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyProductDetails();
//        Assert.assertTrue(amazonBL.verifyProductDetails(),"Product details page not loaded");
    }

    @And("User validates add to cart")
    public void userValidatesAddToCart() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyAddToCartButton();
//        int value = amazonBL.verifyAddToCartButton();
//        if(value==-2)
//            Assert.fail("Product out of stock, currently unavailable");
//        else if(value==1)
//            Assert.assertTrue(true,"Add to Cart Button displayed");
//        else
//            Assert.fail("Add to cart button not displayed");
    }

    @When("User add product to cart")
    public void userClicksAddToCart() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).addProductToCart();
//        amazonBL.addProductToCart();
    }

    @And("User opens cart")
    public void userGoToCart() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).openCart();
//        amazonBL.openCart();
    }

    @Then("User able to see added product in cart")
    public void userAbleToSeeAddedProductInCart() {
        new AmazonBL(SAMPLE_TEST_CONTEXT.ME,Runner.platform).verifyProductAddedToCart();
//        Assert.assertTrue(amazonBL.verifyProductAddedToCart());
    }
}
