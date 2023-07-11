package com.znsio.sample.e2e.screen.ajio;

import com.znsio.sample.e2e.screen.android.ajio.AjioHomeScreenAndroid;
import com.znsio.sample.e2e.screen.web.ajio.AjioHomeScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class AjioHomeScreen {
    private static final String SCREEN_NAME = AjioHomeScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static AjioHomeScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch (platform) {
            case android:
                return new AjioHomeScreenAndroid(driver, visually);
            case web:
                return new AjioHomeScreenWeb(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract AjioSearchResultsScreen searchFor(String product);

    public abstract AjioHomeScreen goToMenu();

    public abstract AjioSearchResultsScreen selectProductFromCategory(String product, String category, String gender);
}
