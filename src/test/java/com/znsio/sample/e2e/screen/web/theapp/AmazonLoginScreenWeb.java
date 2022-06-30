package com.znsio.sample.e2e.screen.web.theapp;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.theapp.AmazonLoginScreen;
import com.znsio.sample.e2e.screen.theapp.AmazonResultsScreen;
import com.znsio.sample.e2e.screen.theapp.LoginScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.znsio.e2e.tools.Wait.waitFor;

public class AmazonLoginScreenWeb extends AmazonLoginScreen {

    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = AmazonLoginScreenWeb.class.getSimpleName();
    private final By UserEmail = By.id("ap_email");
    private final By Continue = By.id("continue");
    private final By passwordId = By.id("ap_password");
    private final By loginButton = By.id("signInSubmit");
    private final By LoginMessage = By.id("nav-link-accountList-nav-line-1");


    public AmazonLoginScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Home screen");
    }

    @Override
    public AmazonLoginScreenWeb AmazonEnterLoginDetails(String username, String password) {
        driver.findElement(UserEmail).sendKeys(username);
        driver.findElement(Continue).click();
        driver.findElement(passwordId).sendKeys(password);
        visually.takeScreenshot(SCREEN_NAME, "enterLoginDetails");
        return this;
    }

    @Override
    public AmazonResultsScreen login() {
        driver.findElement(loginButton).click();
        return AmazonResultsScreen.get();
    }

    @Override
    public String GetLoginMessage() {
        return driver.findElement(LoginMessage).getText();
    }


}
