package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.time.Duration;

public class AmazonProductScreenAndroid extends AmazonProductScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = AmazonSearchScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static Driver driver;
    private final Visual visually;

    private final By productNameOnProductScreenByXpath = By.xpath("//android.view.View[@resource-id='title_feature_div']");

    private static final By addToCartButtonByXpath = By.xpath("//android.widget.Button[@resource-id='add-to-cart-button']");
    private final By addToCartSuccessMessageByXpath = By.xpath("//android.widget.TextView[@text='Added to cart']");

    public AmazonProductScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Product screen");
    }


    @Override
    public String getProductNameInProductScreen() {
        return driver.waitTillElementIsPresent(productNameOnProductScreenByXpath, 2).getText();
    }

    @Override
    public AmazonProductScreen click_AddToCart_Button() {
        this.scrollDownTillElementPresent(addToCartButtonByXpath);
        driver.waitForClickabilityOf(addToCartButtonByXpath).click();
        visually.checkWindow(SCREEN_NAME, "Add the product to cart");
        return this;
    }

    private  void scrollDownTillElementPresent(By element) {

        AndroidDriver driver1 = (AndroidDriver) driver.getInnerDriver();

        while (!this.isDisplayed(element)) {

            Dimension dimension = driver1.manage().window().getSize();
            int startX = dimension.getWidth() / 2;
            int starty = (int) (dimension.getHeight() * 0.9);
            int endx = dimension.getWidth() / 2;
            int endy = (int) (dimension.getHeight() * 0.3);

            TouchAction action = new TouchAction(driver1);
            action.press(PointOption.point(startX, starty))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point(endx, endy))
                    .release()
                    .perform();
        }
    }

    private boolean isDisplayed(By element){
        try {
          driver.getInnerDriver().findElement((By) element).isDisplayed();
          return true;
        } catch (NoSuchElementException exception){
            return false;
        }

    }

    @Override
    public String getAddToCartSuccessMessage() {
        return driver.waitTillElementIsPresent(addToCartSuccessMessageByXpath,3).getText();
    }
}
