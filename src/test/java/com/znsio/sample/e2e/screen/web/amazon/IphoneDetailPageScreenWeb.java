package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.IphoneDetailPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

public class IphoneDetailPageScreenWeb extends IphoneDetailPageScreen {

    public static final By clickOnFirstIphone13 = By.xpath("(//span[contains(text(),'iPhone 13')])[4]");
    public static final By byAddToCartId = By.id("add-to-cart-button");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = AmazonHomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;



    public IphoneDetailPageScreenWeb (Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }


    @Override
    public IphoneDetailPageScreen selectFirstIphone() {
        driver.findElement(clickOnFirstIphone13).click();
        visually.checkWindow(SCREEN_NAME,"Iphone detail page opened");
        return IphoneDetailPageScreen.get();
    }

    @Override
    public IphoneDetailPageScreen addIphoneToCart() {
        driver.switchToNextTab();
   //     driver.findElement(byAddToCartId).click();
        driver.waitForClickabilityOf(byAddToCartId).click();
        return IphoneDetailPageScreen.get();
    }
}
