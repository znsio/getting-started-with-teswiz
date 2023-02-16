package com.znsio.sample.e2e.screen.web.ajio2;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio2.AjioSearchResultsScreen;
import com.znsio.sample.e2e.screen.android.ajio2.AjioSearchResultsScreenAndroid;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AjioSearchResultsScreenWeb extends AjioSearchResultsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AjioSearchResultsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byNumberOfProductsFoundId = By.cssSelector("[class=\"length\"]");
    private static final By bySearchStringId = By.cssSelector("[class=\"header2\"]");

    public AjioSearchResultsScreenWeb(Driver driver, Visual visually) {
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
        String actualSearchString = driver.waitTillElementIsPresent(bySearchStringId)
                .getText();
        LOGGER.info(String.format("Actual search was for: '%s'", actualSearchString));
        visually.checkWindow(SCREEN_NAME, "Search results screen");
        return actualSearchString;
    }
}
