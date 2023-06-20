package com.znsio.sample.e2e.screen.web.theapp;

import com.znsio.sample.e2e.screen.theapp.LoginScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.znsio.teswiz.tools.Wait.waitFor;

public class LoginScreenWeb
        extends LoginScreen {
    private static final String SCREEN_NAME = LoginScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;
    private final By byUserNameId = By.id("username");
    private final By byPasswordId = By.id("password");
    private final By byLoginButtonXpath = By.xpath("//button/i[contains(text(),\"Login\")]");
    private final By byErrorMessageId = By.id("flash");

    public LoginScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home screen");
    }

    @Override
    public LoginScreen enterLoginDetails(String username, String password) {
        waitFor(2);
        driver.findElement(byUserNameId).sendKeys(username);
        driver.findElement(byPasswordId).sendKeys(password);
        visually.checkWindow(SCREEN_NAME, "entered login details");
        return this;
    }

    @Override
    public LoginScreen login() {
        driver.findElement(byLoginButtonXpath).click();
        waitFor(2);
        return this;
    }

    @Override
    public String getInvalidLoginError() {
        WebElement alertText = driver.waitForClickabilityOf(byErrorMessageId);
        visually.checkWindow(SCREEN_NAME, "Invalid Login alert");
        return alertText.getText().trim();
    }

    @Override
    public LoginScreen dismissAlert() {
        waitFor(2);
        visually.checkWindow(SCREEN_NAME, "Invalid Login alert dismissed");
        return this;
    }
}
