package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonProductDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import com.znsio.sample.e2e.screen.android.ajio.AjioSearchResultsScreenAndroid;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AmazonSearchResultsScreenWeb extends AmazonSearchResultsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonSearchResultsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final By bySearchStringXpath = By.xpath("//span[contains(text(),\"results\")]/following-sibling::span[2]");

    private static final By byProductFoundCSSSelector = By.cssSelector(".a-size-mini span");

    private static final By byProductCostXpath = By.xpath("(//span[@class='a-price'])[1]/span");


    public AmazonSearchResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Search results screen");
    }

    private WebElement waitForProductListAndGetFirstProduct(){
        WebDriverWait wait = new WebDriverWait(driver.getInnerDriver(),30);
        List<WebElement> productTitleWebElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byProductFoundCSSSelector));
        return productTitleWebElements.get(0);
    }

    @Override
    public String getActualSearchString() {
        String searchString = driver.waitTillElementIsPresent(bySearchStringXpath)
                .getText();
        String actualSearchString = searchString.substring(1, searchString.length() - 1);
        LOGGER.info(String.format("Actual search was for: '%s'", actualSearchString));
        return actualSearchString;
    }

    @Override
    public AmazonSearchResultsScreen clickOnFirstItem(){
        waitForProductListAndGetFirstProduct().click();
        visually.checkWindow(SCREEN_NAME, "Clicked on first item");
        return this;
    }

    @Override
    public String getFirstItemName(){
        String itemName = waitForProductListAndGetFirstProduct().getText();

        LOGGER.info(String.format("Expected item name: '%s'", itemName));
        return itemName;
    }

    @Override
    public String getFirstItemCost(){
        String itemCost = driver.waitTillElementIsPresent(byProductCostXpath)
                .getText();

        LOGGER.info(String.format("Expected item cost: '%s'", itemCost));
        return itemCost;
    }
}
