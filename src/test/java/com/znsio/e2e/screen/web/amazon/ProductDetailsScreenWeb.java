package com.znsio.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.amazon.ProductDetailsScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.junit.Assert;

public class ProductDetailsScreenWeb extends ProductDetailsScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ProductDetailsScreenWeb.class.getSimpleName();
    private final TestExecutionContext context;

    public ProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Product details screen");
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public ProductDetailsScreen validateProductOnDetailsPage() {
        Assert.assertTrue(context.getTestStateAsString("selectedItemName").equals(driver.findElementByXpath("//span[@id='productTitle']").getText()));
        return this;
    }

    @Override
    public ProductDetailsScreen addToCart() {
        driver.findElementByXpath("//input[@id='add-to-cart-button']").click();
        return this;
    }
}