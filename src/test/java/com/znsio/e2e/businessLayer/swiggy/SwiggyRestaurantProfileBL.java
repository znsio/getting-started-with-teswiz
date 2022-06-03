package com.znsio.e2e.businessLayer.swiggy;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.swiggy.CartSectionInRestaurantProfileScreen;
import com.znsio.e2e.screen.swiggy.RestaurantProfileScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class SwiggyRestaurantProfileBL {

    private static final Logger LOGGER = Logger.getLogger(SwiggyRestaurantProfileBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public SwiggyRestaurantProfileBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }


    public SwiggyRestaurantProfileBL addFoodItemToCart(int unitOfItemsToAdd,String foodCategory) {
        RestaurantProfileScreen.get().addFoodItemsToCart(unitOfItemsToAdd,foodCategory);
        int itemsCounterValue=RestaurantProfileScreen.get().getItemsCountFromDishImage(foodCategory);
        LOGGER.info("Items Counter Value:"+itemsCounterValue);
        softly.assertThat(itemsCounterValue).isEqualTo(unitOfItemsToAdd);
        return this;
    }

    public SwiggyRestaurantProfileBL addFoodItemToCart(String foodCategory) {
        LOGGER.info("In Overloaded method");
        RestaurantProfileScreen.get().addFoodItemsToCart(foodCategory);
        int itemsCounterValue=RestaurantProfileScreen.get().getItemsCountFromDishImage(foodCategory);
        LOGGER.info("Items Counter Value:"+itemsCounterValue);
        softly.assertThat(itemsCounterValue).isGreaterThan(0);
        return this;
    }

    public SwiggyRestaurantProfileBL getFoodItemsTotalCountValueFromCartSection(String foodItemsValue) {
        String cartCounter=CartSectionInRestaurantProfileScreen.get().getTotalItemsCountInCartSection();
        LOGGER.info("Asserting Cart Section value from Restaurant Profile Page:"+cartCounter+"|Expected Value:"+foodItemsValue);
        softly.assertThat(cartCounter).isEqualTo(foodItemsValue);
        return this;
    }

    public SwiggyRestaurantProfileBL isAddedFoodItemNameMatchingWithCart() {
        LOGGER.info("Getting name of Food Item that was supposed to be added to cart");
        String foodItemNameAddedFromRestaurantProfileSection = RestaurantProfileScreen.get().getFoodItemNameToBeAdded();
        String foodItemNameAddedInCartSectionOfRestaurantProfile = CartSectionInRestaurantProfileScreen.get().getSingleAddedItemNameFromCartSection();
        LOGGER.info("Asserting Item Added:"+foodItemNameAddedFromRestaurantProfileSection+" = Item in Cart:"+foodItemNameAddedInCartSectionOfRestaurantProfile);
        //softly.assertThat(foodItemNameAddedFromRestaurantProfileSection).isEqualTo(foodItemNameAddedInCartSectionOfRestaurantProfile);
        softly.assertThat(foodItemNameAddedInCartSectionOfRestaurantProfile).contains(foodItemNameAddedFromRestaurantProfileSection);
        return this;
    }

    public SwiggyRestaurantProfileBL isAddedFoodItemCountMatchingWithCart() {
        LOGGER.info("Getting count of Food Item that was supposed to be added to cart");
        int foodItemCountAddedFromRestaurantProfileSection = RestaurantProfileScreen.get().getFoodItemOrderCount();
        int foodItemCountAddedInCartSectionOfRestaurantProfile = CartSectionInRestaurantProfileScreen.get().getSingleAddedItemCountFromCartSection();
        LOGGER.info("Asserting units of Item Added:"+foodItemCountAddedFromRestaurantProfileSection+" = Units of Items in Cart:"+foodItemCountAddedInCartSectionOfRestaurantProfile);
        softly.assertThat(foodItemCountAddedFromRestaurantProfileSection).isEqualTo(foodItemCountAddedInCartSectionOfRestaurantProfile);
        return this;
    }

    public SwiggyRestaurantProfileBL incrementCartValueBySingleUnit(){
        LOGGER.info("Incrementing value in cart");
        RestaurantProfileScreen.get().incrementCartValue();
        return this;
    }

    public int getFoodItemOrderCountFromRestaurantProfileMenu() {
        int itemsInOrderList = RestaurantProfileScreen.get().getFoodItemOrderCount();
        LOGGER.info("Preparing to empty the items in line of Order, currently available:"+itemsInOrderList);
        return itemsInOrderList;
    }

    public SwiggyRestaurantProfileBL removeAddedItemsFromRestauranProfileMenu(int itemsAlreadyAdded) {
        LOGGER.info("Removing items in line of order from Restaurant profile page:"+itemsAlreadyAdded);
        RestaurantProfileScreen.get().decrementCartValueFromProfileSection(itemsAlreadyAdded);
        return this;
    }

    public SwiggyRestaurantProfileBL isCartEmpty() {
        LOGGER.info("Asserting whether cart is empty or not");
        softly.assertThat(CartSectionInRestaurantProfileScreen.get().isCartEmpty()).isEqualTo(true);
        return this;
    }
}
