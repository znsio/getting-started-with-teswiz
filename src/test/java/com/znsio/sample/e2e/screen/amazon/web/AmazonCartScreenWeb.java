package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonCartScreenWeb extends AmazonCartScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String product=SAMPLE_TEST_CONTEXT.PRODUCT;

    private static final By byAddToCartButtonXpath=By.xpath("//input[@id='add-to-cart-button']");
    private static final By byCartButtonXpath=By.xpath("//span[@id='attach-sidesheet-view-cart-button']//input");

    private static final By byCartProductXpath=By.xpath("//span[@class='a-truncate-cut'][contains(text(),'iPhone 13')]");
    private static final By byAddedToCartMsgXpath=By.xpath("//span[contains(@class,'a-size-medium-plus a-color-base')]");
    public AmazonCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
    @Override
    public AmazonCartScreen addingFirstProductToCart() {
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
    public boolean isCartProductCorrect() {
        LOGGER.info(String.format("Selected product= '%s'", product));
        WebElement cartProduct= driver.waitTillElementIsVisible(byCartProductXpath);

        String productName=cartProduct.getText().trim();
        if(productName.contains(product)){
            visually.takeScreenshot(SCREEN_NAME, "Product Details");
            LOGGER.info("Selected Product: Text: " + productName);
            return true;
        }
        else {
            return false;
        }

    }
    @Override
    public boolean isCartReady() {
        WebElement addedToCartMsg=driver.waitTillElementIsVisible(byAddedToCartMsgXpath);
        String expectedAddedToCartMsg=addedToCartMsg.getText().trim();
        if(expectedAddedToCartMsg.equals("Added to Cart")){
            visually.takeScreenshot(SCREEN_NAME, "Product added to cart message");
            LOGGER.info("Added to cart : Text: " + expectedAddedToCartMsg);
            return true;
        }
        else{
            visually.takeScreenshot(SCREEN_NAME, "incorrect added to cart message");
            LOGGER.info("Added to Cart message is incorrect : Text: " + expectedAddedToCartMsg);
            return false;
        }

    }

}
