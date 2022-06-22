package com.znsio.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.businessLayer.amazoncartvalidation.AmazonHomeBL;
import com.znsio.e2e.businessLayer.amazoncartvalidation.ProductCartBL;
import com.znsio.e2e.businessLayer.amazoncartvalidation.ProductDetailsBL;
import com.znsio.e2e.businessLayer.amazoncartvalidation.SearchResultsListBL;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonCartValidationStep {
    private static final Logger LOGGER = Logger.getLogger(AmazonCartValidationStep.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;
    public AmazonCartValidationStep() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }
    @Given("user is on homepage")
    public void userIsOnHomepage(){
        LOGGER.info(System.out.printf("userIsOnHomePage - Persona:'%s'", SAMPLE_TEST_CONTEXT.ME));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new AmazonHomeBL().get();
    }

    @When("user searches for {string}")
    public void userSearchesFor(String productName) {
        new AmazonHomeBL().searchForTheProduct(productName).verifySearchResult(productName);
    }

    @And("user select first product")
    public void userSelectFirstProduct() {
        new SearchResultsListBL().selectTheFirstProduct().verifyProductDeatils();
    }

    @And("user adds product to the cart")
    public void userAddsProductToTheCart() throws InterruptedException {new ProductDetailsBL().addProductToTheCart();}

    @Then("product should be get added to the cart")
    public void productShouldBeGetAddedToTheCart() {new ProductCartBL().verifyProductIsAddedToTheCart();}
}

