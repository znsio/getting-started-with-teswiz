package com.znsio.e2e.steps.swiggy;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.businessLayer.swiggy.SwiggyHomeBL;
import com.znsio.e2e.businessLayer.swiggy.SwiggyRestaurantListingBL;
import com.znsio.e2e.businessLayer.swiggy.SwiggyRestaurantProfileBL;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public class CartSteps {

    private static final Logger LOGGER = Logger.getLogger(CartSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public CartSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("User is on Home Page and sets location to {string}")
    public void userEntersADeliveryLocationOnHomePage(String deliveryLocation) {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        //context.addTestState(SAMPLE_TEST_CONTEXT.GUEST_USER);
        new SwiggyHomeBL().searchForDeliveryLocation(deliveryLocation);
    }

    @When("User sorts restaurants by ratings on Restaurant listing page")
    public void userSortsRestaurantsByRatingsOnRestaurantListingPage() {
        new SwiggyRestaurantListingBL().sortRestaurantsByRating();
    }

    @And("User selects {string} from Restaurant List")
    public void userSelectsARestuarant(String restaurantName) {
        new SwiggyRestaurantListingBL().selectRestaurantByName(restaurantName);
    }

    @And("User adds {string} units of food items from {string} category to cart")
    public void userAddsTwoFoodItemsToCart(String unitsToAdd,String foodCategory) {
        int unitOfItemsToAdd = Integer.parseInt(unitsToAdd);
        new SwiggyRestaurantProfileBL().addFoodItemToCart(unitOfItemsToAdd,foodCategory);
    }

    @Then("Cart should have {string} added")
    public void cartShouldHaveFoodItemsAdded(String foodItemsValue) {
        new SwiggyRestaurantProfileBL().getFoodItemsCountValueFromCartSection(foodItemsValue);
    }


}