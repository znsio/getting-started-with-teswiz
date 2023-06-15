package com.znsio.sample.e2e.screen.web.ajio;


import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import com.znsio.sample.e2e.screen.ajio.AjioProductDetailsScreen;
import com.znsio.sample.e2e.screen.ajio.AjioWishlistScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AjioProductDetailsScreenWeb
        extends AjioProductDetailsScreen {
    private static final String SCREEN_NAME = AjioProductDetailsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byProductContentClassName = By.className("prod-container");
    private static final By byWishlistProductClassName = By.className("pdp-wishlist-desktop-icon");
    private static final By byProductNameClassName = By.className("prod-name");
    private static final By byWishlistIconXpath = By.xpath("//div[@class='popup-blk wishlist-blk-icon']//a");


    private final Driver driver;
    private final Visual visually;

    public AjioProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public AjioProductDetailsScreen wishlistTheProduct() {
        LOGGER.info("Adding the product to Wishlist");
        driver.findElement(byWishlistProductClassName).click();
        return this;
    }

    @Override
    public boolean isProductOpened() {
        LOGGER.info("Verifying Product Details Page");
        driver.switchToNextTab();
        return driver.isElementPresent(byProductContentClassName);
    }

    @Override
    public String getProductName() {
        LOGGER.info("Getting product name");
        return driver.findElement(byProductNameClassName).getText();
    }

    @Override
    public AjioWishlistScreen goToWishList() {
        LOGGER.info("Going to wishlist of user");
        driver.waitForClickabilityOf(byWishlistIconXpath).click();
        return AjioWishlistScreen.get();
    }

    @Override
    public AjioProductDetailsScreen selectSize() {
        return this;
    }

    @Override
    public AjioProductDetailsScreen addToBag() {
        return this;
    }

    @Override
    public AjioCartScreen goToBag() {
        return AjioCartScreen.get();
    }
}