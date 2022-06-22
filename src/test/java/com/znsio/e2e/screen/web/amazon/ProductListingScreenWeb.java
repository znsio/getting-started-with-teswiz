package com.znsio.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.amazon.ProductDetailsScreen;
import com.znsio.e2e.screen.amazon.ProductListingScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.junit.Assert;


public class ProductListingScreenWeb extends ProductListingScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ProductListingScreenWeb.class.getSimpleName();
    private final TestExecutionContext context;

    public ProductListingScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Product listing screen");
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public ProductListingScreen verifySearchedResults(String keyWord) {
        String showingResultsFor = driver.findElementByXpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span[@class='a-color-state a-text-bold']").getText();
        Assert.assertTrue(showingResultsFor.contains(keyWord));
        return this;
    }

    @Override
    public ProductDetailsScreen selectAnyProductFromListingPage() {
        context.addTestState("selectedItemName", driver.findElementByXpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]").getText());
        driver.findElementByXpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]").click();
        driver.switchToNextTab();
        return ProductDetailsScreen.get();
    }
}