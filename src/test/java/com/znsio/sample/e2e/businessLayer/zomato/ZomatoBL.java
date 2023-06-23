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
        boolean isHomePageLaunchedSuccessfully = HomePageScreen.get().verifyHomePageLaunch();
        assertThat(isHomePageLaunchedSuccessfully).as("Url mismatch occurred, try opening the correct url for the application").isTrue();
        LOGGER.info("Homepage launched successfully");
        return this;
    }

    public ZomatoBL selectDiningOption() {
        HomePageScreen.get().selectDiningOption();
        return this;
    }

    public ZomatoBL verifyRedirectionToDineoutPage() {
        boolean isRedirectionToDineoutPageSuccessful = DineOutScreen.get().verifyRedirectionToDineoutPage();
        assertThat(isRedirectionToDineoutPageSuccessful).as("Load the correct page, redirection to dineout was not successful ").isTrue();
        LOGGER.info("Redirected to Dine-out page successfully");
        return this;
    }

    public ZomatoBL selectLocationForRestaurant() {
        DineOutScreen.get().selectLocation();
        return this;
    }

    public ZomatoBL verifySelectedLocationWithLocationDisplayed() {
        boolean isCorrectLocationSelected = DineOutScreen.get().verifyLocationDisplayed();
        assertThat(isCorrectLocationSelected).as("Incorrect/Improper location was set").isTrue();
        LOGGER.info("Displayed Location is correct");
        return this;
    }

    public ZomatoBL chooseRestaurantFromSelectedLocation() {
        DineOutScreen.get().selectSpecificRestaurant();
        return this;
    }

    public ZomatoBL verifySelectedRestaurantWithRestaurantDisplayed() {
        boolean isCorrectRestaurantDisplayed = RestaurantScreen.get().verifyRestaurantDisplayed();
        assertThat(isCorrectRestaurantDisplayed).as("incorrect location set").isTrue();
        LOGGER.info("Displayed Restaurant is correct");
        return this;
    }

    public ZomatoBL bookATableOnASpecificDateAndTimeForGuests() {
        boolean isBookATableOptionSelected = RestaurantScreen.get().clickOnBookATable().verifyBookATableOptionSelected();
        assertThat(isBookATableOptionSelected).as("Book a Table option not selected").isTrue();
        LOGGER.info("Book a Table option successfully selected");

        boolean isDateDisplayedSameAsSelected = RestaurantScreen.get().selectDate().verifyDateSelectedMatchingWithDateDisplayed();
        softly.assertThat(isDateDisplayedSameAsSelected).as("Incorrect date displayed or no date selected").isTrue();
        LOGGER.info("Correct Date selected");

        boolean areNumberOfGuestsDisplayedSameAsSelected = RestaurantScreen.get().selectNumberOfGuests().verifyNumberOfGuestsSelectedMatchingWithNumberOfGuestsDisplayed();
        softly.assertThat(areNumberOfGuestsDisplayedSameAsSelected).as("Incorrect number of guests displayed or no guests selected").isTrue();
        LOGGER.info("Correct Number of Guests selected");

        boolean isTimeSlotSelected = RestaurantScreen.get().selectTimeSlot().verifyTimeSlotSelected();
        softly.assertThat(isTimeSlotSelected).as("No timeslot selected").isTrue();
        LOGGER.info("Timeslot selected");

        boolean areGuestDetailsCorrect = RestaurantScreen.get().fillGuestBasicDetails().verifyGuestDetails();
        softly.assertThat(areGuestDetailsCorrect).as("Guest details filled are incorrect or incomplete").isTrue();
        LOGGER.info("Guest details filled correctly");
        return this;
    }

    public ZomatoBL verifyLoginPopUpMessage() {
        boolean isLoginPopUpMessageDisplayed = RestaurantScreen.get().verifyLoginPopUpMessageDisplayed();
        assertThat(isLoginPopUpMessageDisplayed).as("Login Pop Up message not displayed").isTrue();
        LOGGER.info("Login Pop up Message displayed");
        return this;
    }
}
