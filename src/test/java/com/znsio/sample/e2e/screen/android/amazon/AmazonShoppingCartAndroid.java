package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonShoppingCartScreen;
import com.znsio.sample.e2e.screen.web.amazon.AmazonHomeScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonShoppingCartAndroid extends AmazonShoppingCartScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byShoppingCartButtonXpath = By.xpath("//android.view.View[@content-desc=\"Cart\"]/android.view.View");

    private static final By byShoppingCartMessageXpath = By.xpath("//android.view.View[@resource-id = \"sc-buy-box\"]");
    private static final By byFirstProductInCartXpath = By.xpath("//android.view.View[contains(@content-desc=\""+ SAMPLE_TEST_CONTEXT.PRODUCT_NAME+"\")]/android.widget.TextView");

    public AmazonShoppingCartAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AmazonShoppingCartScreen navigateToTheShoppingCart(){
        LOGGER.info("Select the shopping cart option");
        driver.waitTillElementIsPresent(byShoppingCartButtonXpath).click();
        return this;
    }

    @Override
    public boolean checkShoppingCartMessage(){
        visually.checkWindow(SCREEN_NAME, "Shopping Cart Screen and message is displayed");
        WebElement shoppingCartMsg = driver.waitTillElementIsVisible(byShoppingCartMessageXpath);
        return shoppingCartMsg.isDisplayed();
    }

    @Override
    public String getShoppingCartProduct(){
        LOGGER.info("Get the name of the first product added in the cart");
        WebElement element = driver.waitTillElementIsPresent(byFirstProductInCartXpath);
        return element.getText();
    }
}
