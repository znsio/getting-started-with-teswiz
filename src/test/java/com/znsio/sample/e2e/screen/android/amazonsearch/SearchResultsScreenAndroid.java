package com.znsio.sample.e2e.screen.android.amazonsearch;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class SearchResultsScreenAndroid extends SearchResultsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SearchResultsScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byProductImage = By.xpath("//android.widget.Image[contains(@text, '"+ SAMPLE_TEST_CONTEXT.PRODUCT_NAME + "')]");

    public SearchResultsScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public int getNumberOfProductsFound() {
        LOGGER.info("Checking number of results being displayed");
        driver.waitTillElementIsPresent(byProductImage);
        visually.checkWindow(SCREEN_NAME, "List of products displayed.");
        return driver.findElements(byProductImage).size();
    }
}
