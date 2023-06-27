package com.znsio.sample.e2e.screen.web.ajioShopping;

import com.znsio.sample.e2e.screen.ajioShopping.CartPageScreen;
import com.znsio.sample.e2e.screen.ajioShopping.ProductLandingPageScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.znsio.teswiz.tools.Wait.waitFor;

public class ProductLandingPageScreenWeb extends ProductLandingPageScreen {
    private static final String SCREEN_NAME = ProductLandingPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;
    private static final By goToWishList = By.xpath("//span[@class='pdp-wishlist-desktop-icon']");
    private static final By addToBag = By.xpath("//img[@class='wishlist-card-bag']");
    private static final By goToBag = By.xpath("//div[@class='ic-cart ']");

    private static final By removeWishList = By.xpath("//span[text()='REMOVE FROM WISHLIST']");
    private static final By addWishList = By.xpath("//span[text()='SAVE TO WISHLIST']");


    public ProductLandingPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public CartPageScreen addProductToBag() {
        if(driver.isElementPresent(removeWishList)){
            driver.waitForClickabilityOf(removeWishList).click();
            driver.waitForClickabilityOf(addWishList).click();
        }else {
            driver.waitForClickabilityOf(addWishList).click();
        }
        LOGGER.info(String.format("Product added to wishlist "));
        visually.takeScreenshot(SCREEN_NAME, "Added to Wish List");
        driver.waitForClickabilityOf(By.cssSelector(".popup-blk img")).click();
        visually.takeScreenshot(SCREEN_NAME, "Wish List Page");
        driver.waitForClickabilityOf(addToBag).click();
        driver.waitForClickabilityOf(By.cssSelector(".size-swatch:nth-child(4) > .circle")).click();
        waitFor(5);
        driver.waitForClickabilityOf(goToBag).click();
        visually.takeScreenshot(SCREEN_NAME, "Cart Page");
        return CartPageScreen.get();
    }
}
