package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.RestaurantListingScreen;
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


    public RestaurantListingBL userSortTheRestaurantListByCriteria(String criteria) {
        RestaurantListingScreen.get().searchRestaurantBy(criteria);
        int restaurantCount = RestaurantListingScreen.get().getRestaurantCount();
        softly.assertThat(restaurantCount).as("Restaurant List is empty").isNotEqualTo(0);
        return this;

    }


}
