package com.znsio.sample.e2e.screen.ajio;

import com.znsio.sample.e2e.screen.web.ajio.AjioWishlistScreenWeb;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class AjioWishlistScreen {
    private static final String SCREEN_NAME = AjioWishlistScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static AjioWishlistScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch (platform) {
            case web:
                return new AjioWishlistScreenWeb(driver, visually);

        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract boolean isProductPresentInWishlist(String productName);

    public abstract AjioWishlistScreen selectSizeAndMoveToBag(String productSize);

    public abstract AjioCartScreen proceedToBag();

    public abstract boolean isproductSizeInStock(String productSize);

    public abstract AjioWishlistScreen clearWishlist();

    public abstract boolean isWishlistEmpty();
}