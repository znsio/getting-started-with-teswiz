package com.znsio.sample.e2e.screen.amazon.android;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonProductScreenAndroid extends AmazonProductScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String product= SAMPLE_TEST_CONTEXT.PRODUCT;

    private static final By byFirstProduct = By.xpath("//android.widget.Image[contains(@text,'iPhone 13')]");
    private static final By byProductTitle = By.xpath("//android.widget.TextView[contains(@text, '" + SAMPLE_TEST_CONTEXT.PRODUCT + "')]");
    public AmazonProductScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Product page");

    }


    @Override
    public AmazonProductScreen viewProduct() {
        LOGGER.info(String.format("Selected product= '%s'", product));
        WebElement selectProduct= driver.waitTillElementIsPresent(byFirstProduct);
        selectProduct.click();
        return this;
    }

    @Override
    public boolean isProductPresent() {
        LOGGER.info(String.format("Verifying the selected product '%s'", product));
        String titleOfProduct=driver.waitTillElementIsVisible(byProductTitle).getText().trim();
        if(titleOfProduct.contains(product)){
            visually.takeScreenshot(SCREEN_NAME, "Product Details");
            LOGGER.info("Selected Product: Text: " + titleOfProduct);
            return true;
        }
        else{
            visually.takeScreenshot(SCREEN_NAME, "Product Details are incorrect");
            LOGGER.info("Selected Product: Text: " + titleOfProduct);
            return false;
        }

    }
}
