package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductDetailsScreenWeb extends ProductDetailsScreen {
    private static final String SCREEN_NAME = ProductDetailsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(ProductDetailsScreenWeb.class.getName());
    private static final By byShareProductDetailsButtonXpath = By.xpath("//i[@title='Share']");
    private final Driver driver;
    private final Visual visually;
    private final By byAddToCartButtonId = By.id("add-to-cart-button");
    private final By byProductTitleId = By.id("productTitle");

    public ProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String getProductTitleFromProductDetailsScreen() {
        driver.waitTillElementIsPresent(byProductTitleId);
        LOGGER.debug("Getting product tilte from Product Details Screen");
        return driver.findElement(byProductTitleId).getText();
    }

    @Override
    public ProductDetailsScreen userAddsProductToCart() {
        driver.waitForClickabilityOf(byAddToCartButtonId).click();
        LOGGER.debug("Product added to Cart");
        return this;
    }

    @Override
    public boolean isUserOnProductPage() {
        LOGGER.debug("On Products Detail Screen");
        visually.checkWindow(SCREEN_NAME, "On ProductsDetail Screen");
        driver.waitTillElementIsPresent(byShareProductDetailsButtonXpath);
        return driver.isElementPresent(byShareProductDetailsButtonXpath);
    }

    @Override
    public boolean isAddToCartButtonIPresent() {
        return driver.isElementPresent(byAddToCartButtonId);
    }
}