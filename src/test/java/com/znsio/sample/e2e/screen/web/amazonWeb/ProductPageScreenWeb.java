package com.znsio.sample.e2e.screen.web.amazonWeb;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import com.znsio.sample.e2e.screen.amazon.ProductPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductPageScreenWeb extends ProductPageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private final By byProductName = By.xpath("(//span[@id='productTitle'])");
    private final By byAddToCart = By.id("add-to-cart-button");
    private final By verifyCart = By.xpath("(//span[contains(text(),'Added to Cart')])[3]");
    private final By clickOnCart = By.cssSelector("input[aria-labelledby='attach-sidesheet-view-cart-button-announce']");

    public ProductPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "iphone screen");
    }

    public String verifyProductName(){
        String productName = driver.findElement(byProductName).getText();
        return productName;
    }

//    public String verifyTitle(){
//       = driver.ge
//        return this;
//    }

    public ProductPageScreen clickAddToCart(){
        driver.waitForClickabilityOf(byAddToCart).click();
//        driver.waitForClickabilityOf(cartPopupCloseButton).click();
        return this;
    }

    public String checkSuccessMsgForAddToCart(){
        String addedToCartText = driver.waitTillElementIsPresent(verifyCart).getText();
        return addedToCartText;
    }

    public AmazonCartScreen moveToCart(){
        driver.findElement(clickOnCart).click();
        return AmazonCartScreen.get();
    }
}
