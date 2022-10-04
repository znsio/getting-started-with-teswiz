package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import com.znsio.sample.e2e.screen.android.jiomeet.LandingScreenAndroid;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsScreenWeb extends SearchResultsScreen {
    private static final String SCREEN_NAME = LandingScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By byAllProductTitleXpath = By.xpath("//div[@data-component-type='s-search-result']//h2");
    private final By byFirstProductTitleXpath = By.xpath("//div[@cel_widget_id='MAIN-SEARCH_RESULTS-2']//h2");

    public SearchResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String getTitleOfFirstProduct() {
        return driver.waitTillElementIsPresent(byFirstProductTitleXpath).getText();
    }

    @Override
    public boolean isSearchedResultsDisplayed(String Product) {
        driver.waitTillElementIsPresent(byAllProductTitleXpath);
        List<WebElement> allResults = driver.findElements(byAllProductTitleXpath);
        if (!allResults.isEmpty()) {
            LOGGER.debug("Search results found");
            return true;
        }
        return false;

    }

    @Override
    public ProductDetailsScreen userSelectsFirstProduct() {
        driver.waitForClickabilityOf(byFirstProductTitleXpath).click();
        driver.switchToNextTab();
        LOGGER.debug("Clicked on first product, and switched to Product Details Screen");
        visually.checkWindow(SCREEN_NAME, "On Product Detail Screen");
        return ProductDetailsScreenWeb.get();
    }
}
