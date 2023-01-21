package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonProductScreenWeb extends AmazonProductScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = AmazonSearchScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By byProductTitleById = By.id("productTitle");
   private final By addToCartButtonByXpath = By.xpath("//input[@id='add-to-cart-button']");
    private final By addToCartSuccessMessageByCSS = By.cssSelector("#attach-added-to-cart-message .sw-atc-message + span");

    public AmazonProductScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Product screen");
    }

    @Override
    public String getProductNameInProductScreen() {
        return  driver.waitTillElementIsPresent(byProductTitleById,2).getText();
    }

    @Override
    public AmazonProductScreen click_AddToCart_Button()  {
        driver.waitTillElementIsPresent(addToCartButtonByXpath).click();
        visually.checkWindow(SCREEN_NAME, "Add the product to cart");
        return this;
    }

    @Override
    public String getAddToCartSuccessMessage() {
        return driver.waitTillElementIsVisible(addToCartSuccessMessageByCSS).getText();
    }
}
