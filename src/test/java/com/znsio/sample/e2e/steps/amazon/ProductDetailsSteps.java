package com.znsio.sample.e2e.steps.amazon;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.NavBarBL;
import com.znsio.sample.e2e.businessLayer.amazon.ProductDetailsBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;

public class ProductDetailsSteps {
    private static final Logger LOGGER = Logger.getLogger(ProductDetailsSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public ProductDetailsSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @And("I verify the items in cart {string}")
    public void iVerifyTheItemsInCart(String itemsCount) {
        LOGGER.info(String.format("I verify the items in cart, Platform: '%s'", SAMPLE_TEST_CONTEXT.ME,
                Runner.platform));
        new NavBarBL().verifyItemsCountInCart(Integer.parseInt(itemsCount));
    }

    @Then("I add above item to cart")
    public void iAddAboveItemToCart() {
        LOGGER.info(String.format("I add item to cart, Platform: '%s'", SAMPLE_TEST_CONTEXT.ME,
                Runner.platform));
        new ProductDetailsBL().addItemsToCard();
    }
}
