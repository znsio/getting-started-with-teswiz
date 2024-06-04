package com.znsio.sample.e2e.screen.bookingDotCom;

import com.znsio.sample.e2e.screen.web.bookingDotCom.BookingDotComFlightsScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BookingDotComFlightsScreen {
    private static final String SCREEN_NAME = BookingDotComFlightsScreen.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(SCREEN_NAME);

    public static BookingDotComFlightsScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch (platform) {
            case web:
                return new BookingDotComFlightsScreenWeb(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract BookingDotComFlightsScreen selectJourneyType(String journeyType);

    public abstract BookingDotComFlightsScreen selectTo(String destination);

    public abstract BookingDotComFlightSearchResultsScreen searchFlightOptions();
}
