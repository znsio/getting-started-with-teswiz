package com.znsio.sample.e2e.screen.Yatra;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.sample.e2e.screen.web.Yatra.YatraHomeWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class YatraHomeScreen {
    private static final String SCREEN_NAME = YatraHomeScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static YatraHomeScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch (platform) {
            case web:
                return new YatraHomeWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract YatraHomeScreen selectTripType(String tripType);
    public abstract YatraHomeScreen selectSourceCity(String sourceCity);
    public abstract YatraHomeScreen selectDestinationCity(String destinationCity);
    public abstract YatraHomeScreen selectSecondSourceCity(String sourceCity);
    public abstract YatraHomeScreen selectSecondDestinationCity(String destinationCity);

    public abstract YatraHomeScreen selectTravelDate();
}
