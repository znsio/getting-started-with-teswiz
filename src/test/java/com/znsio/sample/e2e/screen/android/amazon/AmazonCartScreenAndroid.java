package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonCartScreenAndroid extends AmazonCartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonCartScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byCartContentXpath =By.xpath("//android.view.View[contains(@content-desc,\"Apple iPhone 13\")]");
    private static final By byCartButtonXpath =By.xpath("//android.view.View[@content-desc=\"Cart\"]");
    private static final By byAddedToCartXpath =By.xpath("//android.widget.TextView[@text=\"Added to cart\"]");
    public AmazonCartScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Cart page");
    }

    @Override
    public boolean isAddedToCart()
    {
        visually.checkWindow(SCREEN_NAME, "Cart option page");
        LOGGER.info("Added to cart text confirmation");
        try {
            WebElement addedToCartText = driver.waitTillElementIsPresent(byAddedToCartXpath);
            if (addedToCartText.isDisplayed()) {
                driver.waitTillElementIsPresent(byCartButtonXpath).click();
                return true;
            }
        }
        catch (Exception e)
        {
            LOGGER.info("Error in navigation from cart");
        }
        return false;
    }

    @Override
    public boolean isProductInCart()
    {
        visually.checkWindow(SCREEN_NAME, "Cart items");
        LOGGER.info("Verification of product in cart");
        try {
            WebElement cartElement = driver.waitTillElementIsPresent(byCartContentXpath);
            LOGGER.info(String.format("cartContent: '%s'",cartElement.getText()));
            return true;
        }
        catch (Exception e)
        {
            LOGGER.info("Product not in cart");
        }
        return false;
    }
}
