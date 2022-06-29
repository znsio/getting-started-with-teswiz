package com.znsio.sample.e2e.screen.web.AmazonCartWeb;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.AmazonCart.AmazonScreen;
import com.znsio.sample.e2e.screen.AmazonCart.SearchResultsScreen;
import com.znsio.sample.e2e.screen.web.theapp.AppLaunchScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonScreenWeb extends AmazonScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(AmazonScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private final String SCREEN_NAME = AmazonScreenWeb.class.getSimpleName();
    private final By status = By.xpath("//span[contains(text(),'Hello, Sign in')]");
    private final By addItemIntoSearchbox = By.xpath("//input[@id='twotabsearchtextbox']");
    private final By searchButton = By.xpath("//input[@id='nav-search-submit-button']");

    private final By cartNo = By.id("nav-cart-count");


    public AmazonScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.driver = driver;
        this.visually = visually;
        this.context = Runner.getTestExecutionContext(threadId);
        visually.takeScreenshot(SCREEN_NAME, "Amazon Home screen");
    }

    @Override
    public String getSignInStatus(){
        String cartItem = driver.findElement(cartNo).getText();
        context.addTestState("CartValue",cartItem);
        String signInMessage = driver.findElement(status).getText();
        return signInMessage;
    }


    @Override
    public SearchResultsScreen searchTheProduct(String productName){
        driver.findElement(addItemIntoSearchbox).sendKeys(productName);
        driver.findElement(searchButton).click();
        return SearchResultsScreen.get();
    }
}
