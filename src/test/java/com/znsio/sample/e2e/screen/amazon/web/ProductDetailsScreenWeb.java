package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.ShoppingCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductDetailsScreenWeb extends ProductDetailsScreen {
    private static final String SCREEN_NAME = ProductDetailsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(ProductDetailsScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;

    private final By productTitle = By.cssSelector("span#productTitle");
    
    private final By addToCart = By.cssSelector("input#add-to-cart-button");

    private final By viewCart = By.cssSelector("#attach-view-cart-button-form input");
    
    public ProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
    
    public String getProductName() {
        return driver.findElement(productTitle).getText();
    }

    public ProductDetailsScreen clickAddToCardButton() {
        visually.takeScreenshot(SCREEN_NAME, " Before clicking on add to cart");
        driver.waitForClickabilityOf(addToCart);
        driver.findElement(addToCart).click();
        return this;
    }

    public ShoppingCartScreen clickViewCartButton() {
        visually.takeScreenshot(SCREEN_NAME, " Before moving to cart page");
        driver.waitForClickabilityOf(viewCart);
        driver.findElement(viewCart).click();
        return ShoppingCartScreen.get();
    }

}
