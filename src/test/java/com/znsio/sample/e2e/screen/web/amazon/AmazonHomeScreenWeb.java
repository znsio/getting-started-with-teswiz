package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import com.znsio.sample.e2e.screen.web.ajio.AjioHomeScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonHomeScreenWeb extends AmazonHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySearchBoxId = By.id("twotabsearchtextbox");
    private static final By bySearchIconId = By.id("nav-search-submit-button");

    public AmazonHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public AmazonSearchResultsScreen searchFor(String product) {
        LOGGER.info(String.format("Search for '%s'", product));
        WebElement searchElement = driver.waitTillElementIsPresent(bySearchBoxId);
        searchElement.click();
        searchElement.clear();
        searchElement.sendKeys(product);
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        WebElement searchIcon = driver.waitTillElementIsPresent(bySearchIconId);
        searchIcon.click();
        return AmazonSearchResultsScreen.get();
    }
}
