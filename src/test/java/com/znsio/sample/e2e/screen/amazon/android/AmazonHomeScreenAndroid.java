package com.znsio.sample.e2e.screen.amazon.android;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.android.ajio.AjioHomeScreenAndroid;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonHomeScreenAndroid extends AmazonHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static final By bySearchBoxXpath= By.xpath("//android.view.View[@content-desc='Clear search keywords']");
    private static final By bySearchIconXpath= By.xpath("//android.widget.Button[@text='Go']");

    public AmazonHomeScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public AmazonHomeScreen searchProductFromSearchbar(String product) {
        LOGGER.info(String.format("Search for '%s'", product));
        WebElement searchElement = driver.waitTillElementIsPresent(bySearchBoxXpath);
        searchElement.click();
        searchElement.clear();
        searchElement.sendKeys(product);
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        WebElement searchIcon=driver.waitTillElementIsVisible(bySearchIconXpath);
        searchIcon.click();
        return this;
    }

}
