package com.znsio.sample.e2e.screen.zomato;

import com.znsio.sample.e2e.screen.web.zomato.ZomatoScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class ZomatoScreen {
    private static final String SCREEN_NAME = ZomatoScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static ZomatoScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch (platform) {
            case web:
                return new ZomatoScreenWeb(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract boolean isHomePageLaunchedSuccessfully();

    public abstract ZomatoScreen clickOnDiningOption();

    public abstract boolean verifyRedirectionToDineoutPage();

    public abstract ZomatoScreen selectLocationForRestaurants(String location);

    public abstract boolean isCorrectLocationSelected(String location);

    public abstract ZomatoScreen selectSpecificRestaurant();

    public abstract boolean isCorrectRestaurantSelected();

    public abstract ZomatoScreen clickOnBookATable();

    public abstract boolean isBookATableOptionSelected();

    public abstract ZomatoScreen selectDate();

    public abstract boolean isCorrectDateSelected();

    public abstract ZomatoScreen selectNumberOfGuests();

    public abstract boolean areCorrectNumberOfGuestsSelected();

    public abstract ZomatoScreen selectTimeSlot();

    public abstract ZomatoScreen fillGuestBasicDetails();
}
