package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.Amazon.AmazonHomeBL;
import com.znsio.sample.e2e.businessLayer.Amazon.ProductCartDetailsBL;
import com.znsio.sample.e2e.businessLayer.Amazon.ProductDetailsBL;
import com.znsio.sample.e2e.businessLayer.Amazon.SearchResultsBL;
import com.znsio.sample.e2e.businessLayer.notepad.NotepadBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonCartFunctionalitySteps {

    private static final Logger LOGGER = Logger.getLogger(AmazonCartFunctionalitySteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonCartFunctionalitySteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("user is on the homepage")
    public void userIsOnTheHomepage() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        LOGGER.info(System.out.printf("iHaveLaunchedAmazonApplication - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.platform));
        new AmazonHomeBL().get();
    }

    @When("user searches for {string}")
    public void userSearchesFor(String productName) {
        new AmazonHomeBL().searchForTheItem(productName).verifyTheResults(productName);

    }

    @And("user selects the first item from the search results")
    public void userSelectsTheFirstItemFromTheSearchResults() {
        new SearchResultsBL().selectTheFirstItem().verifyTheProductDetails();
    }

    @And("user add product into the cart")
    public void userAddProductIntoTheCart() {
        new ProductDetailsBL().addItemToCart();
    }

    @Then("added product should be visible in the cart")
    public void addedProductShouldBeVisibleInTheCart() {
        new ProductCartDetailsBL().verifyTheProductInTheCart();
    }


}
