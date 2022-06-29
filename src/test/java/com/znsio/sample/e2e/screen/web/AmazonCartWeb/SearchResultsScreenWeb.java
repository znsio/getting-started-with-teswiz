package com.znsio.sample.e2e.screen.web.AmazonCartWeb;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.AmazonCart.ProductDetailsScreen;
import com.znsio.sample.e2e.screen.AmazonCart.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.znsio.e2e.tools.Wait.waitFor;

public class SearchResultsScreenWeb extends SearchResultsScreen{

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(SearchResultsScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private final String SCREEN_NAME = SearchResultsScreenWeb.class.getSimpleName();
    private final By verifySearchResult = By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/child::span[3]");
    private final By firstResultItem = By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[2]");
    private final By nextResultItem = By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[3]");


    public SearchResultsScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.driver = driver;
        this.visually = visually;
        this.context = Runner.getTestExecutionContext(threadId);
        visually.takeScreenshot(SCREEN_NAME, "Search results screen");
    }


    @Override
    public boolean verifyTheSearchedResults(String productName) {
        String resultsDisplayedFor = driver.findElement(verifySearchResult).getText();
        boolean isPresentInResults = resultsDisplayedFor.contains(productName);
        return isPresentInResults;
    }


    @Override
    public ProductDetailsScreen selectTheFirstProduct() {
        WebElement firstItemName= driver.findElement(firstResultItem);
        context.addTestState("ExactProductName",firstItemName.getText());
        firstItemName.click();
        //switching to next tab
        waitFor(5);
        driver.switchToNextTab();
        return ProductDetailsScreen.get();
    }

}
