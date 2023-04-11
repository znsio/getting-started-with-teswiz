package com.znsio.sample.e2e.screen.web.ajioOperator;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorHomeScreen;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorLoginScreen;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class OperatorLoginWeb extends OperatorLoginScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = OperatorLoginWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By emailAddressTextboxbyXapth = By.xpath("//input[@class='jss82 jss67']");
    private final By passwordTextBoxByXpath = By.xpath("//input[@class='jss82 jss67 jss85 jss70']");

    private final By signButtonByXpath =By.xpath("//span[@class='jss90']");

    public OperatorLoginWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Login screen");
    }

    @Override
    public OperatorHomeScreen loginToSellerPage(String userName, String password) {
        driver.findElementByXpath("emailAddressTextboxbyXapth").sendKeys(userName);
        driver.findElementByXpath("passwordTextBoxByXpath").sendKeys(password);
        visually.checkWindow(SCREEN_NAME, "screen with credential");
        driver.findElementByXpath("signButtonByXpath").click();
        return OperatorHomeScreen.get();
    }

}
