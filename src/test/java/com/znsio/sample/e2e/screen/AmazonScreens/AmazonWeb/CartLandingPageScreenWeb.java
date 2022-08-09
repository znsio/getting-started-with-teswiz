package com.znsio.sample.e2e.screen.AmazonScreens.AmazonWeb;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.AmazonShopping.AMAZON_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.AmazonScreens.CartLandingPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CartLandingPageScreenWeb extends CartLandingPageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = CartLandingPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    private static final By byCartProductTitleXpath = By.xpath("//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-mini sc-info-block']/li[1]/span/a/span[1]/span/span[2]");
    private static final By byCartProductPriceXpath = By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']");
    private String cartProductTitle;
    private String cartProductPrice;
    private String productPriceOnCartLandingPage;
    private String cartPrice;

    public CartLandingPageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Cart Landing Page Screen");
    }

    @Override
    public boolean validateSameProductAddedToCart(String firstProductTitle, String firstProductPrice) {
        cartProductTitle = driver.findElement(byCartProductTitleXpath).getText().trim();
        productPriceOnCartLandingPage = driver.findElement(byCartProductPriceXpath).getText().trim();
        String[] arrProductPriceOnCartLandingPage = productPriceOnCartLandingPage.split("[.]");
        cartProductPrice = arrProductPriceOnCartLandingPage[0];
        return cartProductTitle.equals(firstProductTitle) && cartProductPrice.equals(firstProductPrice);
    }
}