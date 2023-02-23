package com.znsio.sample.e2e.screen.ajio;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.android.ajio.HomeScreenAndroid;
import com.znsio.sample.e2e.screen.android.ajio.ProductDetailScreenAndroid;
import com.znsio.sample.e2e.screen.web.ajio.ProductDetailScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class ProductDetailScreen {
    private static final String SCREEN_NAME = ProductDetailScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static ProductDetailScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch(platform) {
            case android:
                return new ProductDetailScreenAndroid(driver, visually);
            case web:
                return new ProductDetailScreenWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract ProductDetailScreen selectProductSize();
    public abstract ProductDetailScreen addToCart();
    public abstract String getProductBrand();
    public abstract ProductDetailScreen proceedToCart();
}