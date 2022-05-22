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

    public SwiggyRestaurantProfileBL getFoodItemsCountValueFromCartSection(String foodItemsValue) {
        String cartCounter=CartSectionInRestaurantProfileScreen.get().getItemsCountInCartSection();
        LOGGER.info("Asserting Cart Section value from Restaurant Profile Page:"+cartCounter+"|Expected Value:"+foodItemsValue);
        softly.assertThat(cartCounter).isEqualTo(foodItemsValue);
        return this;
    }
}
