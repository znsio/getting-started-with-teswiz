package com.znsio.sample.e2e.screen.zomato;

import com.znsio.sample.e2e.screen.web.zomato.RestaurantDetailScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class RestaurantDetailScreen {
    private static final String SCREEN_NAME = RestaurantDetailScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static RestaurantDetailScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch(platform) {
            case web:
                return new RestaurantDetailScreenWeb(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract RestaurantDetailScreen clickBookATableTab();
    public abstract RestaurantDetailScreen selectBookingDate(int aheadDays);
    public abstract RestaurantDetailScreen selectGuestCount(int guestCount);
    public abstract RestaurantDetailScreen clickBookButton();
    public abstract boolean isLoginWindowVisible();
    public abstract boolean isRestaurantNameVisible(String restaurantName);
    public abstract boolean isSelectedAheadDateCorrect(int aheadDays);
    public abstract boolean isNumberOfGuestSelectedCorrect(int guestCount);
    public abstract boolean isSelectTimeOptionEnabled();
    public abstract RestaurantDetailScreen addFirstName(String firstName);
    public abstract RestaurantDetailScreen addLastName(String lastName);
    public abstract RestaurantDetailScreen addEmail(String userEmail);
    public abstract RestaurantDetailScreen addPhoneNumber(String phoneNo);
    public abstract RestaurantDetailScreen selectTimeSlot();
}
