package com.znsio.sample.e2e.screen.android.amazonsearch;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonCartScreen;
import com.znsio.sample.e2e.screen.web.amazonsearch.AmazonCartScreenWeb;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonCartScreenAndroid extends AmazonCartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonCartScreenAndroid.class.getSimpleName();
    private static final By byAddedToCartXpath = By.xpath("//android.widget.TextView[@text='Added to cart']");
    private static final By byCartButton = By.xpath("//android.widget.Button[@text='Cart']");

    public AmazonCartScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home Page");
    }

    @Override
    public AmazonCartScreen clickOnCartButton() {
        driver.waitTillElementIsVisible(byAddedToCartXpath);
        assertThat(driver.isElementPresent(byAddedToCartXpath)).as("Product not added to the cart").isTrue();
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
