package com.znsio.sample.e2e.steps.amazon;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.ShoppingCartBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.steps.TheAppSteps;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;

public class ShoppingCartSteps {
    private static final Logger LOGGER = Logger.getLogger(TheAppSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public ShoppingCartSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Then("I verify the cart details")
    public void iVerifyTheCartDetails() {
        LOGGER.info(String.format("I verify the cart details, Platform: '%s'", SAMPLE_TEST_CONTEXT.ME,
                Runner.platform));
    }

    @Then("I verify details in cart")
    public void iVerifyDetailsInCart() {
        LOGGER.info(String.format("I verify details in cart: '%s'", SAMPLE_TEST_CONTEXT.ME,
                Runner.platform));
        String productName = context.getTestStateAsString("ProductName");
        new ShoppingCartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).verifyProductDetails(productName, 1);
    }
}
