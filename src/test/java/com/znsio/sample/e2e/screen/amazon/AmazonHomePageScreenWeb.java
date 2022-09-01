package com.znsio.sample.e2e.screen.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonHomePageScreenWeb extends AmazonHomePageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By SearchBox = By.id("twotabsearchtextbox");
    private static final By searchButton = By.id("nav-search-submit-button");
    private final TestExecutionContext context;
    private static final By amazonHeader = By.id("nav-belt");

    public AmazonHomePageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Amazon Base URL");
    }

    @Override
    public boolean isAmazonHomepageLaunched() {
        LOGGER.info("Validating if Amazon Base URL is opened");
        return driver.isElementPresent(amazonHeader);
    }

    @Override
    public ProductListPageScreen userSearchesForTheProduct(String prod) {
        LOGGER.info("Entering query in searchbar");
        driver.waitTillElementIsPresent(SearchBox).sendKeys(prod);
        LOGGER.info("Tap search button after entering query");
        driver.waitForClickabilityOf(searchButton).click();
        return ProductListPageScreen.get();
    }
}