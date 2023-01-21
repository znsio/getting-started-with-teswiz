package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonHomeScreenWeb extends AmazonHomeScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By bySearchTextBoxById = By.id("twotabsearchtextbox");
    private final By bySubmitButtonId = By.id("nav-search-submit-button");


    public AmazonHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home screen");
    }

    @Override
    public AmazonHomeScreen enterTheTextOnSearchBar(String productName) {
        WebElement searchBox = driver.waitTillElementIsVisible(bySearchTextBoxById);
        searchBox.click();
        searchBox.sendKeys(productName);
        visually.checkWindow(SCREEN_NAME, "Product search on homePage");
        return  AmazonHomeScreen.get();
    }

    @Override
    public AmazonSearchScreen clickTheSearchButton() {
        driver.waitForClickabilityOf(bySubmitButtonId,3).click();
        return AmazonSearchScreen.get();
    }

}
