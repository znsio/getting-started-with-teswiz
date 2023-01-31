package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchScreen;
import io.appium.java_client.android.AndroidElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonHomeScreenAndroid extends AmazonHomeScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = AmazonHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;


    private final By searchButtonByXapth = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View");

    private final By navSearchKeywordByXpath =By.xpath("//android.widget.EditText[@resource-id='nav-search-keywords']");


    public AmazonHomeScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home screen");
    }

    @Override

    public AmazonHomeScreen enterTheTextOnSearchBar(String productName) {
       AndroidElement searchBox = (AndroidElement) driver.waitTillElementIsVisible(navSearchKeywordByXpath,3);
        searchBox.sendKeys(productName);
        visually.checkWindow(SCREEN_NAME, "Product search on homePage");
        return  AmazonHomeScreen.get();
    }

    @Override
    public AmazonSearchScreen clickTheSearchButton() {
        driver.waitForClickabilityOf(searchButtonByXapth,3).click();
        return AmazonSearchScreen.get();
    }

}
