package com.znsio.sample.e2e.businessLayer.zomato;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.zomato.DineOutScreen;
import com.znsio.sample.e2e.screen.zomato.HomePageScreen;
import com.znsio.sample.e2e.screen.zomato.RestaurantScreen;
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

    public ZomatoBL launchHomePageAndValidate() {
        boolean isHomePageLaunchedSuccessfully = HomePageScreen.get().isHomePageLaunchedSuccessfully();
        assertThat(isHomePageLaunchedSuccessfully).as("Url mismatch occurred, try opening the correct url for the application").isTrue();
        LOGGER.info("Homepage launched successfully");
        return this;
    }

    public ZomatoBL clickOnDiningOption() {
        HomePageScreen.get().clickOnDiningOption();
        return this;
    }

    public ZomatoBL verifyRedirectionToDineoutPage() {
        boolean isRedirectionToDineoutPageSuccessful = DineOutScreen.get().verifyRedirectionToDineoutPage();
        assertThat(isRedirectionToDineoutPageSuccessful).as("Load the correct page, redirection to dineout was not successful ").isTrue();
        LOGGER.info("Redirected to Dine-out page successfully");
        return this;
    }

    public ZomatoBL selectLocationForRestaurants(String location) {
        DineOutScreen.get().selectLocationForRestaurants(location);
        return this;
    }

    public ZomatoBL verifySelectedLocation(String location) {
        boolean isCorrectLocationSelected = DineOutScreen.get().isCorrectLocationSelected(location);
        assertThat(isCorrectLocationSelected).as("Incorrect/Improper location was set").isTrue();
        LOGGER.info("Selected Location is correct");
        return this;
    }

    public ZomatoBL selectSpecificRestaurant() {
        DineOutScreen.get().selectSpecificRestaurant();
        return this;
    }

    public ZomatoBL verifySelectedRestaurant() {
        boolean isCorrectRestaurantSelected = RestaurantScreen.get().isCorrectRestaurantSelected();
        assertThat(isCorrectRestaurantSelected).as("incorrect location set").isTrue();
        LOGGER.info("Correct Restaurant is selected");
        return this;
    }

    public ZomatoBL bookTableForGuestDayForASpecificDate() {
        boolean isBookATableOptionSelected = RestaurantScreen.get().clickOnBookATable().isBookATableOptionSelected();
        assertThat(isBookATableOptionSelected).as("Book a Table option not selected").isTrue();
        LOGGER.info("Book a Table option successfully selected");

        boolean isCorrectDateSelected = RestaurantScreen.get().selectDate().isCorrectDateSelected();
        assertThat(isCorrectDateSelected).as("Incorrect date or no date selected").isTrue();
        LOGGER.info("Correct Date selected");

        boolean areCorrectNumberOfGuestsSelected = RestaurantScreen.get().selectNumberOfGuests().areCorrectNumberOfGuestsSelected();
        assertThat(areCorrectNumberOfGuestsSelected).as("Incorrect number of guests or no guests selected").isTrue();
        LOGGER.info("Correct Number of Guests selected");

        boolean isCorrectTimeSlotSelected = RestaurantScreen.get().selectTimeSlot().isCorrectTimeSlotSelected();
        assertThat(isCorrectTimeSlotSelected).as("Incorrect time slot or no timeslot selected").isTrue();
        LOGGER.info("Correct Timeslot selected");

        boolean areGuestDetailsCorrect = RestaurantScreen.get().fillGuestBasicDetails().areGuestDetailsCorrect();
        assertThat(areGuestDetailsCorrect).as("Incorrect/No guest details selected").isTrue();
        LOGGER.info("Correct Guest details filled");
        return this;
    }

    public ZomatoBL verifyLoginPopUpMessage() {
        boolean isLoginPopUpMessageVisible = RestaurantScreen.get().isLoginPopUpMessageVisible();
        assertThat(isLoginPopUpMessageVisible).as("Login Pop Up mesaage didn't occur").isTrue();
        LOGGER.info("Login Pop up Message Occurred");
        return this;
    }
}
