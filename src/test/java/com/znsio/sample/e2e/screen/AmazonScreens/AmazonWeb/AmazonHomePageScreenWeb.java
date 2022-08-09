package com.znsio.sample.e2e.screen.AmazonScreens.AmazonWeb;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.AmazonShopping.AMAZON_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.AmazonScreens.AmazonHomePageScreen;
import com.znsio.sample.e2e.screen.AmazonScreens.ProductListPageScreen;
import com.znsio.sample.e2e.screen.web.ScreenShotScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonHomePageScreenWeb extends AmazonHomePageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By SearchTextBarId = By.id("twotabsearchtextbox");
    private static final By searchButton = By.id("nav-search-submit-button");
    private final TestExecutionContext context;
    private static final By amazonLogo = By.id("nav-logo-sprites");

    public AmazonHomePageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Amazon Home Screen");
    }

    @Override
    public boolean isAmazonHomepageLaunched() {
        return driver.isElementPresent(amazonLogo);
    }

    @Override
    public ProductListPageScreen userSearchesForTheProduct(String product) {
        driver.waitTillElementIsPresent(SearchTextBarId).sendKeys(product);
        driver.waitForClickabilityOf(searchButton).click();
        return ProductListPageScreen.get();
    }
}
