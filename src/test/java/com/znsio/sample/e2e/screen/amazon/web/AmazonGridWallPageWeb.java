package com.znsio.sample.e2e.screen.amazon.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonGridWallPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonGridWallPageWeb extends AmazonGridWallPageScreen {
    private final TestExecutionContext context;
    private final Visual visually;
    private final Driver driver;
    public static final String SCREEN_NAME = AmazonGridWallPageWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By header_Results = By.xpath("//span[text()='RESULTS']");
    private final By list_SearchResults = By.xpath("//div[@data-component-type='s-search-result']//h2//a//span");

    public AmazonGridWallPageWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public Boolean checkForResultsHeader() {
        Boolean isText_RESULTS_IsPresent=false;
        String headerText = driver.waitTillElementIsPresent(header_Results,10)
                .getText()
                .trim();
        if (headerText.equals("RESULTS")) {
            isText_RESULTS_IsPresent = true;
        }
        return isText_RESULTS_IsPresent;
    }

    @Override
    public Boolean checkForProductResultsSize() {
        Boolean isResultsSizeIsMoreThanOne = false;
        int resultsSize = driver.findElements(list_SearchResults).size();
        if (resultsSize>1) {
            isResultsSizeIsMoreThanOne = true;
        }
        return isResultsSizeIsMoreThanOne;
    }

    @Override
    public Boolean selectProduct() {
        String productToBeSelected = driver.findElements(list_SearchResults).get(0).getText();
        LOGGER.info(String.format("ProductDesc: "+productToBeSelected+""));
        context.addTestState(SAMPLE_TEST_CONTEXT.AMAZON_PRODUCTDESC,productToBeSelected);
        driver.findElements(list_SearchResults).get(0).click();
        visually.takeScreenshot(SCREEN_NAME, "After Selecting the product from the serach results");
        return true;
    }

}
