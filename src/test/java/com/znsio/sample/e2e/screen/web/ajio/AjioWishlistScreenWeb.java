package com.znsio.sample.e2e.screen.web.ajio;


import com.znsio.sample.e2e.screen.ajio.AjioProductDetailsScreen;
import com.znsio.sample.e2e.screen.ajio.AjioWishlistScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AjioWishlistScreenWeb
        extends AjioWishlistScreen {
    private static final String SCREEN_NAME = AjioWishlistScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byProductNameClassName = By.className("nameCls");


    private final Driver driver;
    private final Visual visually;

    public AjioWishlistScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }


    @Override
    public boolean isProductWishlisted(String productName) {
        LOGGER.info("Checking if product is added to wishlist");
        String wishlishtProductName = driver.findElement(byProductNameClassName).getText();
        LOGGER.info("Product added to wishlist" + wishlishtProductName);
        return productName.equalsIgnoreCase(wishlishtProductName);
    }

    @Override
    public AjioProductDetailsScreen goToProductDetails() {
        LOGGER.info("Going to Product Details from Wishlist");

        return AjioProductDetailsScreen.get();
    }
}