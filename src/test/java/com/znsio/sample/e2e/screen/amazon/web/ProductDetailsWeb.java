package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import com.znsio.sample.e2e.screen.theapp.AppLaunchScreen;
import com.znsio.sample.e2e.screen.web.theapp.AppLaunchScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductDetailsWeb extends ProductDetailsScreen {
    private static final String SCREEN_NAME = AppLaunchScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(AppLaunchScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;

    private final By productTitle = By.cssSelector("span#productTitle");


    private final By addToCart = By.cssSelector("input#add-to-cart-button");

    private final By viewCart = By.cssSelector("#attach-view-cart-button-form input");


    public ProductDetailsWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    /**
     * Returns product title .
     */
    public String getProductName() {
        return driver.findElement(productTitle).getText();
    }


    /**
     * Clicks add to cart button .
     */
    public ProductDetailsWeb clickAddToCardButton() {
        visually.takeScreenshot(SCREEN_NAME, " Before clicking on add to cart");
        driver.waitForClickabilityOf(addToCart);
        driver.findElement(addToCart).click();
        return this;
    }

    /**
     * Clicks view cart button .
     */
    public ShoppingCartWeb clickViewCartButton() {
        visually.takeScreenshot(SCREEN_NAME, " Before moving to cart page");
        driver.waitForClickabilityOf(viewCart);
        driver.findElement(viewCart).click();
        return new ShoppingCartWeb(driver, visually);
    }

}
