package com.znsio.sample.e2e.screen.web.amazonsearch;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.HomeScreen;
import com.znsio.sample.e2e.screen.amazonsearch.ProductScreen;
import com.znsio.sample.e2e.screen.amazonsearch.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomeScreenWeb extends HomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = HomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public HomeScreenWeb(Driver driver, Visual visually) {
        super();
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home Page");
    }

    public static final By bySearchBoxId = By.id("twotabsearchtextbox");
    public static final By bySearchButtonId = By.id("nav-search-submit-button");
    private static final By byFirstImageResultXpath = By.xpath("//img[@class='s-image']");
    @Override
    public SearchResultsScreen searchForProductInSearchBar(String productTitle) {
        LOGGER.info(String.format("Search for string '%s'", productTitle));
        driver.waitTillElementIsPresent(bySearchBoxId).click();
        WebElement searchBox = driver.findElement(bySearchBoxId);
        searchBox.clear();
        searchBox.sendKeys(productTitle);
        visually.checkWindow(SCREEN_NAME, String.format("Search for product '%s'", productTitle));
        driver.findElement(bySearchButtonId).click();
        return SearchResultsScreen.get();
    }

    @Override
    public HomeScreenWeb selectFirstItem() {
        LOGGER.info("Clicking the first result item");
        driver.findElement(byFirstImageResultXpath).click();
        return this;
    }

    @Override
    public HomeScreenWeb changeToNewTab() {
        LOGGER.info("Switching to next tab.");
        driver.switchToNextTab();
        visually.checkWindow(SCREEN_NAME, "Shifted to next tab");
        return this;
    }
}
