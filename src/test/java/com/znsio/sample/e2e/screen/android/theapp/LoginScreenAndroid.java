package com.znsio.sample.e2e.screen.android.theapp;

import com.applitools.eyes.appium.Target;
import com.znsio.sample.e2e.screen.theapp.LoginScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.znsio.teswiz.tools.Wait.waitFor;

public class LoginScreenAndroid
        extends LoginScreen {
    private static final String SCREEN_NAME = LoginScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;
    private final String byUserNameId = "username";
    private final String byPasswordId = "password";
    private final String byLoginButtonId = "loginBtn";
    private final By byErrorMessageId = By.id("android:id/message");
    private final By byDismissAlertId = By.id("android:id/button1");

    public LoginScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public LoginScreen enterLoginDetails(String username, String password) {
        waitFor(2);
        WebElement userNameElement = driver.findElementByAccessibilityId(byUserNameId);
        userNameElement.clear();
        userNameElement.sendKeys(username);
        WebElement passwordElement = driver.findElementByAccessibilityId(byPasswordId);
        passwordElement.clear();
        passwordElement.sendKeys(password);
        //        driver.waitForVisibilityOf(passwordId).sendKeys(username);
        visually.check(SCREEN_NAME, "entered login details",
                       Target.window().fully().layout(userNameElement, passwordElement));
        return this;
    }

    @Override
    public LoginScreen login() {
        waitFor(1);
        driver.findElementByAccessibilityId(byLoginButtonId).click();
        waitFor(2);
        return this;
    }

    @Override
    public String getInvalidLoginError() {
        WebElement alertText = driver.waitForClickabilityOf(byErrorMessageId);
        visually.checkWindow(SCREEN_NAME, "Invalid Login alert");
        return alertText.getText();
    }

    @Override
    public LoginScreen dismissAlert() {
        driver.waitForClickabilityOf(byDismissAlertId).click();
        waitFor(2);
        visually.checkWindow(SCREEN_NAME, "Invalid Login alert dismissed");
        return this;
    }
}
