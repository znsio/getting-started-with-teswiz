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
    private static final By firstProductInCart = By.xpath("(//div[@data-name='Active Items']//span[contains(@class, 'sc-product-title')]//span//span)[2]");

    public AmazonShoppingCartWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AmazonShoppingCartScreen openShoppingCart(){
        driver.waitTillElementIsPresent(shoppingCartButton).click();
        return AmazonShoppingCartScreen.get();
    }

    @Override
    public String getProductName(){
        waitFor(2);
        WebElement element = driver.waitTillElementIsPresent(firstProductInCart);
        return element.getText();
    }
}
