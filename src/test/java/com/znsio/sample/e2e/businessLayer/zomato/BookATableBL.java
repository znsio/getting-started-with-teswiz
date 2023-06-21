package com.znsio.sample.e2e.businessLayer.zomato;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.zomato.RestaurantDetailScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BookATableBL {
    private static final Logger LOGGER = Logger.getLogger(BookATableBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public BookATableBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public BookATableBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public BookATableBL enterBookingDetails(int guestCount, int aheadDays) {
        LOGGER.info(String.format("bookATable(): book a table with %d number of guest and %d days ahead date", guestCount, aheadDays));

        RestaurantDetailScreen restaurantDetailScreen = RestaurantDetailScreen.get()
                .clickBookATableTab()
                .selectBookingDate(aheadDays)
                .selectGuestCount(guestCount);

        softly.assertThat(restaurantDetailScreen.isSelectTimeOptionEnabled())
                .as("Select time option did not get enabled").isTrue();

        assertThat(restaurantDetailScreen.isNumberOfGuestSelectedCorrect(guestCount))
                .as("The count of guest is different").isTrue();

        assertThat(restaurantDetailScreen.isSelectedAheadDateCorrect(aheadDays))
                .as("The date selected is different").isTrue();

        Map<String, Object> userDetails = Runner.getTestDataAsMap("userDetail");
        restaurantDetailScreen.selectTimeSlot()
                .addFirstName(userDetails.get("firstName").toString())
                .addLastName(userDetails.get("lastName").toString())
                .addEmail(userDetails.get("userEmail").toString())
                .addPhoneNumber(userDetails.get("phoneNo").toString())
                .clickBookButton();

        return this;
    }
}
