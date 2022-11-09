package com.znsio.sample.e2e.screen.indigo;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.web.indigo.IndigoHomePageScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.apache.tools.ant.types.selectors.SelectorContainer;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class IndigoHomePageScreen {

    private static final String SCREEN_NAME = IndigoHomePageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static IndigoHomePageScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch(platform) {
            case web:
                return new IndigoHomePageScreenWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract boolean validateHomePage();

    public abstract IndigoHomePageScreen addCurrentLocationDetails() ;

    public abstract IndigoHomePageScreen addDestinationDetails() ;

    public abstract IndigoHomePageScreen addDateDetails();

    public abstract boolean validateFlightDetails();
}
