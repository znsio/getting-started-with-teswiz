package com.znsio.sample.e2e.screen.android.amazonsearch;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.CartScreen;
import org.openqa.selenium.By;

public class CartScreenAndroid extends CartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = CartScreenAndroid.class.getSimpleName();
    private static final By byAddedToCartXpath = By.xpath("//android.widget.TextView[@text='Added to cart']");
    private static final By byCartButton = By.xpath("//android.widget.Button[@text='Cart']");

    public CartScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home Page");
    }

    @Override
    public CartScreen clickOnCartButton() {
        driver.waitTillElementIsVisible(byAddedToCartXpath);
        driver.findElement(byCartButton).click();
        return this;
    }

    @Override
    public boolean isProductPresentInTheCart(String productName) {
        By byproductTitleXpath = By.xpath("//android.widget.TextView[contains(@text,'" + productName + "')]");
        driver.waitTillElementIsPresent(byproductTitleXpath);
        boolean isProductPresent = driver.isElementPresent(byproductTitleXpath);
        visually.checkWindow(SCREEN_NAME, "Product list in cart");
        return isProductPresent;
    }
}
