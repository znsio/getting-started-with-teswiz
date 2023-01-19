package com.znsio.sample.e2e.screen.web.amazonsearch;

import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonSearchResultsScreen;
import com.znsio.sample.e2e.screen.android.ajio.AjioSearchResultsScreenAndroid;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonSearchResultsScreenWeb extends AmazonSearchResultsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonSearchResultsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By ByProductImage = By.xpath("//img[@class='s-image']");

    public AmazonSearchResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public int getNumberOfProductsFound() {
        LOGGER.info("Checking number of results being displayed");
        driver.waitTillElementIsPresent(ByProductImage);
        return driver.findElements(ByProductImage).size();
    }
}
