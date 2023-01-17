package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.Amazon.AmazonHomeBL;
import com.znsio.sample.e2e.businessLayer.Amazon.AmazonProductViewBL;
import com.znsio.sample.e2e.businessLayer.Amazon.AmazonShoppingCartBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonProductViewScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonSearchSteps {
    private static final Logger LOGGER = Logger.getLogger(AmazonSearchSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSearchSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I, as a guest user, search for {string} in amazon search")
    public void iAsAGuestUserSearchForInAmazonSearchOption(String productName) {
        context.addTestState(SAMPLE_TEST_CONTEXT.PRODUCT_NAME, productName);
        LOGGER.info(System.out.printf("iAsAGuestUserSearchForInAmazonSearchOption - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        new AmazonHomeBL().searchProduct(productName);
    }

    @When("I select the first product from the result list")
    public void iSelectFirstProductFromTheList() {
        new AmazonProductViewBL().selectFisrtProductFromTheResultList();
    }

    @And("I add the selected product to the shopping cart")
    public void iAddTheSelectedProductToTheShoppingCart() {
        new AmazonProductViewBL().addProductToTheShoppingCart();
    }

    @Then("I should be able to see the product in the shopping cart")
    public void iShouldBeAbleToSeeProductAddedInTheCart() {
        String productName = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.PRODUCT_NAME);
        new AmazonShoppingCartBL().navigateToTheShoppingCart().VerifyTheProductIsVisibleInShoppingCart(productName);
    }

}
