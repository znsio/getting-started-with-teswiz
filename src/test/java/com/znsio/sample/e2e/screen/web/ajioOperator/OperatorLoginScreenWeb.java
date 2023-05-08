package com.znsio.sample.e2e.screen.web.ajioOperator;


import com.znsio.sample.e2e.screen.ajioOperator.OperatorLoginScreen;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorManageSellerScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;
import java.util.stream.IntStream;

public class OperatorLoginScreenWeb extends OperatorLoginScreen {

    private static final Logger LOGGER = Logger.getLogger(OperatorLoginScreenWeb.class.getName());
    private static final String SCREEN_NAME = OperatorLoginScreenWeb.class.getSimpleName();
    private final Visual visually;
    private Driver driver;
    private final By emailTxtBxById = By.id("email");
    private final By passwordTxtBoxById = By.id("password");
    private final By signButtonByXpath = By.xpath("//span[contains(text(),'Sign In')]");
    private final By ajioLogoByXpath = By.xpath("//img[@alt='logo']");


    public OperatorLoginScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
    @Override
    public boolean operatorOnLoginPage() {
        LOGGER.info("operator on login page");
        visually.checkWindow(SCREEN_NAME,"login page" );
        return driver.findElement(ajioLogoByXpath).isDisplayed();
    }

    @Override
    public OperatorManageSellerScreen loginToSellerCentralPage(String email, String password) {
        LOGGER.info("operator login with valid credential");
        WebElement emailTextFiled = driver.findElement(emailTxtBxById);
        cleaAndEnterInput(emailTextFiled,email);
        driver.findElement(passwordTxtBoxById).sendKeys(password);
        visually.checkWindow(SCREEN_NAME, "screen with credential");
        driver.findElement(signButtonByXpath).click();
        return OperatorManageSellerScreen.get();
    }

    @Override
    public boolean isLoginPageSignInDisplayed() {
        return driver.findElement(signButtonByXpath).isDisplayed();
    }

    public void cleaAndEnterInput(WebElement element,String input){
        String elementValue = element.getAttribute("value");
        IntStream.range(0,elementValue.length()).mapToObj(i-> Keys.BACK_SPACE).forEach(element::sendKeys);
        element.sendKeys(input);
    }
}
