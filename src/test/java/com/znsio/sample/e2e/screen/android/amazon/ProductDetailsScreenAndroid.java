package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.CartScreen;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ProductDetailsScreenAndroid extends ProductDetailsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductDetailsScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byProductFoundNameXpath = By.xpath("//android.view.View[(@resource-id='title_feature_div')]//android.view.View");

    private static final By byProductFoundCostXpath = By.xpath("//android.view.View[(@resource-id='corePriceDisplay_mobile_feature_div')]//android.view.View");

    private static final By byaddToCartBtnXpath = By.xpath("//android.widget.Button[(@resource-id='add-to-cart-button')]");

    private static final By byGoToCartBtnXpath = By.xpath("//android.view.View[@content-desc=\"Cart\"]");

    private static final By byAddToCartTextXpath = By.xpath("//android.widget.TextView[@text='Added to cart']");

    public ProductDetailsScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Product Details screen");
    }

    @Override
    public String getActualProductName() {

        String itemName = driver.waitTillElementIsPresent(byProductFoundNameXpath)
                .getText();

        LOGGER.info(String.format("Actual item name: '%s'", itemName));
        return itemName;
    }

    @Override
    public String getActualProductCost() {

        swipeDownTillElementFound(byProductFoundCostXpath);
        String itemCost = driver.waitTillElementIsPresent(byProductFoundCostXpath)
                .getText().split("%")[1];

        LOGGER.info(String.format("Actual item cost: '%s'", itemCost));
        return itemCost;
    }

    private void swipeDownTillElementFound(By element) {
        AndroidDriver driver1 = (AndroidDriver) driver.getInnerDriver();
        while ((!this.isVisible(element))) {

            Dimension size = driver1.manage().window().getSize();
            int width = size.getWidth();
            int height = size.getHeight();
            int startx = width / 2;
            int starty = (int) (height * 0.5);
            int endx = width / 2;
            int endy = (int) (height * 0.3);

            TouchAction touchAction = new TouchAction(driver1);
            touchAction.press(PointOption.point(startx, starty))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(endx, endy))
                    .release()
                    .perform();
        }
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
    public ProductDetailsScreen addToCart() {

        swipeDownTillElementFound(byaddToCartBtnXpath);
        driver.waitTillElementIsPresent(byaddToCartBtnXpath).click();
        visually.checkWindow(SCREEN_NAME, "Item added to cart");
        return this;
    }

    @Override
    public boolean isItemAddedToCartTextVisible() {
        if (isVisible(byAddToCartTextXpath))
            return driver.waitTillElementIsVisible(byAddToCartTextXpath).isDisplayed();

        return false;
    }

    @Override
    public CartScreen navigateToCartPage() {
        if (isVisible(byGoToCartBtnXpath))
            driver.waitTillElementIsVisible(byGoToCartBtnXpath).click();

        visually.checkWindow(SCREEN_NAME, "Go to cart page");
        return CartScreen.get();
    }
}
