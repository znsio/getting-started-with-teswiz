package com.znsio.sample.e2e.screen.zomato;

import com.znsio.sample.e2e.screen.web.zomato.RestaurantScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class RestaurantScreen {
    private static final String SCREEN_NAME = RestaurantScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static RestaurantScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch (platform) {
            case web:
                return new RestaurantScreenWeb(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract boolean isCorrectRestaurantSelected();

    public abstract RestaurantScreen clickOnBookATable();

    public abstract boolean isBookATableOptionSelected();

    public abstract RestaurantScreen selectDate();

    public abstract boolean isCorrectDateSelected();

    public abstract RestaurantScreen selectNumberOfGuests();

    public abstract boolean areCorrectNumberOfGuestsSelected();

    public abstract RestaurantScreen selectTimeSlot();

    public abstract RestaurantScreen fillGuestBasicDetails();

    public abstract boolean isCorrectTimeSlotSelected();

    public abstract boolean areGuestDetailsCorrect();

    public abstract boolean isLoginPopUpMessageVisible();
}
