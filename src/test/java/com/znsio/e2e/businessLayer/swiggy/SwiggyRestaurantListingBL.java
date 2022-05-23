package com.znsio.e2e.businessLayer.swiggy;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.swiggy.RestaurantListingScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class SwiggyRestaurantListingBL {

    private static final Logger LOGGER = Logger.getLogger(SwiggyRestaurantListingBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public SwiggyRestaurantListingBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }



    public SwiggyRestaurantListingBL sortRestaurantsByRating() {
        RestaurantListingScreen.get().sortRestaurantByRating();
        return this;
    }

    public SwiggyRestaurantListingBL getRestaurantCountForSearchedLocation(){
        int actualSearchedRestaurantcount=RestaurantListingScreen.get().getRestaurantCountForSearchedLocation();
        LOGGER.info("Asserting that found restaurants:"+actualSearchedRestaurantcount+" is greater than 0" );
        softly.assertThat(actualSearchedRestaurantcount).isGreaterThan(0);
        return this;
    }

    public SwiggyRestaurantProfileBL selectRestaurantByName(String restaurantName) {
        RestaurantListingScreen.get().selectRestaurantByName(restaurantName);
        return new SwiggyRestaurantProfileBL();
    }

    public SwiggyRestaurantProfileBL selectRestaurantByIndex(String oneBasedIndxValue) {
        RestaurantListingScreen.get().selectRestaurantByIndex(oneBasedIndxValue);
        return new SwiggyRestaurantProfileBL();
    }
}
