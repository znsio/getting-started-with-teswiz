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
    private static final String sizeXpath = "//span[text()='%s']";
    private static final By byWishlistIconXpath = By.xpath("//div[@class='popup-blk wishlist-blk-icon']//a");
    private static final By byAddToCartClassName = By.className("pdp-addtocart-button");
    private static final By byGoToBagXpath = By.xpath("//span[text()='GO TO BAG']");
    private static final By byRemoveFromWishlistClassName = By.className("pdp-wishlist-desktop-icon");


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
    public AjioProductDetailsScreen selectSize(String productSize) {
        LOGGER.info("selecting size for the product");
        driver.waitTillElementIsVisible(By.xpath(String.format(sizeXpath, productSize))).click();
        return this;
    }

    @Override
    public AjioProductDetailsScreen addToBag() {
        LOGGER.info("Adding product to Bag");
        driver.findElement(byAddToCartClassName).click();
        return this;
    }

    @Override
    public AjioCartScreen goToBag() {
        LOGGER.info("Go to bag");
        driver.waitTillElementIsVisible(byGoToBagXpath).click();
        return AjioCartScreen.get();
    }

    @Override
    public AjioProductDetailsScreen removeFromWishlist() {
        LOGGER.info("Removing product from Wishlist");
        driver.waitTillElementIsVisible(byRemoveFromWishlistClassName).click();
        return this;
    }
}