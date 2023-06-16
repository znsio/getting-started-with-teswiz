package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import com.znsio.sample.e2e.screen.ajio.AjioHomeScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class AjioCartScreenWeb
        extends AjioCartScreen {
    private static final String SCREEN_NAME = AjioCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byProductInCartClassName = By.className("product-card");
    private static final By bySignOutXpath = By.xpath("//a[text()='Sign Out']");
    private static final By byDeleteProductClassName = By.className("delete-btn");
    private static final By bySubmitDeleteProductXpath = By.xpath("//div[text()='DELETE']");
    private static final By byEmptyBagMessageClassName = By.className("empty-msg");


    private final Driver driver;
    private final Visual visually;

    public AjioCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public boolean isProductAddedToBag() {
        LOGGER.info("Checking if product is added to Bag");
       return driver.isElementPresent(byProductInCartClassName);
    }

    @Override
    public AjioCartScreen removeProductFromCart() {
        LOGGER.info("Removing Product from cart");
        driver.findElement(byDeleteProductClassName).click();
        JavascriptExecutor js = (JavascriptExecutor) driver.getInnerDriver();
        js.executeScript("arguments[0].click()", driver.waitTillElementIsVisible(bySubmitDeleteProductXpath));
        return this;
    }

    @Override
    public boolean isCartEmpty() {
        LOGGER.info("Checking if cart is empty");
        return driver.isElementPresent(byEmptyBagMessageClassName);
    }

    @Override
    public AjioHomeScreen logout() {
        LOGGER.info("Logout user");
        driver.waitTillElementIsVisible(bySignOutXpath).click();
        return AjioHomeScreen.get();
    }
}
