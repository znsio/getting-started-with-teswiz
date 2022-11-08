package com.znsio.sample.e2e.screen.web.amazonWeb;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonLogInScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonLogInScreenWeb extends AmazonLogInScreen {
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = AmazonLogInScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final TestExecutionContext context;
    private final By byUserNameId = By.id("ap_email");
    private final By byContinueButton = By.id("continue");
    private final By byPasswordId = By.id("ap_password");
    public static final By byClickOnSignIn = By.id("signInSubmit");

    public AmazonLogInScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public AmazonHomeScreen isLoginToAmazonHomePage(){
        driver.findElement(byUserNameId).click();
        LOGGER.debug("Enter username");
        driver.findElement(byUserNameId).sendKeys(SAMPLE_TEST_CONTEXT.USER_NAME);
        driver.findElement(byContinueButton).click();
        LOGGER.debug("Enter password");
        driver.findElement(byPasswordId).sendKeys(SAMPLE_TEST_CONTEXT.PASSWORD);
        driver.findElement(byClickOnSignIn).click();
        LOGGER.debug("Have successfully login to amazon");
        return AmazonHomeScreen.get();
    }
}
