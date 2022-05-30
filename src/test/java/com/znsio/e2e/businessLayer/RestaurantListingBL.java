package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.CartScreen;
import com.znsio.e2e.screen.RestaurantListingScreen;
import com.znsio.e2e.screen.RestaurantMenuScreen;
import com.znsio.e2e.screen.SwiggyHomeScreen;
import org.assertj.core.api.SoftAssertions;
import org.testng.asserts.SoftAssert;

public class RestaurantListingBL {

    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public RestaurantListingBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public RestaurantListingBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.SWIGGY_USER;
        this.currentPlatform = Runner.platform;
    }


    public RestaurantListingBL validateLocation(String userEnteredLocation) {
        String restaurantListingPageLocation = RestaurantListingScreen.get().getLocationName();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(restaurantListingPageLocation,userEnteredLocation,"Location does not Matches");
        softAssert.assertAll();
        return this;
    }


    public RestaurantListingBL selectRatingTab(String criteria){
        RestaurantListingScreen.get().clickOnRating(criteria);
        return this;
    }

    public RestaurantListingBL validateRestaurantList() {
        int restaurantList = RestaurantListingScreen.get().getRestaurantList();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(restaurantList ,0,"Restaurant List is empty");
        softAssert.assertAll();
        return this;
    }

    public RestaurantMenuBL clickOnRestauturant() {
        RestaurantListingScreen.get().selectRestaurant();
    return new RestaurantMenuBL();
    }


}
