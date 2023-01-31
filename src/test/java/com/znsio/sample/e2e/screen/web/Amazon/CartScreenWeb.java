package com.znsio.sample.e2e.screen.web.Amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.AMAZON_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.CartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CartScreenWeb extends CartScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = CartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byItemStringClassName = By.className("a-truncate-cut");

    public CartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Cart Screen Web");
    }

    @Override
    public boolean isScreenLoaded() {
        String title = AMAZON_TEST_CONTEXT.CART_SCREEN_TITLE;
        String actualScreenTitle = driver.getInnerDriver().getTitle();
        LOGGER.info(String.format("Cart screen title is: '%s'", actualScreenTitle));
        return actualScreenTitle.contains(title);
    }

    @Override
    public String getCartItem() {
        String itemInCart = driver.waitTillElementIsPresent(byItemStringClassName).getText();
        LOGGER.info(String.format("Item added in the cart is '%s'", itemInCart));
        return itemInCart;
    }
}
