package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.ajioShopping.AjioHomePageBL;
import com.znsio.sample.e2e.businessLayer.ajioShopping.CartPageBL;
import com.znsio.sample.e2e.businessLayer.ajioShopping.ProductLandingPageBL;
import com.znsio.sample.e2e.businessLayer.ajioShopping.ProductListBL;
import com.znsio.sample.e2e.entities.ajioShopping.AJIO_SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import java.util.Map;

public class AjioShoppingSteps {
    private static final Logger LOGGER = Logger.getLogger(AjioSteps.class.getName());
    private final TestExecutionContext context;

    public AjioShoppingSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
    }

    @Given("User logs-in as {string}, search for {string} products")
    public void userLogsInAsSearchForProducts(String userSuffix, String searchProduct) {
        Map userDetails = Runner.getTestDataAsMap(userSuffix);
        LOGGER.info(System.out.printf("userLogsIn - Persona:'%s', on platform: '%s'", AJIO_SAMPLE_TEST_CONTEXT.ME,
                Runner.getPlatform()));
        Drivers.createDriverFor(AJIO_SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform(), context);
        context.addTestState(AJIO_SAMPLE_TEST_CONTEXT.ME, String.valueOf(userDetails.get("username")));
        new AjioHomePageBL().signIn(userDetails, searchProduct);
    }

    @And("Opens product at position {int} and add it to wishlist")
    public void opensProductAtPositionAndAddItToWishlist(int productPosition) {
        new ProductListBL().userSelectsProduct(productPosition);
    }

    @When("Go to wishlist and add the product with the right size to the bag")
    public void goToWishlistAndAddTheProductWithTheRightSizeToTheBag() {
        new ProductLandingPageBL().userAddProductToBag();
    }

    @Then("Apply a coupon from the bag and get the estimated delivery date")
    public void applyACouponFromTheBagAndGetTheEstimatedDeliveryDate() {
        new CartPageBL().userAppliesCouponAndGetETA();
    }
}
