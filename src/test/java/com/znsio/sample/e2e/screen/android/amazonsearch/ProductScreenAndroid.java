package com.znsio.sample.e2e.screen.android.amazonsearch;

import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.ProductScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductScreenAndroid extends ProductScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byProductTitle = By.xpath("//android.widget.TextView[contains(@text, '" + SAMPLE_TEST_CONTEXT.PRODUCT_NAME + "')]");
    private static final By byAddToCartButton = By.xpath("//android.widget.Button[@text='Add to Cart']");

    public ProductScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home Page");
    }

    @Override
    public boolean isProductDetailsDisplayed() {
        LOGGER.info("Verifying product details like Product Title");
        driver.waitTillElementIsPresent(byProductTitle);
        boolean isProductTitle = driver.isElementPresent(byProductTitle);
        visually.checkWindow(SCREEN_NAME, "Product information screen");
        return isProductTitle;
    }

    @Override
    public boolean clickOnAddToCart() {
        LOGGER.info("in method to click on 'clickAddToCart' button");
        while (!driver.isElementPresent(byAddToCartButton)) {
           driver.scrollDownByScreenSize();
        }
        try {
            driver.findElement(byAddToCartButton).click();
            visually.checkWindow(SCREEN_NAME, "Cart window");
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
