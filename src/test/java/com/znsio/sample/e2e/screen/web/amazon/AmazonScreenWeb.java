package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomePageScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonLoginScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AmazonScreenWeb extends AmazonLoginScreen {

    public static final By byUsernameTextBoxid = By.id("ap_email");
    public static final By clickOnContinueButtonid = By.id("continue");
    public static final By byPasswordid = By.id("ap_password");
    public static final By byClickOnSignId = By.id("signInSubmit");
    public static final By byClickOnAccounts = By.id("nav-link-accountList");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = AmazonScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;



    public AmazonScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public AmazonHomePageScreen login(String username, String password) {
        LOGGER.info("Clicking on Login button on Amazon");
        driver.findElement(byClickOnAccounts).click();
        visually.checkWindow(SCREEN_NAME,"On Amazon Login Screen");
        LOGGER.info("Clicking on Username Field");
        driver.findElement(byUsernameTextBoxid).sendKeys(username);
        driver.findElement(clickOnContinueButtonid).click();
        LOGGER.info("Clicking on Password Field");
        driver.findElement(byPasswordid).sendKeys(password);
        driver.findElement(byClickOnSignId).click();
        visually.checkWindow(SCREEN_NAME,"Successfully logged in");
        return AmazonHomePageScreen.get();
    }

}
