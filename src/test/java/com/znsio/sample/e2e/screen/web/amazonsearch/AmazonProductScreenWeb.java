package com.znsio.sample.e2e.screen.web.amazonsearch;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.businessLayer.amazonsearch.AmazonProductBL;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonProductScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonProductScreenWeb extends AmazonProductScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonProductScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    private static final By byProductTitle = By.id("productTitle");
    private static final By byCustomerReviewsText = By.id("acrCustomerReviewText");
    private static final By byAddToCartButton = By.id("add-to-cart-button");

    public AmazonProductScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home Page");
    }

    @Override
    public AmazonProductScreen verifyProductDetails() {
        LOGGER.info("Verifying product details like Product Title, Price, Product Images, Product Description.");
        driver.waitTillElementIsPresent(byProductTitle);
        assertThat(driver.isElementPresent(byProductTitle)).as("Product Title not present").isTrue();
        assertThat(driver.isElementPresent(byCustomerReviewsText)).as("Customer Reviews not present").isTrue();
        assertThat(driver.isElementPresent(byAddToCartButton)).as("Add to cart Button not present").isTrue();
        return AmazonProductScreen.get();
    }

    @Override
    public AmazonProductScreen addToCart() {
        LOGGER.info("in method to click on 'Add to cart' button");
//        wait(10000);
        driver.findElement(byAddToCartButton).click();
        return AmazonProductScreen.get();
    }

    @Override
    public AmazonProductScreen changeToNewTab() {
        LOGGER.info("Switching to next tab.");
        driver.switchToNextTab();
        return AmazonProductScreen.get();
    }
}
