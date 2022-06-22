package com.znsio.e2e.screen.web.amazoncartvalidation;

import com.znsio.e2e.screen.amazoncartvalidation.AmazonHomeScreen;
import com.znsio.e2e.screen.amazoncartvalidation.SearchResultsListScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.openqa.selenium.By;

public class AmazonHomeScreenWeb extends AmazonHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private final static By searchBoxId=By.id("twotabsearchtextbox");
    private final static By searchButtonId=By.id("nav-search-submit-button");




    public AmazonHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Amazon Home screen");
    }


    @Override
    public SearchResultsListScreen searchForProduct(String productName) {
       driver.findElement(searchBoxId).sendKeys(productName);
       driver.findElement(searchButtonId).click();
        return SearchResultsListScreen.get();
    }
}
