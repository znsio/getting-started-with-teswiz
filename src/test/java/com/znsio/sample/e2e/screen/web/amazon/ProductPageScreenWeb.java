package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.CONTEXT_AMAZON;
import com.znsio.sample.e2e.screen.amazon.PopupCartPageScreen;
import com.znsio.sample.e2e.screen.amazon.ProductPageScreen;
import com.znsio.sample.e2e.screen.android.jiomeet.LandingScreenAndroid;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPageScreenWeb extends ProductPageScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = LandingScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    private final WebDriver innerDriver;

    private final By byAddToCartButtonXpath = By.xpath("//input[@id='add-to-cart-button']");
    private final By byProductTitleXpath = By.xpath("//span[@id='productTitle']");
    private final By byNavigationBarCartCountXpath = By.xpath("//span[@id='nav-cart-count']");
    private final By byPopupProceedToCheckoutXpath = By.xpath("//span[@id='attach-sidesheet-checkout-button']");
    private final By byProductPriceXpath = By.xpath("//span[contains(@class,'priceToPay')]/span[@class='a-offscreen']");


    public ProductPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public ProductPageScreen waitTillProductPageIsDisplayed() {
        String productTitle = driver.waitTillElementIsPresent(byProductTitleXpath).getText().trim();
        context.addTestState(CONTEXT_AMAZON.PRODUCT_TITLE,productTitle);
        String productPrice = driver.findElement(byProductPriceXpath).getText().trim();
        //productPrice = productPrice.replaceAll("[^\\d.]", "");
        context.addTestState(CONTEXT_AMAZON.PRODUCT_PRICE,productPrice);
        LOGGER.info(String.format("On web product page. Product Title - '%s', Price - '%s'", productTitle, productPrice));
        return this;
    }

    @Override
    public PopupCartPageScreen addProductToCart() {
        driver.waitForClickabilityOf(byAddToCartButtonXpath).click();
        return this.waitForAddingToCartToPerform();
    }

    private PopupCartPageScreen waitForAddingToCartToPerform() {
        return PopupCartPageScreen.get().waitTillProductAddedToCart();
    }

    @Override
    public int getNumberOfProductsInCart() {
        String cartCount;
        cartCount = driver.waitTillElementIsPresent(byNavigationBarCartCountXpath).getText().trim();
        LOGGER.info(String.format("Current product in cart: '%s'", cartCount));
        return Integer.parseInt(cartCount);
    }
}
