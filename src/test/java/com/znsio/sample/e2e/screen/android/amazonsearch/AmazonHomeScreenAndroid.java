package com.znsio.sample.e2e.screen.android.amazonsearch;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonSearchResultsScreen;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonHomeScreenAndroid extends AmazonHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public AmazonHomeScreenAndroid(Driver driver, Visual visually) {
        super();
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home Page");
    }

    public static final By bySearchBoxXpath = By.xpath("//android.view.View[@content-desc='Clear search keywords']");
    public static final By bySearchButtonXpath = By.xpath("//android.widget.Button[@text='Go']");
    private static final By byFirstImageResultXpath = By.xpath("//android.widget.Image[contains(@text,'iPhone 13')]");
    private static final By bySearchTextBox = By.xpath("//android.widget.EditText[@resource-id='nav-search-keywords']");


    @Override
    public AmazonSearchResultsScreen searchForProductInSearchBar(String productTitle) {
        LOGGER.info(String.format("Search for string '%s'", productTitle));
        driver.waitTillElementIsPresent(bySearchBoxXpath, 60);
        WebElement searchBox = driver.findElement(bySearchBoxXpath);
        searchBox.click();
        searchBox.clear();
        WebElement searchTextBox = driver.findElement(bySearchTextBox);
        searchTextBox.sendKeys(productTitle);
        visually.checkWindow(SCREEN_NAME, String.format("Search for product '%s'", productTitle));
        driver.findElement(bySearchButtonXpath).click();
        return AmazonSearchResultsScreen.get();
    }

    @Override
    public boolean selectFirstItem() {
        LOGGER.info("Clicking the first result item");
        driver.findElement(byFirstImageResultXpath).click();
        return driver.isElementPresent(byFirstImageResultXpath);
    }
}
