package com.znsio.sample.e2e.screen.web.ajio;

import com.applitools.eyes.selenium.fluent.Target;
import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import com.znsio.sample.e2e.screen.ajio.AjioWishlistScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AjioWishlistScreenWeb
        extends AjioWishlistScreen {
    private static final String SCREEN_NAME = AjioWishlistScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byProductNameClassName = By.className("nameCls");
    private static final String selectSizeXpath = "//div[@class='size-variant-section']//div[contains(text(),'%s')]";
    private static final By byProceedToBagClassName = By.className("mini-cart-btn");
    private static final By byMoveToBagIcon = By.className("wishlist-card-bag");
    private static final By byDeleteWishlistItemClassName = By.xpath("//div[@class='ic-delete rilrtl-remove-product']");
    private static final By byEmptyBagMessageClassName = By.className("empty-msg");


    private final Driver driver;
    private final Visual visually;

    public AjioWishlistScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }


    @Override
    public boolean isProductPresentInWishlist(String productName) {
        LOGGER.info("Checking if product is added to wishlist");
        String wishlistProductName = driver.findElement(byProductNameClassName).getText();
        visually.check(SCREEN_NAME, "Wishlist page for User", Target.window().fully().strict());
        return wishlistProductName.equalsIgnoreCase(productName);
    }

    @Override
    public boolean isproductSizeInStock(String productSize) {
        LOGGER.info("Checking if product is in stock for size : " + productSize);
        driver.waitTillElementIsVisible(byMoveToBagIcon).click();
        return driver.waitTillElementIsVisible(
                        By.xpath(String.format(selectSizeXpath, productSize)))
                .getAttribute("class")
                .contains("instock");
    }

    @Override
    public AjioWishlistScreen selectSizeAndMoveToBag(String productSize) {
        LOGGER.info("Selecting Size and moving to bag from Wishlist");
        driver.waitTillElementIsVisible(By.xpath(String.format(selectSizeXpath, productSize))).click();
        return this;
    }

    @Override
    public AjioCartScreen proceedToBag() {
        LOGGER.info("Going to Bag from Wishlist");
        driver.waitTillElementIsVisible(byProceedToBagClassName).click();
        return AjioCartScreen.get();
    }

    @Override
    public AjioWishlistScreen clearWishlist() {
        LOGGER.info("Clearing wishlist of the logged in user");
        for (WebElement clearProduct : driver.waitTillVisibilityOfAllElements(byDeleteWishlistItemClassName)) {
            new WebDriverWait(driver.getInnerDriver(), 10)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.stalenessOf(clearProduct));
            clearProduct.click();
        }
        return this;
    }

    @Override
    public boolean isWishlistEmpty() {
        LOGGER.info("Checking if wishlist is cleared");
        try {
            driver.waitTillElementIsVisible(byEmptyBagMessageClassName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}