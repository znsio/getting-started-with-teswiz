package com.znsio.e2e.steps.swiggynew;

import com.context.SessionContext;
import com.context.TestExecutionContext;

import com.znsio.e2e.businessLayer.swiggynew.RestaurantsBL;
import com.znsio.e2e.businessLayer.swiggynew.SearchLocationBL;
import com.znsio.e2e.businessLayer.swiggynew.RestaurantsListBL;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class CartNew {

    private static final Logger LOGGER = Logger.getLogger(CartNew.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public CartNew() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }
    @Given("User sets location to {string} on home page")
    public void userSetsLocationToOnHomePage(String deliveryLocation) {
        LOGGER.info("Guest User is on Landing page and trying to set delivery location to: "+deliveryLocation);
        allDrivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.platform, context);
        context.addTestState(SAMPLE_TEST_CONTEXT.SEARCHED_DELIVERY_LOCATION,deliveryLocation);
        new SearchLocationBL().searchForLocation(deliveryLocation);
        LOGGER.info("Asserting whether Guest User is shown list of restaurants as per his desired delivery loation");
        new RestaurantsListBL().getDeliveryLocationNameSetByUser();
    }

    @When("User sorts the restaurants on the basis of {string}")
    public void userSortsTheRestaurantsOnTheBasisOf(String sortCriteria) {
        LOGGER.info("Guest User is on Restaurant List page and is trying to Sort restaurants based on: "+sortCriteria);
        new RestaurantsListBL().sortObBasisOf(sortCriteria);

    }

    @And("User selected a restaurant")
    public void userSelectsARestaurant() {
        LOGGER.info("User attempts to select a restaurant at random");
        new RestaurantsListBL().selectRandomRestaurant();
    }

    @And("User adds food item in the cart")
    public void userAddsFoodItemInTheCart() {
        LOGGER.info("User attempts at adding item to the cart");
        new RestaurantsBL().addRandomItemsToCart();
    }

    @Then("Items should be added in the cart")
    public void itemsShouldBeAddedInTheCart() {
        LOGGER.info("Verifying if required unit of items are added to cart");
        new RestaurantsBL().isRequiredItemPresentInCart();
    }
    @When("User removes all items from the cart")
    public void userRemovesItemsFromTheCart() {
        LOGGER.info("Removing items in order to set cart empty");
        int itemsAlreadyAdded=new RestaurantsBL().getFoodItemOrderCountFromRestaurantMenu();
        new RestaurantsBL().removeAddedItemsFromRestauranMenu(itemsAlreadyAdded);
    }

    @Then("The cart should be empty")
    public void theCartShouldBeEmpty() {
        LOGGER.info("Verifying whether cart is empty");
        new RestaurantsBL().verifyCartIsEmpty();
    }


}
