package com.znsio.sample.e2e.screen.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CartLandingPageScreenWeb extends CartLandingPageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = CartLandingPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    private static final By productTitle= By.xpath("//div[@class='sc-list-item-content']//span[@class='a-truncate-cut']");
    private static final By productPrice = By.xpath("//div[@class='sc-list-item-content']//span[@class='currencyINR']/parent::span");
    private static final By productCount = By.className("a-dropdown-prompt");

    public CartLandingPageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Cart Landing Page Screen");
    }

    @Override
    public String getProductTitle(){
        LOGGER.info("get product title from cart");
        return driver.findElement(productTitle).getText().trim();
    }

    @Override
    public String getProductPrice(){
        LOGGER.info("get product price from cart");
        String n=driver.findElement(productPrice).getText().trim();
        return n.substring(0,n.indexOf('.'));
    }

    @Override
    public String getProductCount(){
        LOGGER.info("get product quantity from cart");
        return driver.findElement(productCount).getText().trim();
    }
}