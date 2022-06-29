package com.znsio.sample.e2e.screen.amazon.web.pageFragment;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.NavBarScreen;
import com.znsio.sample.e2e.screen.amazon.web.SearchResultsWeb;
import com.znsio.sample.e2e.screen.theapp.AppLaunchScreen;
import com.znsio.sample.e2e.screen.web.theapp.AppLaunchScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class NavBarWeb extends NavBarScreen {

    public static final By searchInput = By.cssSelector("input#twotabsearchtextbox");
    public static final By itemsIncart = By.cssSelector("span#nav-cart-count");
    private static final String SCREEN_NAME = AppLaunchScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(AppLaunchScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final By searchButton = By.cssSelector("span#nav-search-submit-text");

    public NavBarWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Home screen");
    }

    public By getSearchInput() {
        return searchInput;
    }


    public SearchResultsWeb searchForProduct(String productName) {
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();
        return new SearchResultsWeb(driver, visually);
    }

    public int getItemsInCart() {
        return Integer.parseInt(driver.findElement(itemsIncart).getText());
    }
}
