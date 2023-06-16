package com.znsio.sample.e2e.businessLayer.zomato;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.zomato.DiningOutScreen;
import com.znsio.sample.e2e.screen.zomato.RestaurantDetailScreen;
import com.znsio.sample.e2e.screen.zomato.ZomatoHomeScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;


public class ZomatoBL {

    private static final Logger LOGGER = Logger.getLogger(ZomatoBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ZomatoBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ZomatoBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public ZomatoBL selectDiningLocation(String cityName) {
        LOGGER.info("selectDiningLocation(): select the location for booking");

        assertThat(ZomatoHomeScreen.get().selectLocation(cityName)
                .selectDineOutOption()
                .getPageHeading())
                .as("The select city name is different")
                .containsIgnoringCase(cityName);
        return this;
    }

    public ZomatoBL bookATable(int guestCount, int aheadDays) {
        LOGGER.info(String.format("bookATable(): book a table with %d number of guest and %d days ahead date", guestCount, aheadDays));

        String restaurantName = DiningOutScreen.get().getRestaurantName();

        assertThat(DiningOutScreen.get().selectRestaurant().verifyRestaurantNameVisible(restaurantName))
                .as("Different restaurant detail page obtained").isTrue();

        RestaurantDetailScreen restaurantDetailScreen = RestaurantDetailScreen.get()
                .selectBookATable()
                .selectBookingDate(aheadDays)
                .enterGuestCount(guestCount);

        softly.assertThat(restaurantDetailScreen.isSelectTimeOptionEnabled())
                .as("Select time option did not get enabled").isTrue();

        assertThat(restaurantDetailScreen.isNumberOfGuestSelectedCorrect(guestCount))
                .as("The count of guest is different").isTrue();

        assertThat(restaurantDetailScreen.isSelectedAheadDateCorrect(aheadDays))
                .as("The date selected is different").isTrue();

        restaurantDetailScreen.selectBookOption();
        return this;
    }

    public ZomatoBL verifyLoginOption() {
        LOGGER.info("verifyLoginOption(): verify login option");
        RestaurantDetailScreen.get().verifyLogin();
        return this;
    }
}
