package com.znsio.e2e.businessLayer.swiggy;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.swiggy.CartSectionInRestaurantProfileScreen;
import com.znsio.e2e.screen.swiggy.RestaurantListingScreen;
import com.znsio.e2e.screen.swiggy.RestaurantProfileScreen;
import com.znsio.e2e.screen.swiggy.SwiggyHomeScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class SwiggyCartBL {

    private static final Logger LOGGER = Logger.getLogger(SwiggyCartBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public SwiggyCartBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public SwiggyCartBL navigateToHomePage() {
        SwiggyHomeScreen.get();
        return this;
    }

    public SwiggyCartBL searchForDeliveryLocation(String deliveryLoc){
        SwiggyHomeScreen.get().searchForDeliveryLocation(deliveryLoc);
        return this;
    }

    public SwiggyCartBL getCountOfListedRestaurantsForSearchedLocation() {
        int countOfRestaurantsFetched=RestaurantListingScreen.get().getRestaurantCountForSearchedLocation();
        LOGGER.info("Number of Restaurants fetched:"+countOfRestaurantsFetched);
        softly.assertThat(countOfRestaurantsFetched).isGreaterThan(0);
        return this;
    }

    public SwiggyCartBL getSetDeliveryLocation() {
        String setDeliveryLocation = RestaurantListingScreen.get().getSetDeliveryLocationName();
        LOGGER.info("User requested location:"+context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCHED_DELIVERY_LOCATION)+"| Set Delivery location:"+setDeliveryLocation);
        softly.assertThat(setDeliveryLocation).contains(context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCHED_DELIVERY_LOCATION));
        return this;
    }

    public SwiggyCartBL sortRestaurantsByRating() {
        RestaurantListingScreen.get().sortRestaurantByRating();
        return this;
    }

    public SwiggyCartBL selectRandomRestaurant() {
        RestaurantListingScreen.get().selectRestaurantByRandomIndex();
        return this;
    }

    public SwiggyCartBL addFoodItemToCart() {
        LOGGER.info("In Overloaded method");
        RestaurantProfileScreen.get().addFoodItemsToCart();
        int itemsCounterValue=RestaurantProfileScreen.get().getItemsCountFromDishImage();
        LOGGER.info("Items Counter Value:"+itemsCounterValue);
        softly.assertThat(itemsCounterValue).isGreaterThan(0);
        return this;
    }

    public SwiggyCartBL isAddedFoodItemNameMatchingWithCart() {
        LOGGER.info("Getting name of Food Item that was supposed to be added to cart");
        String foodItemNameAddedFromRestaurantProfileSection = RestaurantProfileScreen.get().getFoodItemNameToBeAdded();
        String foodItemNameAddedInCartSectionOfRestaurantProfile = CartSectionInRestaurantProfileScreen.get().getSingleAddedItemNameFromCartSection();
        LOGGER.info("Asserting Item Added:"+foodItemNameAddedFromRestaurantProfileSection+" = Item in Cart:"+foodItemNameAddedInCartSectionOfRestaurantProfile);
        softly.assertThat(foodItemNameAddedFromRestaurantProfileSection).isEqualTo(foodItemNameAddedInCartSectionOfRestaurantProfile);
        //softly.assertThat(foodItemNameAddedInCartSectionOfRestaurantProfile).contains(foodItemNameAddedFromRestaurantProfileSection);
        return this;
    }

    public SwiggyCartBL isAddedFoodItemCountMatchingWithCart() {
        LOGGER.info("Getting count of Food Item that was supposed to be added to cart");
        int foodItemCountAddedFromRestaurantProfileSection = RestaurantProfileScreen.get().getFoodItemOrderCount();
        int foodItemCountAddedInCartSectionOfRestaurantProfile = CartSectionInRestaurantProfileScreen.get().getSingleAddedItemCountFromCartSection();
        LOGGER.info("Asserting units of Item Added:"+foodItemCountAddedFromRestaurantProfileSection+" = Units of Items in Cart:"+foodItemCountAddedInCartSectionOfRestaurantProfile);
        softly.assertThat(foodItemCountAddedFromRestaurantProfileSection).isEqualTo(foodItemCountAddedInCartSectionOfRestaurantProfile);
        return this;
    }

    public SwiggyCartBL incrementCartValueBySingleUnit(){
        LOGGER.info("Incrementing value in cart");
        RestaurantProfileScreen.get().incrementCartValue();
        return this;
    }

    public int getFoodItemOrderCountFromRestaurantProfileMenu() {
        int itemsInOrderList = RestaurantProfileScreen.get().getFoodItemOrderCount();
        LOGGER.info("Preparing to empty the items in line of Order, currently available:"+itemsInOrderList);
        return itemsInOrderList;
    }

    public SwiggyCartBL removeAddedItemsFromRestauranProfileMenu(int itemsAlreadyAdded) {
        LOGGER.info("Removing items in line of order from Restaurant profile page:"+itemsAlreadyAdded);
        RestaurantProfileScreen.get().decrementCartValueFromProfileSection(itemsAlreadyAdded);
        return this;
    }

    public SwiggyCartBL isCartEmpty() {
        LOGGER.info("Asserting whether cart is empty or not");
        softly.assertThat(CartSectionInRestaurantProfileScreen.get().isCartEmpty()).isEqualTo(true);
        return this;
    }

}
