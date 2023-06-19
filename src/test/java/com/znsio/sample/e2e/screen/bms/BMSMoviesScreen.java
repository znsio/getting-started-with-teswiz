package com.znsio.sample.e2e.screen.bms;

import com.znsio.sample.e2e.screen.web.bms.BMSMoviesScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang.NotImplementedException;

import java.util.logging.Logger;

public abstract class BMSMoviesScreen {

    private static final String SCREEN_NAME = BMSMoviesScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static BMSMoviesScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch (platform) {
            //case android:
              //  return new AjioHomeScreenAndroid(driver, visually);
            case web:
                return new BMSMoviesScreenWeb(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }


    public abstract BMSMoviesScreen selectMovieAtPosition(String moviePosition);

    public abstract BMSMoviesScreen selectDateAfterTwoDays();

    public abstract BMSMoviesScreen selectSecondLastTimeSlotForMovie(String cinemaPosition);

    public abstract BMSSelectSeatsScreen selectNoOfSeatsToBook(String noOfSeats);
}
