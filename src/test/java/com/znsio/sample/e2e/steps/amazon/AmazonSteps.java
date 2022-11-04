package com.znsio.sample.e2e.steps.amazon;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.*;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonSteps {
    private static final Logger LOGGER = Logger.getLogger(AmazonSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("I login to amazon with valid credentials")
    public void i_login_to_amazon_with_valid_credentials() {
        LOGGER.info(System.out.printf("i_login_to_amazon_with_valid_credentials", SAMPLE_TEST_CONTEXT.ME));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME,Runner.platform,context);
        new AmazonLoginBL(SAMPLE_TEST_CONTEXT.ME, Runner.platform).LoginToAmazon();
    }

    @When("I searched for iphone13 and selected the first item result")
    public void i_searched_for_iphone13_and_selected_the_first_item_result() {
        LOGGER.info("I am on amazon HomePage and searches for iphone13");
        new AmazonHomePageBL().searchForIphone13();
        new IphoneListPageBL().validateIphoneResult();
        new IphoneListPageBL().clickOnFirstProduct();
    }

    @And("I add the iphone13 to the cart")
    public void i_add_the_iphone13_to_the_cart() {
        LOGGER.info("I am on iphone page and adding the product to the cart");
        new ProductPageBL().verifyProductDetailsAndTitle();
        new ProductPageBL().addProductToCart();
        new ProductPageBL().verifyCart();
        new ProductPageBL().clickOnCart();
    }

    @Then("iphone13 is added to the cart")
    public void iphone13_is_added_to_the_cart() {
        LOGGER.info("I am on the cart");
        new AmazonCartBL().verifyAmIOnCart();
        new AmazonCartBL().verifySameProductPresentInCart();
    }
}
