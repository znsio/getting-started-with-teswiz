package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultsScreenWeb extends SearchResultsScreen {
    private final Driver driver;
    private final Visual visually;

    private static final String SCREEN_NAME = SearchResultsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SearchResultsScreenWeb.class.getName());

    private final By byProductTitleCssSelector = By.cssSelector(".s-card-container .a-size-medium");
    private final By byFirstProductTitleTextCssSelector = By.cssSelector("div[cel_widget_id='MAIN-SEARCH_RESULTS-2'] span.a-size-medium");


    public SearchResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Search Results screen");
        long threadId = Thread.currentThread()
                .getId();
    }

    @Override
    public String getTitleOfFirstProduct() {
        return driver.waitTillElementIsPresent(byFirstProductTitleTextCssSelector).getText();
    }

    @Override
    public boolean isSearchedResultsDisplayed(String Product) {
        driver.waitTillElementIsPresent(byProductTitleCssSelector);
        boolean flag = true;
        for (WebElement productTitle : driver.findElements(byProductTitleCssSelector)) {
            if (!productTitle.getText().contains(Product)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public ProductDetailsScreen userSelectsFirstProduct() {
        driver.waitForClickabilityOf(byFirstProductTitleTextCssSelector).click();
        driver.switchToNextTab();
        return ProductDetailsScreenWeb.get();
    }
}