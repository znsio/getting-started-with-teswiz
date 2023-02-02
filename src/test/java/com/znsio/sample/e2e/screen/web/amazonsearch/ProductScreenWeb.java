package com.znsio.sample.e2e.screen.web.amazonsearch;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.HomeScreen;
import com.znsio.sample.e2e.screen.amazonsearch.ProductScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductScreenWeb extends ProductScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byProductTitle = By.id("productTitle");
    private static final By byCustomerReviewsText = By.id("acrCustomerReviewText");
    private static final By byAddToCartButton = By.id("add-to-cart-button");

    public ProductScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Product Description Page");
    }

    @Override
    public boolean isProductDetailsDisplayed() {
        LOGGER.info("Verifying product details like Product Title, Customer reviews, Add to cart button.");
        driver.waitTillElementIsPresent(byProductTitle);
        boolean isProductTitle = driver.isElementPresent(byProductTitle);
        boolean isCustomerReviewsText = driver.isElementPresent(byCustomerReviewsText);
        boolean isAddToCart = driver.isElementPresent(byAddToCartButton);
        visually.checkWindow(SCREEN_NAME, "Product information screen");
        return isProductTitle && isCustomerReviewsText && isAddToCart;
    }

    @Override
    public boolean clickOnAddToCart() {
        LOGGER.info("in method to click on 'clickAddToCart' button");
        driver.waitTillElementIsPresent(byAddToCartButton);
        try {
            driver.findElement(byAddToCartButton).click();
            visually.checkWindow(SCREEN_NAME, "Cart window");
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
