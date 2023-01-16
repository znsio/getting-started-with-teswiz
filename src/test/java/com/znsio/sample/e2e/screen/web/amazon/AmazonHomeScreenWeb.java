package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonHomeScreenWeb extends AmazonHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By searchBoxById = By.id("twotabsearchtextbox");
    private static final By searchIconById = By.id("nav-search-submit-button");

    public AmazonHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public AmazonSearchResultsScreen searchProductUsingAmazonSearchBar(String product) {
        LOGGER.info(String.format("Search for '%s'", product));
        WebElement searchBox = driver.waitTillElementIsPresent(searchBoxById);
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(product);
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        WebElement searchIcon = driver.waitTillElementIsPresent(searchIconById);
        searchIcon.click();
        return AmazonSearchResultsScreen.get();
    }
}
