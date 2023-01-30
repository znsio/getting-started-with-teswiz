package com.znsio.sample.e2e.screen.android.amazonsearch;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonSearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonSearchResultsScreenAndroid extends AmazonSearchResultsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonSearchResultsScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byProductImage = By.xpath("//android.widget.Image[contains(@text, '"+ SAMPLE_TEST_CONTEXT.PRODUCT_NAME + "')]");

    public AmazonSearchResultsScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public int getNumberOfProductsFound() {
        LOGGER.info("Checking number of results being displayed");
        driver.waitTillElementIsPresent(byProductImage);
        return driver.findElements(byProductImage).size();
    }
}
