package com.znsio.sample.e2e.screen.android.ajio;

import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AjioCartScreenAndroid extends AjioCartScreen {
    private static final String SCREEN_NAME = AjioCartScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byProductTitleId = By.id("com.ril.ajio:id/productTitle");
    private final Driver driver;
    private final Visual visually;

    public AjioCartScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Cart Page");
    }

    @Override
    public String getActualProductName() {
        LOGGER.info("getCartProductName");
        visually.checkWindow(SCREEN_NAME, "Product in the cart");
        WebElement product = driver.waitTillElementIsPresent(byProductTitleId);
        product.click();
        String productTitle = product.getText();
        LOGGER.info("productTitle in the cart" + productTitle);
        return productTitle;
    }

}
