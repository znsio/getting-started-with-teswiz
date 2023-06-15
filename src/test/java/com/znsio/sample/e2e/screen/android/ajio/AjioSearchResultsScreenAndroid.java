package com.znsio.sample.e2e.screen.android.ajio;

import com.znsio.sample.e2e.screen.ajio.AjioProductDetailsScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AjioSearchResultsScreenAndroid
        extends AjioSearchResultsScreen {
    private static final String SCREEN_NAME = AjioSearchResultsScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byNumberOfProductsFoundId = By.id(
            "com.ril.ajio:id/toolbar_subtitle_tv");
    private final Driver driver;
    private final Visual visually;
    private final By bySearchStringId = By.id("com.ril.ajio:id/toolbar_title_tv");

    public AjioSearchResultsScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public int getNumberOfProductsFound() {
        String numberOfProducts = driver.waitTillElementIsPresent(byNumberOfProductsFoundId)
                .getText();
        LOGGER.info(String.format("Found '%s'", numberOfProducts));
        return Integer.parseInt(numberOfProducts.split(" ")[0]);
    }

    @Override
    public String getActualSearchString() {
        String actualSearchString = driver.waitTillElementIsPresent(bySearchStringId).getText();
        LOGGER.info(String.format("Actual search was for: '%s'", actualSearchString));
        visually.checkWindow(SCREEN_NAME, "Search results screen");
        return actualSearchString;
    }

    @Override
    public AjioProductDetailsScreen goToProductDetails(int itemNumber) {
        throw new NotImplementedException("Not implemented for Android");
    }
}
