package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.HomeScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import io.appium.java_client.MobileBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class HomeScreenAndroid extends HomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = com.znsio.sample.e2e.screen.web.amazon.HomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySearchBoxClass = By.className("android.widget.EditText");
    private By bySearchIconXpath = By.xpath("//android.view.View[@resource-id=\"nav-search-form\"]//android.widget.Button");
    private By bySearchSuggestionXpath = By.xpath("//android.view.View[@resource-id='suggestions2']//android.view.View");

    public HomeScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home screen");
    }

    private void SetImplicitWaitInMilliSeconds(int timeOut) {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
    }

    private Boolean isVisible(By elementXpath) {
        SetImplicitWaitInMilliSeconds(2500);
        try {
            if (driver.findElement((elementXpath)).isDisplayed() == true) {
                return true;
            }
        } catch (Exception E) {
            SetImplicitWaitInMilliSeconds(1500);
            return false;
        }
        return false;

    }

    @Override
    public SearchResultsScreen search(String product) {
        LOGGER.info(String.format("Search for '%s'", product));

        WebElement searchElement = driver.waitTillElementIsPresent(bySearchBoxClass);
        searchElement.click();
        searchElement.clear();

        if(isVisible(bySearchSuggestionXpath)){
            driver.waitForClickabilityOf(bySearchSuggestionXpath).click();
        }else{
            searchElement.sendKeys(product);
            WebElement searchIcon = driver.waitTillElementIsPresent(bySearchIconXpath);
            searchIcon.click();
        }

        visually.checkWindow(SCREEN_NAME, "Search string entered");
        return SearchResultsScreen.get();
    }
}
