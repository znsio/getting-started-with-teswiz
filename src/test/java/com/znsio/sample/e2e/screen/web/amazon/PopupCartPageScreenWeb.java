package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.CartPageScreen;
import com.znsio.sample.e2e.screen.amazon.PopupCartPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PopupCartPageScreenWeb extends PopupCartPageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = PopupCartPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    private final WebDriver innerDriver;

    private final By byProceedToCheckoutXpath = By.xpath("//span[@id='attach-sidesheet-checkout-button']");
    private final By byGoToCartXpath = By.xpath("//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce']");
    private final By byAddedToCartMsgXpath = By.xpath("//span[contains(@class,'sw-atc-text')]");
    public PopupCartPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public PopupCartPageScreen waitTillProductAddedToCart() {
        driver.waitForClickabilityOf(byProceedToCheckoutXpath).isDisplayed();
        LOGGER.info("Product added to cart.");
        return this;
    }

    @Override
    public CartPageScreen navigateToCartFromAddedToCartPopup() {
        driver.waitForClickabilityOf(byGoToCartXpath).click();
        return waitTillNavigatedToCart();
    }

    @Override
    public String getAddedToCartSuccessMsg() {
        return driver.findElement(byAddedToCartMsgXpath).getText().trim();
    }

    private CartPageScreen waitTillNavigatedToCart() {
        CartPageScreen cartPageScreen = CartPageScreen.get().waitTillCartIsDisplayed();
        return cartPageScreen;
    }
}
