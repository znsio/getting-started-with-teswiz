package com.znsio.sample.e2e.screen.zomato;

import com.znsio.sample.e2e.screen.web.zomato.DiningOutScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class DiningOutScreen {
    private static final String SCREEN_NAME = DiningOutScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static DiningOutScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch(platform) {
            case web:
                return new DiningOutScreenWeb(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract RestaurantDetailScreen selectRestaurant();
    public abstract String getRestaurantName();
    public abstract String getRestaurantPageHeading();
    public abstract DiningOutScreen selectDineOutOption();
}
