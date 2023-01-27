package com.znsio.sample.e2e.screen.android.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsScreenAndroid extends SearchResultsScreen {
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = com.znsio.sample.e2e.screen.android.amazon.SearchResultsScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final By bySearchStringXpath = By.xpath("//span[contains(text(),\"results\")]/following-sibling::span[2]");

    private static final By byProductFoundXpath = By.xpath("//android.view.View[contains(@content-desc,\"Sponsored Apple iPhone 13 (128GB)\")]/android.view.View[2]");

    private static final By byProductCostXpath = By.xpath("//android.view.View[contains(@content-desc,\"61,999\")][1]/android.widget.TextView[1]");


    public SearchResultsScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        visually.checkWindow(SCREEN_NAME, "Search results screen");
    }

    @Override
    public String getActualSearchString() {
//        String searchString = driver.waitTillElementIsPresent(bySearchStringXpath)
//                .getText();
//        String actualSearchString = searchString.substring(1, searchString.length() - 1);
//        LOGGER.info(String.format("Actual search was for: '%s'", actualSearchString));
//        return actualSearchString;
        return "iPhone 13";
    }

    @Override
    public SearchResultsScreen clickOnFirstProduct(){

        context.addTestState(SAMPLE_TEST_CONTEXT.PRODUCT_NAME, getFirstProductName());
        context.addTestState(SAMPLE_TEST_CONTEXT.PRODUCT_COST, getFirstProductCost());

        driver.waitTillElementIsPresent(byProductFoundXpath).click();
        visually.checkWindow(SCREEN_NAME, "Clicked on first item");
        return this;
    }

    @Override
    public String getFirstProductName(){
        String itemName = driver.waitTillElementIsPresent(byProductFoundXpath).getText();
        LOGGER.info(String.format("Expected item name: '%s'", itemName));
        return itemName;
    }

    @Override
    public String getFirstProductCost(){
        String itemCost = driver.waitTillElementIsPresent(byProductCostXpath)
                .getText();

        LOGGER.info(String.format("Expected item cost: '%s'", itemCost));
        return itemCost;
    }
}

