package com.znsio.sample.e2e.screen.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductLandingPageScreenWeb extends ProductLandingPageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductLandingPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    private static final By productTitle = By.id("productTitle");
    private static final By productPrice = By.xpath("//div[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']");
    private static final By AddToCartBtn = By.id("submit.add-to-cart");
    private static final By OpenCartBtn =By.id("attach-sidesheet-view-cart-button");

    public ProductLandingPageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Product Description Page Screen");
    }

    @Override
    public String getProductTitle(){
        LOGGER.info("Switching to next Tab");
        driver.switchToNextTab();
        LOGGER.info("get product title from cart");
        return driver.findElement(productTitle).getText().trim();
    }

    @Override
    public String getProductPrice(){
        LOGGER.info("get product price from cart");
        String n=driver.findElement(productPrice).getText().trim();
        return driver.findElement(productPrice).getText().trim();
    }

    @Override
    public ProductLandingPageScreen addProductToCart() {
        LOGGER.info("adding product to cart");
        driver.waitTillElementIsPresent(AddToCartBtn).click();
        return this;
    }

    @Override
    public CartLandingPageScreen goToCartPage() {
        LOGGER.info("opening shopping cart");
        driver.waitTillElementIsPresent(OpenCartBtn);
        driver.waitForClickabilityOf(OpenCartBtn).click();
        return CartLandingPageScreen.get();
    }
}