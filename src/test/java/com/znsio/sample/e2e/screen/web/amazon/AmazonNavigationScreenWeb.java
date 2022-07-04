package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonNavigationScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonNavigationScreenWeb extends AmazonNavigationScreen {
    private static final String SCREEN_NAME = AmazonNavigationScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;

    private static final By bySearchTextBoxId =By.id("twotabsearchtextbox");
    private static final By bySearchIconId =By.id("nav-search-submit-button");
    private static final By byCartButtonId =By.id("nav-cart");

    public AmazonNavigationScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.driver = driver;
        this.visually = visually;
        this.context= Runner.getTestExecutionContext(threadId);
    }

    @Override
    public SearchResultScreen iSearchForProduct(String productName) {
        LOGGER.debug("Search for product");
        context.addTestState(SAMPLE_TEST_CONTEXT.PRODUCT_SEARCHED,productName);
        driver.findElement(bySearchTextBoxId).click();
        driver.findElement(bySearchTextBoxId).clear();
        driver.findElement(bySearchTextBoxId).sendKeys(productName);
        driver.findElement(bySearchIconId).click();
        return SearchResultScreen.get();
    }

    @Override
    public AmazonCartScreen iClickOnCartButton() {
        LOGGER.debug("Click on Amazon Cart");
        driver.findElement(byCartButtonId).click();
        return AmazonCartScreen.get();
    }
}
