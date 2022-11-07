package com.znsio.sample.e2e.screen.web.amazonWeb;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.IphoneListScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomeScreenWeb extends AmazonHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final TestExecutionContext context;
    private final By bySearchTabId = By.id("twotabsearchtextbox");
    private final By bySearchButton = By.id("nav-search-submit-button");

    public AmazonHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    public IphoneListScreen searchIphone13(){
        LOGGER.debug("searching iphone in the search bar");
        driver.findElement(bySearchTabId).click();
        driver.findElement(bySearchTabId).sendKeys("iphone 13");
        driver.findElement(bySearchButton).click();
        LOGGER.debug("Have redirected to product listing page");
        return IphoneListScreen.get();
    }
}
