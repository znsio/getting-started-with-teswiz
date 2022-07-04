package com.znsio.sample.e2e.screen.amazon;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.web.SearchResultsScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.List;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class SearchResultsScreen {

    private static final String SCREEN_NAME = SearchResultsScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static SearchResultsScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch (platform) {
            case web:
                return new SearchResultsScreenWeb(driver, visually);
            default:
                throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
        }

    }

    public abstract String openProductByIndex(int productIndexInSearchResults);

    public abstract List<String> getAllSearchResults();

}
