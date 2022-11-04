package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomePageScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomePageScreenWeb extends AmazonHomePageScreen {
    public static final By byClickingOnSearchBoxid = By.id("twotabsearchtextbox");
    public static final By byClickOnSubmitId = By.id("nav-search-submit-button");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = AmazonHomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;



    public AmazonHomePageScreenWeb (Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public SearchResultPageScreen searchForiPhone13() {
        driver.findElement(byClickingOnSearchBoxid).click();
        driver.findElement(byClickingOnSearchBoxid).sendKeys("iphone13");
        driver.findElement(byClickOnSubmitId).click();
        LOGGER.info("On Search Rsult Page");
        visually.checkWindow(SCREEN_NAME,"On Amazon Search Result Page");
        return SearchResultPageScreen.get();
     //   return null;
    }


}
