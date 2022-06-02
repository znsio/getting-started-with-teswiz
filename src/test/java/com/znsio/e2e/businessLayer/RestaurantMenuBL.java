package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.CartScreen;
import com.znsio.e2e.screen.RestaurantListingScreen;
import com.znsio.e2e.screen.RestaurantMenuScreen;
import org.assertj.core.api.SoftAssertions;

public class RestaurantMenuBL {

    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public RestaurantMenuBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public RestaurantMenuBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.SWIGGY_USER;
        this.currentPlatform = Runner.platform;
    }
    public RestaurantMenuBL validateRestaurantName() {
        String restaurantName = RestaurantMenuScreen.get().getRestaurantName();
        String listingPageRestaurantName = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.RESTAURANTNAME);
        softly.assertThat(restaurantName).as("Restaurant Name doesnot match with selected one").isEqualTo(listingPageRestaurantName);
        return this;
    }
    public RestaurantMenuBL userAddsTheItemsInTheCart() {
        RestaurantMenuScreen.get().addFoodItemFromMenu();
        softly.assertThat(validateCartOptionInRestaurantMenuScreen()).as("Cart is empty").isTrue();
        return this;
    }

    public RestaurantMenuBL userSelectRestaurantFromListingPage(){
        RestaurantListingScreen.get().selectRestaurant();
        validateRestaurantName();
        return this;
    }

    private Boolean validateCartOptionInRestaurantMenuScreen(){
        return CartScreen.get().validateCartOptionVisible();
    }
}
