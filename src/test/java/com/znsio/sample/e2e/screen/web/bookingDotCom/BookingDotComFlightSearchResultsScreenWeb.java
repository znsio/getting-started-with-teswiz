package com.znsio.sample.e2e.screen.web.bookingDotCom;

import com.znsio.sample.e2e.screen.bookingDotCom.BookingDotComFlightSearchResultsScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookingDotComFlightSearchResultsScreenWeb
        extends BookingDotComFlightSearchResultsScreen {
    private static final String SCREEN_NAME =
            BookingDotComFlightSearchResultsScreenWeb.class.getSimpleName();
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final Logger LOGGER = LogManager.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    public BookingDotComFlightSearchResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
}
