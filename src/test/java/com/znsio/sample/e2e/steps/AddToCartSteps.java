package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonLandingBL;
import com.znsio.sample.e2e.businessLayer.amazon.CartBL;
import com.znsio.sample.e2e.businessLayer.amazon.ProductDetailsBL;
import com.znsio.sample.e2e.businessLayer.amazon.SearchResultsBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;



    public class AddToCartSteps {

        private static final Logger LOGGER = Logger.getLogger(AddToCartSteps.class.getName());
        private final TestExecutionContext context;
        private final Drivers allDrivers;

        public AddToCartSteps() {
            context = SessionContext.getTestExecutionContext(Thread.currentThread()
                    .getId());
            LOGGER.info("context: " + context.getTestName());
            allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
            LOGGER.info("allDrivers: " + (null == allDrivers));
        }

        @Given("user searches for {string} product on amazon landing page")
        public void userSearchForProductOnAmazonLandingPage(String product) {
            LOGGER.info(System.out.printf("userIsOnTheLandingPage - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.ME,
                    Runner.platform));
            allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
            context.addTestState(SAMPLE_TEST_CONTEXT.ME, SAMPLE_TEST_CONTEXT.ME);
            new AmazonLandingBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).search(product);
        }

        @When("user selects first product from search results")
        public void userSelectsFirstProduct() {
            new SearchResultsBL().userSelectsFirstProduct();
        }


        @And("user adds product into cart")
        public void userAddsProductIntoCart() {
            new ProductDetailsBL().userAddsProductToCart();
        }

        @Then("added product should be visible into cart")
        public void addedProductShouldBeVisibleIntoCart() {
            new CartBL().verifyAddedProductInCart();
        }
    }

