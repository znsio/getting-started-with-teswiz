package com.znsio.e2e.screen.web.amazoncartvalidation;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.amazoncartvalidation.ProductDetailsScreen;
import com.znsio.e2e.screen.amazoncartvalidation.SearchResultsListScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultsListScreenWeb extends SearchResultsListScreen {

    private final Driver driver;
    private final Visual visually;

    private final TestExecutionContext context;
    private static final String SCREEN_NAME = SearchResultsListScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static final By searchResultListFirstElements=By.xpath("(//span[contains(@class,'a-size-medium a-color-base a-text-normal')])[1]");





    public SearchResultsListScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.driver = driver;
        this.visually = visually;
        this.context = Runner.getTestExecutionContext(threadId);
        visually.takeScreenshot(SCREEN_NAME, "Search Results List screen");

    }

    @Override
    public boolean isSearchedProductDisplayed(String productName) {
   String searchResultElement=driver.findElement(searchResultListFirstElements).getText();
        return searchResultElement.contains(productName);
    }

    @Override
    public ProductDetailsScreen selectFirstProduct() {

         WebElement firstProduct=driver.findElement(searchResultListFirstElements);


        context.addTestState("Product Title",firstProduct.getText());
         firstProduct.click();
         driver.switchToNextTab();
        return ProductDetailsScreen.get();
    }
}
