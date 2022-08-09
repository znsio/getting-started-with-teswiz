package com.znsio.sample.e2e.screen.AmazonScreens.AmazonWeb;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.AmazonShopping.AMAZON_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.AmazonScreens.CartLandingPageScreen;
import com.znsio.sample.e2e.screen.AmazonScreens.ProductLandingPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductLandingPageScreenWeb extends ProductLandingPageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductLandingPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    private static final By byProductTitleId = By.id("productTitle");
    private static final By byLandingPageProductPriceXpath = By.xpath("//*[@id='corePrice_desktop']/div/table/tbody/tr[2]/td[2]/span[1]/span[2]");
    private String productTitleOnProductLandingPage;
    private String productPriceOnProductLandingPage;
    private static final By byAddToCartButtonXpath = By.xpath("//*[@value='Add to Cart']");
    private static final By byGoToCartXpath = By.xpath("//form[@id='attach-view-cart-button-form']/span/span/input");
    private String updatedPriceOnProductLandingPage;
    private String price;

    public ProductLandingPageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Product Landing Page Screen");
    }

    @Override
    public boolean isProdDetailsAndTitleCorrect(String firstProductTitle, String firstProductPrice) {
        driver.switchToNextTab();
        productTitleOnProductLandingPage = driver.findElement(byProductTitleId).getText().trim();
        productPriceOnProductLandingPage = driver.findElement(byLandingPageProductPriceXpath).getText();
        String[] arrProductPriceOnProductLandingPage = productPriceOnProductLandingPage.split("[₹]");
        price = arrProductPriceOnProductLandingPage[1];
        String[] updatedArrPriceOnProductLandingPage = price.split("[.]");
        updatedPriceOnProductLandingPage = updatedArrPriceOnProductLandingPage[0];
        if (firstProductTitle.trim().equals(productTitleOnProductLandingPage) &&
                firstProductPrice.trim().equals(updatedPriceOnProductLandingPage)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ProductLandingPageScreen addProductToCart() {
        driver.waitForClickabilityOf(byAddToCartButtonXpath).click();
        return this;
    }

    @Override
    public CartLandingPageScreen goToCartPage() {
        driver.waitForClickabilityOf(byGoToCartXpath, 30).click();
        return CartLandingPageScreen.get();
    }
}