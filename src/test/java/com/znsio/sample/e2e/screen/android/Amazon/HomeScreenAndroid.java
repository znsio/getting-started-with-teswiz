package com.znsio.sample.e2e.screen.android.Amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.HomeScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomeScreenAndroid extends HomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = HomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySkipSignInButtonId = By.id("skip_sign_in_button");
    private static final By bySearchBoxId = By.id("chrome_search_hint_view");
    private static final By bySearchTextId = By.id("rs_search_src_text");

    public HomeScreenAndroid(Driver driver, Visual visually) {

        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home screen Android");
    }

    @Override
    public HomeScreen enterItemNameInSearch(String product) {
        LOGGER.info(String.format("Search for '%s'", product));
        driver.waitTillElementIsPresent(bySkipSignInButtonId).click();
        driver.waitForClickabilityOf(bySearchBoxId, 30).click();
        WebElement searchText = driver.waitTillElementIsPresent(bySearchTextId);
        searchText.clear();
        searchText.sendKeys(product);
        return this;
    }

    @Override
    public SearchResultsScreen pressEnter() {
        ((AndroidDriver) driver.getInnerDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));
        return SearchResultsScreen.get();
    }
}
