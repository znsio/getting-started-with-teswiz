package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ShoppingCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ShoppingCartScreenWeb extends ShoppingCartScreen {
    private static final String SCREEN_NAME = ShoppingCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(ShoppingCartScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;

    private final By productTitle = By.cssSelector("span.sc-product-title");

    public ShoppingCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    /**
     * Returns product title .
     */
    @Override
    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }
}
