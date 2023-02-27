package com.znsio.sample.e2e.screen.Yatra;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.web.Yatra.HomeWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class HomeScreen {
    private static final String SCREEN_NAME = HomeScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static HomeScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch (platform) {
            case web:
                return new HomeWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract HomeScreen selectTripType(String tripType);
    public abstract HomeScreen selectFirstSourceCity(String sourceCity);
    public abstract HomeScreen selectFirstDestinationCity(String destinationCity);
    public abstract HomeScreen selectSecondSourceCity(String sourceCity);
    public abstract HomeScreen selectSecondDestinationCity(String destinationCity);
    public abstract HomeScreen selectTravelDate();
    public abstract String getTitle();
    public abstract HomeScreen closeAdvertisement();
    public abstract HomeScreen selectTravellerOption();
    public abstract HomeScreen addAdults(int adultCount);
    public abstract HomeScreen addChildren(int childrenCount);
    public abstract HomeScreen addInfants(int infantCount);
    public abstract HomeScreen selectClass(String flightClass);
    public abstract HomeScreen selectNonStop();
    public abstract HomeScreen selectSearch();
    public abstract int getTravelCount();
    public abstract String getFirstSourceCity();
    public abstract String getFirstDestinationCity();
    public abstract String getSecondSourceCity();
    public abstract String getSecondDestinationCity();
}
