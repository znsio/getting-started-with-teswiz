package com.znsio.e2e.screen.web.amazon;

import com.znsio.e2e.screen.amazon.ProductDetailsScreen;
import com.znsio.e2e.screen.amazon.ProductListingScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.junit.Assert;


public class ProductListingScreenWeb extends ProductListingScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(ProductListingScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ProductListingScreenWeb.class.getSimpleName();
    private static String selectedItemFromProductLists;

    public ProductListingScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Product listing screen");
    }

    @Override
    public ProductListingScreen verifySearchedResults(String keyWord) {
        String showingResultsFor = driver.findElementByXpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span[@class='a-color-state a-text-bold']").getText();
        Assert.assertTrue(showingResultsFor.contains(keyWord));
        return this;
    }

    @Override
    public ProductDetailsScreen selectAnyProductFromListingPage() {
        selectedItemFromProductLists = driver.findElementByXpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]").getText();
        driver.findElementByXpath("(//img[@class='s-image'])[1]").click();
        // Jump to next tab code
        return ProductDetailsScreen.get();
    }

    public static String getSelectedItemName() {
        return selectedItemFromProductLists;
    }
}
