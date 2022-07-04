package com.znsio.sample.e2e.steps.amazon;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.LaunchScreenBL;
import com.znsio.sample.e2e.businessLayer.amazon.SearchResultsBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class ProductSearchSteps {
    private static final Logger LOGGER = Logger.getLogger(ProductSearchSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public ProductSearchSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I launch amazon application")
    public void iLaunchAmazonApplication() throws Exception {
        LOGGER.info(String.format("I launch amazon application, Platform: '%s'", SAMPLE_TEST_CONTEXT.ME,
                Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        new LaunchScreenBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).verifyPageTitle();
    }

    @And("I search for {string}")
    public void iSearchFor(String productName) {
        LOGGER.info(String.format("I search for product : '%s' and verify results , Platform: '%s'", productName, SAMPLE_TEST_CONTEXT.ME,
                Runner.platform));
        new SearchResultsBL().searchForProductAndVerify(productName);
    }

    @When("I select the first product from the search results")
    public void iSelectTheFirstProductFromTheSearchResults() {
        LOGGER.info(System.out.printf("I select the first product from the search results, Platform: '%s'", SAMPLE_TEST_CONTEXT.ME,
                Runner.platform));
        new SearchResultsBL().selectProductAndVerifyDetails(0);
    }
}
