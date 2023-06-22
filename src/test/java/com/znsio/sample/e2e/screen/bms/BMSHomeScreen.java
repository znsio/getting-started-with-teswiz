package com.znsio.sample.e2e.screen.bms;

import com.znsio.sample.e2e.screen.web.bms.BMSHomeScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang.NotImplementedException;

import java.util.logging.Logger;

public abstract class BMSHomeScreen {

    private static final String SCREEN_NAME = BMSHomeScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static BMSHomeScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch (platform) {
            //case android:
              //  return new AjioHomeScreenAndroid(driver, visually);
            case web:
                return new BMSHomeScreenWeb(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract BMSHomeScreen searchForLocation(String location);

    public abstract BMSMoviesScreen selectMoviesCategory();
}
