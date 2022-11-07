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
    private final By byShareButton = By.xpath("//i[@title='Share']");

    public ProductPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "iphone screen");
    }

    @Override
    public String verifyProductName(){
        LOGGER.info("Validating the product name");
        String productName = driver.findElement(byProductName).getText();
        return productName;
    }
//    @Override
//    public boolean verifyIfShareButtonIsPresent(){
//        LOGGER.info("Validating the presence of share button");
//        driver.waitTillElementIsPresent(byShareButton);
//        return driver.isElementPresent(byShareButton);
//    }

    @Override
    public ProductPageScreen clickAddToCart(){
        LOGGER.info("adding items to the cart");
        driver.waitForClickabilityOf(byAddToCart).click();;
        return this;
    }
    @Override
    public String checkSuccessMsgForAddToCart(){
        LOGGER.info("Verifying if the item is added to cart");
        String addedToCartText = driver.waitTillElementIsPresent(verifyCart).getText();
        return addedToCartText;
    }
    @Override
    public AmazonCartScreen moveToCart(){
        LOGGER.info("Click on cart button");
        driver.findElement(clickOnCart).click();
        return AmazonCartScreen.get();
    }
}
