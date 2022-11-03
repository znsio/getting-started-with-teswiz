package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.IphoneSuccessfullyAddedToCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IphoneSuccessfullyAddedToCartScreenWeb extends IphoneSuccessfullyAddedToCartScreen {
    public static final By byCartButtonid = By.id("attach-sidesheet-view-cart-button");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = AmazonHomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;



    public IphoneSuccessfullyAddedToCartScreenWeb (Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public IphoneSuccessfullyAddedToCartScreen iphone13IsDisplayedOnCart() {
       driver.findElement(byCartButtonid).click();
       String textFromCartPage = driver.findElement(By.xpath("a-truncate-cut")).getText();
       if (textFromCartPage.contains("iPhone 13")) {
            LOGGER.info("iphone 13 successfully added to cart");
       }
        return this;
    }
}
