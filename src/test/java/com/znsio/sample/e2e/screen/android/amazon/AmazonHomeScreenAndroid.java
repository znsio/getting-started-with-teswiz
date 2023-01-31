package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonHomeScreenAndroid extends AmazonHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By searchBoxByClass = By.className("android.widget.EditText");
    private static final By searchIconByXpath = By.xpath("//android.view.View[@resource-id=\"nav-search-form\"]//android.widget.Button");

    public AmazonHomeScreenAndroid(Driver driver, Visual visually) {

        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public AmazonSearchResultsScreen searchProductUsingAmazonSearchBar(String product) {
        LOGGER.info(String.format("Search for '%s'", product));
        WebElement searchBox = driver.waitTillElementIsPresent(searchBoxByClass);
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(product);
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        WebElement searchIcon = driver.waitTillElementIsPresent(searchIconByXpath);
        searchIcon.click();
        visually.checkWindow(SCREEN_NAME, "Search icon clicked");
        return AmazonSearchResultsScreen.get();
    }
}
