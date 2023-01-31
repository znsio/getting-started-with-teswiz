package com.znsio.sample.e2e.screen.amazon.android;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonCartScreenAndroid extends AmazonCartScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String product= SAMPLE_TEST_CONTEXT.PRODUCT;

    private static final By byAddToCartButtonXpath = By.xpath("//android.widget.Button[@text='Add to Cart']");

    private static final By byCartButtonXpath = By.xpath("//android.view.View[@content-desc=\"Cart\"]/android.view.View");
    private static final By byCartProductXpath = By.xpath("//android.view.View[@content-desc=\"Apple iPhone 13 (128GB) - Blue\"]/android.widget.TextView");


    private static final By byCartReadyXpath= By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View/android.widget.Button");


    public AmazonCartScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Product page");
    }

    @Override
    public AmazonCartScreen addingFirstProductToCart() {
        LOGGER.info("Adding product to cart");
        driver.scrollToAnElementByText("Add to Cart");
        WebElement addToCartButton=driver.waitTillElementIsVisible(byAddToCartButtonXpath);
        addToCartButton.click();
        return this;
    }

    @Override
    public AmazonCartScreen viewCart() {
        WebElement cartButton=driver.waitTillElementIsVisible(byCartButtonXpath);
        cartButton.click();
        return this;
    }

    @Override
    public boolean isCartProductPresent() {
        LOGGER.info(String.format("Selected product= '%s'", product));
        WebElement cartProduct= driver.waitTillElementIsVisible(byCartProductXpath);

        String productName=cartProduct.getText().trim();
        if(productName.contains(product)){
            visually.takeScreenshot(SCREEN_NAME, "Product Details");
            LOGGER.info("Selected Product: Text: " + productName);
            return true;
        }
        else {
            visually.takeScreenshot(SCREEN_NAME, "Product Details");
            LOGGER.info("Selected Product: Text: " + productName);
            return false;
        }
    }

    @Override
    public boolean isCartReady() {
        WebElement cartProduct= driver.waitTillElementIsVisible(byCartProductXpath);

        String productName=cartProduct.getText().trim();
        if(productName.contains(product)){
            visually.takeScreenshot(SCREEN_NAME, "Product Details");
            LOGGER.info("Selected Product: Text: " + productName);
            return true;
        }
        else {
            visually.takeScreenshot(SCREEN_NAME, "Product Details");
            LOGGER.info("Selected Product: Text: " + productName);
            return false;
        }
    }
}
