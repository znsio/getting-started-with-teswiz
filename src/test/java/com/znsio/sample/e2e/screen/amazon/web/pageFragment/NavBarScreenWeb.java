package com.znsio.sample.e2e.screen.amazon.web.pageFragment;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.NavBarScreen;
import com.znsio.sample.e2e.screen.amazon.web.SearchResultsScreenWeb;
import com.znsio.sample.e2e.screen.theapp.AppLaunchScreen;
import com.znsio.sample.e2e.screen.web.theapp.AppLaunchScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class NavBarScreenWeb extends NavBarScreen {
    private static final String SCREEN_NAME = NavBarScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(NavBarScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    public final By searchButton = By.cssSelector("span#nav-search-submit-text");
    public static final By searchInput = By.cssSelector("input#twotabsearchtextbox");
    public static final By itemsIncart = By.cssSelector("span#nav-cart-count");

    public NavBarScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Home screen");
    }

    public By getSearchInputByLocator() {
        return searchInput;
    }

    public SearchResultsScreenWeb searchForProduct(String productName) {
        driver.findElement(searchInput).sendKeys(productName);
        driver.waitForClickabilityOf(searchButton);
        driver.findElement(searchButton).click();
        return new SearchResultsScreenWeb(driver, visually);
    }

    public int getItemsCountInCart() {
        return Integer.parseInt(driver.findElement(itemsIncart).getText());
    }
}
