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

public class CartRefactoredSteps {

    private static final Logger LOGGER = Logger.getLogger(CartRefactoredSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public CartRefactoredSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }


    @Given("User is at Home Page")
    public void userIsAtHomePage() {
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        new SwiggyHomeBL().navigateToHomePage();
    }

    @When("User Searches For The Location {string} On Home Page")
    public void userSearchesForTheLocationOnHomePage(String deliveryLoc) {
        new SwiggyHomeBL().searchForDeliveryLocation(deliveryLoc);
    }

    @And("User Sorts The Restaurants By Ratings")
    public void userSortsTheRestaurantsByRatings() {
        new SwiggyRestaurantListingBL().sortRestaurantsByRating();
    }

    @Then("User Gets a List Of Restaurants")
    public void userGetsAListOfRestaurants() {
        new SwiggyRestaurantListingBL().getRestaurantCountForSearchedLocation();
    }
    /*
    @When("User selects Restaurant at index {string}")
    public void userSelectsRestaurantAtIndex(String oneBasedIndxValue) {
        new SwiggyRestaurantListingBL().selectRestaurantByIndex(oneBasedIndxValue);
    }*/

    @When("User selects Restaurant named {string}")
    public void userSelectsRestaurantNamed(String restaurantName) {
        new SwiggyRestaurantListingBL().selectRestaurantByName(restaurantName);
    }

    @And("Adds food from {string} category Into the Cart")
    public void addsFoodFromCategoryIntoTheCart(String foodCategory) {
        new SwiggyRestaurantProfileBL().addFoodItemToCart(foodCategory);
    }

    @Then("The food item should Be Added To The Cart")
    public void theFoodItemShouldBeAddedToTheCart() {
        new SwiggyRestaurantProfileBL().isAddedFoodItemNameMatchingWithCart().isAddedFoodItemCountMatchingWithCart();
    }

    @When("User Increases the Quantity of Food Items in Cart")
    public void userIncreasesTheQuantityOfFoodItemsInCart() {
        new SwiggyRestaurantProfileBL().incrementCartValueBySingleUnit();
    }

    @Then("The Food Items Quantity Should Be Increased in Cart")
    public void theFoodItemsQuantityShouldBeIncreasedInCart() {
        new SwiggyRestaurantProfileBL().isAddedFoodItemCountMatchingWithCart();
    }

    @When("User deletes the Food item from the Cart")
    public void userDeletesTheFoodItemFromTheCart() {
        int itemsAlreadyAdded=new SwiggyRestaurantProfileBL().getFoodItemOrderCountFromRestaurantProfileMenu();
        new SwiggyRestaurantProfileBL().removeAddedItemsFromRestauranProfileMenu(itemsAlreadyAdded);
    }

    @Then("The Cart Should Be Empty")
    public void theCartShouldBeEmpty() {
        new SwiggyRestaurantProfileBL().isCartEmpty();
    }


}
