package com.znsio.sample.e2e.screen.AmazonScreens;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.AmazonScreens.AmazonWeb.ProductListPageScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class ProductListPageScreen {
    private static final String SCREEN_NAME = ProductListPageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static ProductListPageScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch (platform) {
            case web:
                return new ProductListPageScreenWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);

    }
    public abstract List<WebElement> getProductLinksList();
    public abstract boolean validateListOfProducts(String Product, List<WebElement> productList);
    public abstract String getFirstProductTittle(List<WebElement> productList);
    public abstract String getFirstProductPrice();
    public abstract ProductLandingPageScreen userSelectsFirstProduct(List<WebElement> productList);
}
