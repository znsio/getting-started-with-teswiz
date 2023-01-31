package com.znsio.sample.e2e.screen.android.Amazon;

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
    private static final By byCheckoutButtonXpath = By.xpath("//android.view.View[@resource-id='sc-mini-buy-box']");
    private static final By byItemStringXpath = By.xpath("(//android.view.View[@resource-id='activeCartViewForm']//android.view.View[@content-desc])");

    public CartScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Cart Screen Android");
    }

    @Override
    public boolean isScreenLoaded() {
        boolean loadStatus = driver.waitTillElementIsPresent(byCheckoutButtonXpath).isDisplayed();
        LOGGER.info(String.format("Cart screen  load status: '%s'", loadStatus));
        return loadStatus;
    }

    @Override
    public String getCartItem() {
        String itemInCart = driver.waitTillElementIsPresent(byItemStringXpath).getAttribute("content-desc");
        LOGGER.info(String.format("Item added in the cart is '%s'", itemInCart));
        return itemInCart;
    }
}
