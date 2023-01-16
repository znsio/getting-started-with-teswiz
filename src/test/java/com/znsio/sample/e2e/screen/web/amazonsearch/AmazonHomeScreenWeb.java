package com.znsio.sample.e2e.screen.web.amazonsearch;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonSearchResultsScreen;
import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonHomeScreenWeb extends AmazonHomeScreen{
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    public AmazonHomeScreenWeb(Driver driver, Visual visually) {
        super();
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home Page");
    }

    public static final By bySearchBoxXpath = By.xpath("//input[@id='twotabsearchtextbox']");
    public static final By bySearchButtonId = By.id("nav-search-submit-button");
    private static final By byFirstImageResultXpath = By.xpath("//img[@class='s-image']");
    @Override
    public AmazonSearchResultsScreen searchFor(String productTitle) {
        LOGGER.info(String.format("Search for string '%s'", productTitle));
        driver.waitTillElementIsPresent(bySearchBoxXpath).click();
        WebElement searchBox = driver.findElement(bySearchBoxXpath);
        searchBox.clear();
        searchBox.sendKeys(productTitle);
        visually.checkWindow(SCREEN_NAME, String.format("Search for product '%s'", productTitle));
        driver.findElement(bySearchButtonId).click();
        return AmazonSearchResultsScreen.get();
    }

    @Override
    public AmazonSearchResultsScreen selectFirstItem() {
        LOGGER.info("Clicking the first result item");
        driver.waitTillElementIsPresent(byFirstImageResultXpath);
        driver.findElement(byFirstImageResultXpath).click();
        return AmazonSearchResultsScreen.get();
    }
}
