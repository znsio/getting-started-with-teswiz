package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.CartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CartScreenAndroid extends CartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = CartScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    private static final By byCartHeadingTextXpath = By.xpath("//android.view.View[@resource-id='sc-proceed-to-checkout-params-form']");
    private static final By byCartItemNameXpath = By.xpath("//android.view.View[contains(@content-desc,\"Apple iPhone 13\")]/android.widget.TextView");

    public CartScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Cart screen");
    }

    @Override
    public boolean isCartHeadingVisible(){
        return driver.isElementPresent(byCartHeadingTextXpath);
    }

    @Override
    public String getCartProductName(){
        String cartItemName = driver.waitTillElementIsPresent(byCartItemNameXpath).getText();
        LOGGER.info(String.format("Cart item name: '%s'", cartItemName));
        return cartItemName;
    }

}
