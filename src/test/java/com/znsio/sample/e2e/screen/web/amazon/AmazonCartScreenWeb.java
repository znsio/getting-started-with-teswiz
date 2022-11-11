package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class AmazonCartScreenWeb extends AmazonCartScreen {
    public static final By byCartButtonid = By.cssSelector("input[aria-labelledby='attach-sidesheet-view-cart-button-announce']");
    public static final By byclickCartButtonOnCartPageXpath = By.xpath("(//span[@class='a-truncate-cut'])[1]");
    public static final By byCartPageId = By.id("sc-active-cart");


    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = AmazonCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    Map<String, String> testData = Runner.getTestDataAsMap(System.getProperty("user.name"));
    String product = testData.get("item");


    public AmazonCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public boolean iphone13IsDisplayedOnCart() {
        driver.waitTillElementIsPresent(byCartButtonid);
        driver.waitForClickabilityOf(byCartButtonid).click();
        driver.waitTillElementIsPresent(byCartPageId);
        String validatingCartPage = driver.findElement(byCartPageId).getText();
        LOGGER.info("Current Page:-" + validatingCartPage);
        String textFromCartPage = driver.findElement(byclickCartButtonOnCartPageXpath).getText();
        visually.checkWindow(SCREEN_NAME, "Validating Cart Page after selecting product");
        if (textFromCartPage.contains(product)) {
            LOGGER.info(product+ " successfully added to cart");
            return true;
        } else {
            LOGGER.info(product+ "not added to cart");
            return false;
        }
    }
}
