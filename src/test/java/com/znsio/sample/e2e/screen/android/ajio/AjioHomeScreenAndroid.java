package com.znsio.sample.e2e.screen.android.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AjioHomeScreenAndroid
        extends AjioHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AjioHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byStartSearchBoxId = By.id("com.ril.ajio:id/llpsTvSearch");
    private static final By bySearchFieldId = By.id("com.ril.ajio:id/searchETV");
    private static final By byStartSearchId = By.id("com.ril.ajio:id/search_image");

    public AjioHomeScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public AjioSearchResultsScreen searchFor(String product) {
        LOGGER.info(String.format("Search for: '%s'", product));
        driver.waitTillElementIsPresent(byStartSearchBoxId)
                .click();
        WebElement searchBoxElement = driver.waitTillElementIsPresent(bySearchFieldId);
        searchBoxElement.click();
        searchBoxElement.clear();
        searchBoxElement.sendKeys(product);
        visually.checkWindow(SCREEN_NAME, String.format("Search for product: %s", product));
        driver.waitTillElementIsPresent(byStartSearchId)
                .click();
        return AjioSearchResultsScreen.get();
    }
}
