package com.znsio.sample.e2e.businessLayer.zomato;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.zomato.DeliveryScreen;
import com.znsio.sample.e2e.screen.zomato.DineOutScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;


public class DineOutBL {
    private static final Logger LOGGER = Logger.getLogger(DineOutBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public DineOutBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public DineOutBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public BookATableBL selectRestaurant() {
        LOGGER.info("selectRestaurant(): Select restaurant for dine out");

        DineOutScreen dineOutScreen = DeliveryScreen.get().clickDineOutTab();
        String cityName = context.getTestStateAsString(SAMPLE_TEST_CONTEXT.CITY_NAME);

        assertThat(dineOutScreen.getRestaurantPageHeading())
                .as("User is on different city restaurant list: %s", cityName)
                .containsIgnoringCase(cityName);

        String restaurantName = dineOutScreen.getRestaurantName();

        assertThat(dineOutScreen.selectRestaurant().isRestaurantNameVisible(restaurantName))
                .as(String.format("User is no on the selected restaurant page: ", restaurantName)).isTrue();

        return new BookATableBL();
    }
}
