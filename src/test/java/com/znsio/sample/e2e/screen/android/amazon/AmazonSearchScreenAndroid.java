package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class AmazonSearchScreenAndroid extends AmazonSearchScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = AmazonSearchScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By firstProductNameByXpath = By.xpath("//android.view.View[contains(@text,'Apple iPhone 13 (128GB)')]");
    private final By firstSearchProductXpath = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[4]");

    public AmazonSearchScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Search Screen");
    }

    @Override
    public String getTextOfSearchResult() {
    return driver.waitTillElementIsPresent(firstProductNameByXpath,3).getText();
    }

    @Override
    public AmazonProductScreen clickFirstProductOnSearchResult() {
        driver.waitForClickabilityOf(firstSearchProductXpath, 5).click();
        visually.checkWindow(SCREEN_NAME, "Product on search");
        return AmazonProductScreen.get();
    }


}
