package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.ajio.*;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AjioSteps {
    private static final Logger LOGGER = Logger.getLogger(AjioSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AjioSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                                                               .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }


    @Given("I, a guest user, search for {string} products")
    public void iAGuestUserSearchForProducts(String product) {
        LOGGER.info(System.out.printf("iAGuestUserSearchForProducts - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        new AjioSearchBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform).searchFor(product);
    }

    @And("I apply filters for gender {string} with size {string}")
    public void iApplyFiltersForGenderWithSize(String gender, String size) {
        new RefineByBL().refineProducts(gender, size);
    }

    @When("I select and add the 1st product with the right size to the bag")
    public void iSelectAndAddThe1stProductWithTheRightSizeToTheBag() {
        new ResultsBL().selectProduct().addProductToCart();
    }

    @Then("I can apply a coupon from the bag")
    public void iCanApplyACouponFromTheBag() {
        new ProductDetailBL().navigateToCart().applyCouponCode();
    }
}
