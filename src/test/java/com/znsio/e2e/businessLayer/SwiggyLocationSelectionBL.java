package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.RestaurantListingScreen;
import com.znsio.e2e.screen.SwiggyHomeScreen;
import org.assertj.core.api.SoftAssertions;
import org.testng.asserts.SoftAssert;

public class SwiggyLocationSelectionBL {


    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;
    public SwiggyLocationSelectionBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public SwiggyLocationSelectionBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.SWIGGY_USER;
        this.currentPlatform = Runner.platform;
    }

    public SwiggyLocationSelectionBL openSwiggyHome() {
        SwiggyHomeScreen.get();
        return this;
    }
    public SwiggyLocationSelectionBL setGeoLocation(String userSelectedLocation){
        SwiggyHomeScreen.get().addRestaurantLocation(userSelectedLocation);
        return this;
    }

    public RestaurantListingBL verifyLocationIsSet(String userEnteredLocation) {
        String restaurantListingPageLocation = RestaurantListingScreen.get().getLocationName();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(restaurantListingPageLocation, userEnteredLocation, "Location does not Matches");
        return new RestaurantListingBL();
    }

    public SwiggyLocationSelectionBL selectLocationFromHomePage(String geoLocation){
        SwiggyHomeScreen.get().addRestaurantLocation(geoLocation);
        return this;
    }


}
