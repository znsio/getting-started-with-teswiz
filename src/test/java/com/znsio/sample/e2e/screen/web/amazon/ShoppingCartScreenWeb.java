package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ShoppingCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ShoppingCartScreenWeb extends ShoppingCartScreen {
    private final Driver driver;
    private final Visual visually;

    private static final String SCREEN_NAME = ShoppingCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(ShoppingCartScreenWeb.class.getName());

    private final By byCartButtonCssSelector = By.cssSelector("#attach-sidesheet-view-cart-button .a-button-input");

    public ShoppingCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Search Results screen");
        long threadId = Thread.currentThread()
                .getId();
    }

    @Override
    public String getAddedProductTitleFromShoppingCartScreen(String productTitle) {
        driver.waitForClickabilityOf(byCartButtonCssSelector).click();
        return driver.findElementByXpath("//span[@class='a-truncate-cut' and text()='" + productTitle + "']").getText();
    }
}