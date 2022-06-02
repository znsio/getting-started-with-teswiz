package com.znsio.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.businessLayer.RestaurantListingBL;
import com.znsio.e2e.businessLayer.RestaurantMenuBL;
import com.znsio.e2e.businessLayer.SwiggyCartBL;
import com.znsio.e2e.businessLayer.SwiggyLocationSelectionBL;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class SwiggySteps {

    private static final Logger LOGGER = Logger.getLogger(LoginSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public SwiggySteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }


    @Given("user selects the {string} location from the landing page")
    public void userSelectsTheLocationFromTheLandingPage(String geoLocation) {
        LOGGER.info(System.out.printf(SAMPLE_TEST_CONTEXT.SWIGGY_USER, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.SWIGGY_USER, Runner.platform, context);
        new SwiggyLocationSelectionBL()
                .selectLocationFromHomePage(geoLocation).verifyLocationIsSet(geoLocation);


    }

    @When("user sort the restaurant List by {string}")
    public void userSortTheRestaurantListBy(String criteria) {
        new RestaurantListingBL().userSortTheRestaurantListByCriteria(criteria);
    }

    @And("user selects restaurant from the result")
    public void userSelectsRestaurantFromTheResult() {
        new RestaurantMenuBL().userSelectRestaurantFromListingPage();
    }


    @Then("user adds the items in the cart")
    public void userAddsTheItemsInTheCart() {
        new RestaurantMenuBL().userAddsTheItemsInTheCart();
    }

    @And("user should able to see cart created")
    public void userShouldAbleToSeeCartCreated() {
        new SwiggyCartBL().userAbleToSeeCartCreated();}


}
