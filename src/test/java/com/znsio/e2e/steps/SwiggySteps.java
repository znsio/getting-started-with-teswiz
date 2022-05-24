package com.znsio.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.businessLayer.SwiggyCartBL;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

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

    @Given("User is on Home Page")
    public void userIsOnHomePage() {
         LOGGER.info(System.out.printf(SAMPLE_TEST_CONTEXT.SWIGGY_USER, Runner.platform));
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.SWIGGY_USER, Runner.platform, context);
        //context.addTestState(SAMPLE_TEST_CONTEXT.SWIGGY_USER,context);
        //new SwiggyCartBL(SAMPLE_TEST_CONTEXT.SWIGGY_USER, Runner.platform);
        new SwiggyCartBL().openSwiggyHome();
    }

    @When("user adds the location")
    public void userAddsTheLocation(DataTable location) {
        List<Map<String, String>> data = location.asMaps(String.class, String.class);
        new SwiggyCartBL().addLocation(data.get(0).get("location"));
    }

    @Then("user will see Restaurants List for the Location")
    public void userWillSeeRestaurantsListForTheLocation() {
        new SwiggyCartBL().validateLocationInRestaurantPage();
    }

    @When("User sort the Restaurant List by Rating")
    public void userSortTheRestaurantListBy() {
        new SwiggyCartBL().selectRatingTab();
    }

    @Then("User should see Restaurant List")
    public void userShouldSeeRestaurantList() {
        new SwiggyCartBL().validateRestaurantList();
    }

    @When("user select Restaurant")
    public void userSelect() {
        new SwiggyCartBL().clickOnRestaturant();

    }

    @Then("Restaurant menu should get displayed")
    public void menuShouldGetDisplayed() {
        new SwiggyCartBL().validateRestaurantName();
    }

    @When("Search and adds the Item")
    public void searchAndAddsTheItem() {
        new SwiggyCartBL().selectFoodItem();
    }

    @Then("User should able to see cart created")
    public void userShouldAbleToSeeCartCreated() {
        new SwiggyCartBL().openCart()
                .validateItemName();
    }


}
