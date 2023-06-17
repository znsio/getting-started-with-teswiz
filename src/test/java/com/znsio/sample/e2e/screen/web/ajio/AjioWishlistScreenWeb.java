package com.znsio.sample.e2e.screen.web.ajio;

import com.applitools.eyes.selenium.fluent.Target;
import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
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
    private static final String selectSizeXpath = "//div[@class='slick-slide slick-active size-swatch']//div[contains(text(),'%s')] ";
    private static final By byProceedToBagClassName = By.className("mini-cart-btn");
    private static final By byMoveToBagIcon = By.className("wishlist-card-bag");


    private final Driver driver;
    private final Visual visually;

    public AjioWishlistScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }


    @Override
    public boolean isProductPresentInWishlist(String productName) {
        LOGGER.info("Checking if product is added to wishlist");
        String wishlistProductName = driver.findElement(byProductNameClassName).getText();
        visually.check(SCREEN_NAME, "Wishlist page for User", Target.window().fully().strict());
        return wishlistProductName.equalsIgnoreCase(productName);
    }

    @Override
    public AjioWishlistScreen selectSizeAndMoveToBag(String productSize) {
        LOGGER.info("Selecting Size and moving to bag from Wishlist");
        driver.waitTillElementIsVisible(byMoveToBagIcon).click();
        driver.waitTillElementIsVisible(By.xpath(String.format(selectSizeXpath, productSize))).click();
        return this;
    }

    @Override
    public AjioCartScreen proceedToBag() {
        LOGGER.info("Going to Bag from Wishlist");
        driver.waitTillElementIsVisible(byProceedToBagClassName).click();
        return AjioCartScreen.get();
    }
}