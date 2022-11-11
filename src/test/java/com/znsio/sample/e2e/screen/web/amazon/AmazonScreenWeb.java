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
    public static final By validatingUsernameByXpath = By.xpath("//div[@class='a-row a-spacing-base']//span");
    public static final By validatingUsernameById = By.id("nav-link-accountList-nav-line-1");
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
    public boolean login(String username, String password) {
        driver.findElement(byClickOnAccounts).click();
        enterUsername(username);
        enterPassword(password);
        driver.waitTillElementIsPresent(validatingUsernameById, 10);
        visually.checkWindow(SCREEN_NAME, "Validating Amazon Homepage after Login");
        String validatingLogin = driver.findElement(validatingUsernameById).getText().trim();
        if (!validatingLogin.contains("Hello, sign in")) {
            LOGGER.info("Successfully logged in as:-" + validatingLogin);
            return true;
        } else {
            return false;
        }
    }

    public AmazonScreenWeb enterUsername(String username) {
        driver.findElement(byUsernameTextBoxid).sendKeys(username);
        driver.findElement(clickOnContinueButtonid).click();
        String validateUsername = driver.findElement(validatingUsernameByXpath).getText();
        LOGGER.info("Username on Login page:-" + validateUsername);
        return this;
    }

    public AmazonScreenWeb enterPassword(String password) {
        driver.findElement(byPasswordid).sendKeys(password);
        driver.findElement(byClickOnSignId).click();
        return this;
    }

}
