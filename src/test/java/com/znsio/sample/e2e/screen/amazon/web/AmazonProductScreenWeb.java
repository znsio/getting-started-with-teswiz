package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonProductScreenWeb extends AmazonProductScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String product= SAMPLE_TEST_CONTEXT.PRODUCT;
    private static final By byFirstProduct = By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']//span[text()='Apple iPhone 13 (128GB) - Starlight']");
    private static final By byProductTitle = By.xpath("//span[@id='productTitle']");
    public AmazonProductScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
    @Override
    public AmazonProductScreen viewProduct() {
        LOGGER.info(String.format("Selected product= '%s'", product));
        WebElement selectProduct= driver.waitTillElementIsPresent(byFirstProduct);
        selectProduct.click();
        return this;
    }
    @Override
    public boolean isCorrectProduct() {
        LOGGER.info(String.format("Verifying the selected product '%s'", product));
        driver.switchToNextTab();
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
