package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.RestaurantListingScreen;
import com.znsio.e2e.screen.RestaurantMenuScreen;
import org.assertj.core.api.SoftAssertions;
import org.testng.asserts.SoftAssert;

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
        SoftAssert softAssert = new SoftAssert();
        String listingPageRestaurantName = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.RESTAURANTNAME);
        softAssert.assertEquals(restaurantName, listingPageRestaurantName,"Restaurant Name does not matched" );
        softly.assertThat(restaurantName).isEqualTo(listingPageRestaurantName);
        softAssert.assertAll();
        return this;
    }
    public RestaurantMenuBL selectFoodItem() {
        RestaurantMenuScreen.get().searchAndSelectFoodItem()
                .increaseTheQuanity();
        return this;
    }
}
