package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomePageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonHomePageWeb extends AmazonHomePageScreen {
    private final Visual visually;
    private final Driver driver;
    public static final String SCREEN_NAME = AmazonHomePageWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By searchBar = By.xpath("//input[@aria-label='Search']");
    public static By selectSearchResult(String productName) {
        final By selectSearchResult = By.xpath("//div[@class='s-suggestion-container']//div[@aria-label='"+productName+"']");
        return selectSearchResult;
    }

    public AmazonHomePageWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    public Boolean searchProduct(String productToSeacrh) {
        Boolean iSTheProductDisplayedInTheSearchSuggestionBar = false;
        visually.takeScreenshot(SCREEN_NAME, "Before entering the product name in serach bar");
        LOGGER.info(String.format("ProductName: "+productToSeacrh+""));
        WebElement enterProduct = driver.waitTillElementIsPresent(searchBar, 20);
        enterProduct.sendKeys(productToSeacrh);
        visually.takeScreenshot(SCREEN_NAME, "Before selecting the product from the Search bar suggestions");
        if (driver.isElementPresent(selectSearchResult(productToSeacrh))) {
            iSTheProductDisplayedInTheSearchSuggestionBar = true;
            driver.waitTillElementIsPresent(selectSearchResult(productToSeacrh), 5).click();
            visually.takeScreenshot(SCREEN_NAME, "After selecting the product from the Search bar suggestions");
        }
        return iSTheProductDisplayedInTheSearchSuggestionBar;
    }
}
