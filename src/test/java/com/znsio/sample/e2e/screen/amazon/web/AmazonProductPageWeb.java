package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.businessLayer.amazon.CartPageBL;
import com.znsio.sample.e2e.screen.amazon.AmazonProductPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonProductPageWeb extends AmazonProductPageScreen {
    private final Visual visually;
    private final Driver driver;
    public static final String SCREEN_NAME = AmazonGridWallPageWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By button_AddToCart = By.xpath("//input[@value='Add to Cart']");
    private final By button_BuyNow = By.xpath("//input[@id='buy-now-button']");
    private final By productDesc = By.id("productTitle");
    public AmazonProductPageWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
    public Boolean validateProductPageDisplay() {
        Boolean isProductPageIsDisplayed = false;
        driver.switchToNextTab();
        if (driver.isElementPresent(button_BuyNow)) {
            isProductPageIsDisplayed =true;
        }
        return isProductPageIsDisplayed;
    }

    @Override
    public Boolean verifyProductDescSyncWithGridWallPage(String expectedDescFromGridWallPage) {
        Boolean isProductDescMatchWithGridWallPage = false;
        String descInProductPage = driver.findElement(productDesc).getText();
        if (descInProductPage.equals(expectedDescFromGridWallPage)) {
            isProductDescMatchWithGridWallPage =true;
        }
        LOGGER.info(expectedDescFromGridWallPage);
        return isProductDescMatchWithGridWallPage;
    }

    @Override
    public Boolean verifyAddToCartButton() {
        Boolean isExpectedButtonPresent = false;
        if (driver.isElementPresent(button_AddToCart)) {
            isExpectedButtonPresent =true;
        }
        return isExpectedButtonPresent;
    }

    @Override
    public CartPageBL clickAddToCartButton() {
        driver.waitTillElementIsPresent(button_AddToCart,5).click();
        visually.takeScreenshot(SCREEN_NAME, "After Selecting the Add to cart button");
        return new CartPageBL();
    }
}
