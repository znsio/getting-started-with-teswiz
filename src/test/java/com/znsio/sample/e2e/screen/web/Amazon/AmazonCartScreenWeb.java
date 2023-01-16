package com.znsio.sample.e2e.screen.web.Amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonCartScreenWeb extends AmazonCartScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byItemStringClassName = By.className("a-truncate-cut");

    public AmazonCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }
    @Override
    public String getCartItem() {
        String itemInCart =  driver.waitTillElementIsPresent(byItemStringClassName).getText();
        LOGGER.info(String.format("Item added in the cart is '%s'", itemInCart));
        return itemInCart;
    }
}
