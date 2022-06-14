package com.znsio.e2e.screen.web.amazon;

import com.znsio.e2e.screen.amazon.ProductDetailsScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class ProductDetailsScreenWeb extends ProductDetailsScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(ProductDetailsScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ProductDetailsScreenWeb.class.getSimpleName();

    public ProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Product details screen");
    }

    @Override
    public ProductDetailsScreen validateProductOnDetailsPage() {
        Assert.assertTrue(ProductListingScreenWeb.getSelectedItemName().equals(driver.findElementByXpath("//span[@id='productTitle']").getText()));
        return this;
    }

    @Override
    public ProductDetailsScreen addToCart() {
        driver.findElementByXpath("//input[@id='add-to-cart-button']").click();
        driver.findElementByXpath("(//a[@id='attach-close_sideSheet-link'])[1]").click();
        return this;
    }
}
