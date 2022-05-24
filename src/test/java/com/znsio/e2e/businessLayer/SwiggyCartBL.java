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

public class SwiggyCartBL {

    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public SwiggyCartBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public SwiggyCartBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public SwiggyCartBL openSwiggyHome() {
        SwiggyHomeScreen.get();
        return this;
    }
    public SwiggyCartBL addLocation(String location) {
        context.addTestState(SAMPLE_TEST_CONTEXT.USER_ENTERED_LOCATION, location);
        SwiggyHomeScreen.get().addRestaurantLocation(location);
        return this;
    }


    public SwiggyCartBL validateLocationInRestaurantPage() {
        String userEnteredLocation = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.USER_ENTERED_LOCATION);
        String restaurantListingPageLocation = RestaurantListingScreen.get().getLocationName();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(restaurantListingPageLocation,userEnteredLocation,"Location does not Matches");
        softAssert.assertAll();
        return this;
    }

    public SwiggyCartBL selectRatingTab(){
        RestaurantListingScreen.get().clickOnRating();
        return this;
    }

    public SwiggyCartBL validateRestaurantList() {
        int restaurantList = RestaurantListingScreen.get().getRestaurantList();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(restaurantList ,0,"Restaurant List is empty");
        softAssert.assertAll();
        return this;
    }

    public SwiggyCartBL clickOnRestaturant() {
        RestaurantListingScreen.get().selectRestaurant();
    return this;
    }

    public SwiggyCartBL validateRestaurantName() {
        String restaurantName = RestaurantMenuScreen.get().getRestaurantName();
        SoftAssert softAssert = new SoftAssert();
        String listingPageRestaurantName = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.RESTAURANTNAME);
        softAssert.assertEquals(restaurantName, listingPageRestaurantName,"Restaurant Name does not matched" );
        softly.assertThat(restaurantName).isEqualTo(listingPageRestaurantName);
        softAssert.assertAll();
        return this;
    }


    public SwiggyCartBL selectFoodItem() {
        RestaurantMenuScreen.get().searchAndSelectFoodItem()
                .increaseTheQuanity();

        return this;
    }

    public SwiggyCartBL openCart() {
        CartScreen.get().openCartOption();
        return this;
    }

    public SwiggyCartBL validateItemName() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(CartScreen.get().getItemName(),context.getTestStateAsString(SAMPLE_TEST_CONTEXT.FOOD_ITEM_NAME),"Item Name doesnot matched");
        softAssert.assertAll();
        return this;
    }
}
