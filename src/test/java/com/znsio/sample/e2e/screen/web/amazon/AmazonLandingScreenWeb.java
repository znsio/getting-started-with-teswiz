package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonLandingScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonLandingScreenWeb extends AmazonLandingScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonLandingScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(AmazonLandingScreenWeb.class.getName());
    private final By bySearchId = By.id("twotabsearchtextbox");
    private final By bySearchButtonId = By.id("nav-search-submit-text");

    public AmazonLandingScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "amazon Landing screen");
    }

    @Override
    public SearchResultsScreen search(String product) {
        LOGGER.info("user Search for product : "+product);
        driver.waitTillElementIsPresent(bySearchId).sendKeys(product);
        driver.findElement(bySearchButtonId).click();
        return SearchResultsScreen.get();
    }
}