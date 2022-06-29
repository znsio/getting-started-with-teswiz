package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ShoppingCartScreen;
import com.znsio.sample.e2e.screen.theapp.AppLaunchScreen;
import com.znsio.sample.e2e.screen.web.theapp.AppLaunchScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ShoppingCartWeb extends ShoppingCartScreen {
    private static final String SCREEN_NAME = AppLaunchScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(AppLaunchScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;

    private final By productTitle = By.cssSelector("span.sc-product-title");

    public ShoppingCartWeb(Driver driver, Visual visually) {
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
