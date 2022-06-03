package com.znsio.e2e.businessLayer.swiggynew;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.swiggynew.RestaurantListScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;


public class RestaurantsListBL {

    private static final Logger LOGGER = Logger.getLogger(RestaurantsListBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final SoftAssertions softly;
    private final Platform currentPlatform;

    public RestaurantsListBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }


    public RestaurantsListBL sortObBasisOf(String sortCriteria) {
        RestaurantListScreen.get().sortBy(sortCriteria);
        return this;
    }

    public RestaurantsListBL getDeliveryLocationNameSetByUser() {
        String deliveryLocationSetOnRestaurantListScreen = RestaurantListScreen.get().getDeliveryLocationName();
        String deliveryLocSearchedFor=context.getTestStateAsString(SAMPLE_TEST_CONTEXT.SEARCHED_DELIVERY_LOCATION);
        LOGGER.info("Searched For:"+deliveryLocSearchedFor+"| Got:"+deliveryLocationSetOnRestaurantListScreen);
        softly.assertThat(deliveryLocationSetOnRestaurantListScreen)
                .as("Verifying if delivery location matches what user requested for")
                .contains(deliveryLocSearchedFor);
        return this;
    }

    public RestaurantsBL selectRandomRestaurant() {
        RestaurantListScreen.get().selectRestaurantByRandomIndex();
        return new RestaurantsBL();
    }
}
