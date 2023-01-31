package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonSearchScreenAndroid extends AmazonSearchScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonSearchScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static final By bySearchBoxXpath = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View");
    private static final By bySearchFieldXpath=By.xpath("//android.widget.EditText[@resource-id=\"nav-search-keywords\"]");
    public AmazonSearchScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }
    @Override
    public  AmazonProductScreen enteringAndSearchingProduct(String productName){
        LOGGER.info(String.format("Search for: '%s'", productName));
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        WebElement searchBox=driver.waitTillElementIsPresent(bySearchFieldXpath);
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(productName);
        driver.waitTillElementIsVisible(bySearchBoxXpath).click();
       return AmazonProductScreen.get();
    }

}
