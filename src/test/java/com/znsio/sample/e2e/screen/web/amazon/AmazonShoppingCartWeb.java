package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonShoppingCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.znsio.e2e.tools.Wait.waitFor;

public class AmazonShoppingCartWeb extends AmazonShoppingCartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By shoppingCartButton = By.id("nav-cart");

    private static final By shoppingCartMessage = By.xpath("//h1[contains(text(),'Shopping Cart')]");
    private static final By firstProductInCart = By.xpath("(//div[@data-name='Active Items']//span[contains(@class, 'sc-product-title')]//span//span)[2]");

    public AmazonShoppingCartWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AmazonShoppingCartScreen navigateToTheShoppingCart(){
        driver.waitTillElementIsPresent(shoppingCartButton).click();
        return this;
    }

    @Override
    public boolean getTheShoppingCartTitle(){
        WebElement shoppingCartMsg = driver.waitTillElementIsVisible(shoppingCartMessage);
        String expectedAddedToCartMsg = shoppingCartMsg.getText().trim();
        if(expectedAddedToCartMsg.equals("Shopping Cart")){
            visually.takeScreenshot(SCREEN_NAME, "User navigated to shopping cart");
            LOGGER.info("Added to cart : Text: " + expectedAddedToCartMsg);
            return true;
        }
        else{
            visually.takeScreenshot(SCREEN_NAME, "User did not navigate to the shopping cart");
            LOGGER.info("Added to Cart message is incorrect : Text: " + expectedAddedToCartMsg);
            return false;
        }
    }

    @Override
    public String getShoppingCartProduct(){
        WebElement element = driver.waitTillElementIsPresent(firstProductInCart);
        return element.getText();
    }
}
