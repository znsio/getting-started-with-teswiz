package com.znsio.sample.e2e.screen.bookMyShow;

import com.znsio.sample.e2e.screen.web.bookMyShow.BookMyShowLoginPageScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class BookMyShowLoginPageScreen {
    private static final String SCREEN_NAME = BookMyShowLoginPageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static BookMyShowLoginPageScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch (platform) {
            case web:
                return new BookMyShowLoginPageScreenWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }


    public abstract boolean isBookMyShowHomepageLaunched();

    public abstract boolean isLocationDelhi(String cityName);

    public abstract Object isValidLogin();

    public abstract MoviesListPageScreen moviesPage();
}
