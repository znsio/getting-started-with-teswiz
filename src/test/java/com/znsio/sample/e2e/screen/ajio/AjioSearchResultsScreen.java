package com.znsio.sample.e2e.screen.ajio;

import com.znsio.sample.e2e.screen.android.ajio.AjioSearchResultsScreenAndroid;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public abstract class AjioSearchResultsScreen {
    private static final String SCREEN_NAME = AjioSearchResultsScreen.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(SCREEN_NAME);

    public static AjioSearchResultsScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch (platform) {
            case android:
                return new AjioSearchResultsScreenAndroid(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract int getNumberOfProductsFound();

    public abstract String getActualSearchString();

    public abstract boolean isProductListLoaded(String product);

    public abstract AjioProductScreen selectProduct();

    public abstract int numberOfProductFound();
}
