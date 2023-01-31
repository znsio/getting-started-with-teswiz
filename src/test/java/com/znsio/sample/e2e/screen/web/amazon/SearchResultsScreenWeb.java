package com.znsio.sample.e2e.screen.web.amazon;

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

public class SearchResultsScreenWeb extends SearchResultsScreen {
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = SearchResultsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final By bySearchStringXpath = By.xpath("//span[contains(text(),\"results\")]/following-sibling::span[2]");

    private static final By byProductFoundCSSSelector = By.cssSelector(".a-size-mini span");

    private static final By byProductCostXpath = By.xpath("(//span[@class='a-price'])[1]/span");


    public SearchResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        visually.checkWindow(SCREEN_NAME, "Search results screen");
    }

    private List<WebElement> waitForProductList(){
        WebDriverWait wait = new WebDriverWait(driver.getInnerDriver(),30);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byProductFoundCSSSelector));
    }

    @Override
    public Boolean matchTopResultsWithSearchedString(String product) {
        List<WebElement> list = waitForProductList();
        for(WebElement ele : list.subList(0,5)){
            if(!ele.getText().contains(product)){
                return false;
            }
        }
        return true;
    }

    @Override
    public SearchResultsScreen clickOnFirstProduct(){
        context.addTestState(SAMPLE_TEST_CONTEXT.PRODUCT_NAME, getFirstProductName());
        context.addTestState(SAMPLE_TEST_CONTEXT.PRODUCT_COST, getFirstProductCost());

        waitForProductList().get(0).click();
        visually.checkWindow(SCREEN_NAME, "Clicked on first item");
        return this;
    }

    @Override
    public String getFirstProductName(){
        String itemName = waitForProductList().get(0).getText();

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
