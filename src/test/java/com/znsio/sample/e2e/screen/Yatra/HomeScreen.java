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
    public abstract HomeScreen selectSourceCity(String sourceCity, String tripNumber);
    public abstract HomeScreen selectDestinationCity(String destinationCity , String tripNumber);
    public abstract HomeScreen selectTravelDate();
    public abstract String getSourceCity(String tripNumber);

    public abstract String getDestinationCity(String tripNumber);

    public abstract String getTitle();

    public abstract HomeScreen closeAdvertisement();

    public abstract HomeScreen selectClassOption();

    public abstract HomeScreen addAdults(String adultCount);

    public abstract HomeScreen addChildren(String childrenCount);

    public abstract HomeScreen addInfants(String infantCount);
}
