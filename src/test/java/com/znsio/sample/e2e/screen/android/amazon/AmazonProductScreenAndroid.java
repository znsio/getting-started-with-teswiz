package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonProductScreenAndroid extends AmazonProductScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonProductScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By resultXpath= By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]");
    private static final By resultListXpath=By.xpath("//android.view.View[contains(@text,\"Apple iPhone 13\")]");
    private static final By addToCartXpath=By.xpath("//android.widget.Button[@resource-id=\"add-to-cart-button\"]");
    private static final By productXpath=By.xpath("//android.view.View[@resource-id=\"title\"]");
    public AmazonProductScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Product detailed page");
    }

    @Override
    public AmazonCartScreen addToCart()
    {
        visually.checkWindow(SCREEN_NAME, "Product page");
        LOGGER.info("Adding in cart");
        driver.waitTillElementIsPresent(productXpath);
        driver.scrollDownByScreenSize();
        driver.scrollDownByScreenSize();
        driver.waitTillElementIsPresent(addToCartXpath).click();
        return AmazonCartScreen.get();

    }

    @Override
    public boolean isProductAvailableAfterSearching()
    {
        visually.checkWindow(SCREEN_NAME, "Viewing results list");
        driver.waitTillElementIsVisible(resultXpath);
        LOGGER.info("Searched results");
        List<WebElement> resultList=driver.findElements(resultListXpath);
        if(resultList.size()!=0)
        {
            driver.findElement(resultListXpath).click();
            LOGGER.info("Clicked on first search related product");
            return true;
        }
        return  false;
    }
}
