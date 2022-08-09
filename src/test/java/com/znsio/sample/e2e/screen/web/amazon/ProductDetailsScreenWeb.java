package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductDetailsScreenWeb extends ProductDetailsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductDetailsScreenWeb.class.getSimpleName();

    private static final Logger LOGGER = Logger.getLogger(ProductDetailsScreenWeb.class.getName());
    private final By byProductTitleTextId = By.id("productTitle");
    private final By byAddToCartButtonId = By.id("add-to-cart-button");



    public ProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Product details screen");
    }

    @Override
    public String getProductTitleFromProductDetailsScreen() {
        return driver.waitTillElementIsPresent(byProductTitleTextId).getText();
    }

    @Override
    public ProductDetailsScreen userAddsProductToCart() {
        driver.waitForClickabilityOf(byAddToCartButtonId).click();
        return ProductDetailsScreen.get();
    }
}