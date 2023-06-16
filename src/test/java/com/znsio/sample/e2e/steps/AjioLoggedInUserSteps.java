package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.ajioUAT.AjioHomeBL;
import com.znsio.sample.e2e.businessLayer.ajioUAT.AjioProductBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.log4j.Logger;

import java.util.Map;

public class AjioLoggedInUserSteps {
    private static final Logger LOGGER = Logger.getLogger(AjioLoggedInUserSteps.class.getName());
    private final TestExecutionContext context;

    public AjioLoggedInUserSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
    }

    @Given("I login to ajio as {string}")
    public void iLoginToAjioAs(String user) {
        LOGGER.info(System.out.printf("iLoginToAjio - Persona:'%s', Platform: '%s'",
                SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()));
        Drivers.createDriverFor(SAMPLE_TEST_CONTEXT.LOGGEDIN_USER, Runner.getPlatform(), context);
        context.addTestState(SAMPLE_TEST_CONTEXT.LOGGEDIN_USER, user);
        Map<String, Object> userDetails = Runner.getTestDataAsMap(user);
        new AjioHomeBL(SAMPLE_TEST_CONTEXT.LOGGEDIN_USER, Runner.getPlatform()).loginAsValidUser(userDetails);
    }

    @And("I search for for {string} product")
    public void iSearchForForProduct(String product) {
        new AjioHomeBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.getPlatform()).searchFor(product);
    }

    @And("I wishlist the {int}th product and move it to cart")
    public void iWishlistTheThProductAndMoveItToCart(int itemNumber) {
        new AjioProductBL().wishlistTheProductFromSearchResult(itemNumber).moveTheProductToCart();
    }

    @And("I remove the product from cart and verify cart is empty by relog")
    public void iRemoveTheProductFromCartAndVerifyCartIsEmpty() {
        Map<String, Object> userDetails = Runner.getTestDataAsMap(
                context.getTestStateAsString(SAMPLE_TEST_CONTEXT.LOGGEDIN_USER));
        new AjioProductBL().removeProductFromCart();
        new AjioHomeBL().logoutUser().loginAsValidUser(userDetails);
        new AjioProductBL().verifyCartIsEmpty();
    }


//    @And("I Relog and verify cart is empty")
//    public void iRelogAndVerifyCartIsEmpty() {
//        new AjioLoginBL().logoutUser().verifyCartIsEmpty();
//    }
}
