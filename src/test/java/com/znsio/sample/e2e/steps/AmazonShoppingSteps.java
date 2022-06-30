package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.theapp.AmazonHomeBL;
import com.znsio.sample.e2e.businessLayer.theapp.AmazonCartBL;
import com.znsio.sample.e2e.businessLayer.theapp.AmazonSearchResultsBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class AmazonShoppingSteps {

    private static final Logger LOGGER = Logger.getLogger(TheAppSteps.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final Drivers allDrivers;
    public String ProductName;

    public AmazonShoppingSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
        long threadId = Thread.currentThread().getId();
        softly = Runner.getSoftAssertion(threadId);
    }

    @Given("I login with valid Credentials - {string}, {string}")
    public void i_login_with_valid_credentials(String username, String password) {
        LOGGER.info(System.out.printf("i_login_with_valid_credentials - Persona:'%s', Username: '%s', Password:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, username, password,
                Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.platform, context);
        context.addTestState(SAMPLE_TEST_CONTEXT.ME, username);
        new AmazonHomeBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).AmazonLogin(username, password);
        new AmazonHomeBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).loginVerification();
    }

    @When("I search for - {string}")
    public void i_search_for(String product) {
        LOGGER.info(System.out.printf("I search for a product"));
        new AmazonSearchResultsBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).SearchProduct(product);
    }

    @Then("I verify the search results contain - {string}")
    public void i_verify_the_search_results(String product) {
        LOGGER.info(System.out.printf("Verifying if correct products are displayed"));
        new AmazonSearchResultsBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).VerifyingProduct(product);
        LOGGER.info(System.out.printf("The search results are verified"));
    }

    @Then("I add first product displayed in search results")
    public void i_add_first_product_displayed_in_search_results() {
        new AmazonSearchResultsBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).AddProduct();
        LOGGER.info(System.out.printf(ProductName + " Details are displayed after clicking on it"));
    }

    @Then("I verify the product added in cart")
    public void i_verify_the_product_added_in_cart() {
        LOGGER.info(System.out.printf("Add product to cart and verify"));
        new AmazonCartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).AddProductToCart();
        new AmazonCartBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).ProductVerification();
    }

}
