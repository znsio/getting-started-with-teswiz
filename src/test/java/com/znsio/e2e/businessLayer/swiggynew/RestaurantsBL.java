package com.znsio.e2e.businessLayer.swiggynew;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.swiggynew.CartSectionInRestaurantScreen;
import com.znsio.e2e.screen.swiggynew.RestaurantScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.Assertions;

public class RestaurantsBL {

    private static final Logger LOGGER = Logger.getLogger(RestaurantsBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final SoftAssertions softly;
    private final Platform currentPlatform;

    public RestaurantsBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }


    public RestaurantsBL addRandomItemsToCart() {
        RestaurantScreen.get().addRandomItemToCart();
        return this;
    }

    public RestaurantsBL isRequiredItemPresentInCart() {
        String itemNameInCart = CartSectionInRestaurantScreen.get().getSingleAddedItemNameFromCartSection();
        String itemsNameAddedFromRestaurantPage =  RestaurantScreen.get().getFoodItemNameToBeAdded();
        LOGGER.info("Asserting ? Items Added:"+itemsNameAddedFromRestaurantPage +" = "+"Currently in Cart:"+itemNameInCart);
        Assertions.assertThat(itemNameInCart).as("Verifying item added in cart is what was actually added from Restaurant menu").isEqualTo(itemsNameAddedFromRestaurantPage);

        int itemCountInCart = CartSectionInRestaurantScreen.get().getSingleAddedItemCountFromCartSection();
        int itemsAddedFromRestaurantPage = (Integer) context.getTestState(SAMPLE_TEST_CONTEXT.UNIT_OF_ITEMS_TO_ADD);
        LOGGER.info("Asserting ? Items Added:"+itemsAddedFromRestaurantPage +" = "+"Currently in Cart:"+itemCountInCart);
        Assertions.assertThat(itemCountInCart).as("Verifying quantity of item added in cart is matching Restaurant menu").isEqualTo(itemsAddedFromRestaurantPage);
        return this;
    }

    public int getFoodItemOrderCountFromRestaurantMenu() {
        int itemsInOrderList = RestaurantScreen.get().getFoodItemOrderCount();
        LOGGER.info("Preparing to empty the items in line of Order, currently available:"+itemsInOrderList);
        return itemsInOrderList;
    }

    public RestaurantsBL removeAddedItemsFromRestauranMenu(int itemsAlreadyAdded) {
        LOGGER.info("Removing items in line of order from Restaurant profile page:"+itemsAlreadyAdded);
        RestaurantScreen.get().decrementCartValueFromProfileSection(itemsAlreadyAdded);
        return this;
    }

    public RestaurantsBL verifyCartIsEmpty() {
        LOGGER.info("Asserting whether cart is empty or not");
        Assertions.assertThat(CartSectionInRestaurantScreen.get().isCartEmpty()).as("Verifying whether cart is empty").isEqualTo(true);
        return this;
    }
}
