package com.znsio.e2e.steps.swiggy;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.businessLayer.swiggy.SwiggyCartBL;
import com.znsio.e2e.businessLayer.swiggy.SwiggyRestaurantProfileBL;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.entities.TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CartReviewSteps {

    private static final Logger LOGGER = Logger.getLogger(CartReviewSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public CartReviewSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }
    @Given("User is on Home Page")
    public void userIsOnHomePage() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        context.addTestState(SAMPLE_TEST_CONTEXT.GUEST_USER,"Guest");
        LOGGER.info("Navigating "+ context.getTestStateAsString(SAMPLE_TEST_CONTEXT.GUEST_USER)+" to Home Page");
        new SwiggyCartBL().navigateToHomePage();
    }

    @When("User sets location to")
    public void userSetsLocationTo(DataTable deliveryLocation) {
        List<Map<String,String>> locTestData = deliveryLocation.asMaps(String.class, String.class);
        context.addTestState(SAMPLE_TEST_CONTEXT.SEARCHED_DELIVERY_LOCATION,locTestData.get(0).get("location"));
        LOGGER.info("User sets delivery Location to:"+context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCHED_DELIVERY_LOCATION));
        new SwiggyCartBL().searchForDeliveryLocation(locTestData.get(0).get("location"));

    }

    @Then("User should see restaurants available for selected location")
    public void userShouldSeeRestaurantsAvailableForSelectedLocation() {
        LOGGER.info("Verifying User is returned list of Restaurants to select from for searched location:"+context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCHED_DELIVERY_LOCATION));
        new SwiggyCartBL().getCountOfListedRestaurantsForSearchedLocation().getSetDeliveryLocation();
    }

    @When("User sorts the restaurants by ratings")
    public void userSortsTheRestaurantsByRatings() {
        LOGGER.info("User tries to sort Restaurants as per their Ratings");
        new SwiggyCartBL().sortRestaurantsByRating();
    }

    @Then("User gets Restaurant list sorted by ratings")
    public void userGetsRestaurantListSortedByRatings() {
        LOGGER.info("Verifying number of Restaurants returned post Ratings based sort");
        new SwiggyCartBL().getCountOfListedRestaurantsForSearchedLocation();
    }

    @When("User selects a restaurant")
    public void userSelectsARestaurant() {
        LOGGER.info("User attempts to select a restaurant at random");
        new SwiggyCartBL().selectRandomRestaurant();
    }

    @And("User Adds food item in the cart")
    public void userAddsFoodItemInTheCart() {
        LOGGER.info("USer tries to add food items to cart");
        new SwiggyCartBL().addFoodItemToCart();
    }

    @Then("Food items should Be Added to the Cart")
    public void foodItemsShouldBeAddedToTheCart() {
        LOGGER.info("Verifying food items added to the cart");
        new SwiggyCartBL().isAddedFoodItemNameMatchingWithCart().isAddedFoodItemCountMatchingWithCart();
    }

    @When("User increments the Quantity of food items in cart")
    public void userIncrementsTheQuantityOfFoodItemsInCart() {
        LOGGER.info("User increments the cart items quantity");
        new SwiggyCartBL().incrementCartValueBySingleUnit();
    }

    @Then("Food items Quantity Should Be Increased in cart")
    public void foodItemsQuantityShouldBeIncreasedInCart() {
        LOGGER.info("Verify value increase in cart");
        new SwiggyCartBL().isAddedFoodItemCountMatchingWithCart();
    }

    @When("User deletes the food item from the cart")
    public void userDeletesTheFoodItemFromTheCart() {
        LOGGER.info("Removing items in order to set cart empty");
        int itemsAlreadyAdded=new SwiggyCartBL().getFoodItemOrderCountFromRestaurantProfileMenu();
        new SwiggyCartBL().removeAddedItemsFromRestauranProfileMenu(itemsAlreadyAdded);
    }

    @Then("The cart should be Empty")
    public void theCartShouldBeEmpty() {
        LOGGER.info("Verifying whether cart is empty");
        new SwiggyCartBL().isCartEmpty();
    }
}
