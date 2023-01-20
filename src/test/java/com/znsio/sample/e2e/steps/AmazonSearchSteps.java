package com.znsio.sample.e2e.steps;
import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonProductBL;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonSearchBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonSearchSteps {
    private static final Logger LOGGER=Logger.getLogger(AmazonSearchSteps.class.getSimpleName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSearchSteps()
    {
        context= SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: "+ context.getTestName());
        allDrivers=(Drivers)context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }


    @Given("I, a guest user, search for the product {string} in search bar on Amazon")
    public void iAGuestUserSearchForTheProductInSearchBarOnAmazon(String productName)
    {
        LOGGER.info(System.out.printf("iAGuestUserSearchForTheProductInSearchBarOnAmazon - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        new AmazonSearchBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).searchProduct(productName);
    }
    @And("I view the first product from the product list")
    public void iViewTheFirstProductFromTheProductList()
    {
        new AmazonSearchBL().seeProductResults();
    }
    @When("I add the product to cart")
    public void iAddTheProductToCart()
    {
        new AmazonProductBL().addProductToCart();
    }
    @Then("I should be able to see the product in cart")
    public void iShouldBeAbleToSeeTheProductInCart()
    {
        new AmazonProductBL().seeProductInCart();

    }
}
