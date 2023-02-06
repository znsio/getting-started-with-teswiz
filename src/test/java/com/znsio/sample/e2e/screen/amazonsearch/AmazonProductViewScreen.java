package com.znsio.sample.e2e.screen.amazonsearch;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.android.amazon.AmazonProductViewAndroid;
import com.znsio.sample.e2e.screen.web.amazon.AmazonHomeScreenWeb;
import com.znsio.sample.e2e.screen.web.amazon.AmazonProductViewWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class AmazonProductViewScreen {
    private static final String SCREEN_NAME = AmazonProductViewScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static AmazonProductViewScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch(platform) {
            case android:
                return new AmazonProductViewAndroid(driver, visually);
            case web:
                return new AmazonProductViewWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract AmazonProductViewScreen selectFirstProduct();

    public abstract boolean verifyProductDetails();

    public abstract AmazonProductViewScreen clickAddToCartButton();

    public abstract String getAddedToCartMessage();
}
