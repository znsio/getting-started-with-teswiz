package com.znsio.sample.e2e.screen.web.ajioOperator;

import com.applitools.eyes.selenium.fluent.Target;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorLoginScreen;
import com.znsio.sample.e2e.screen.ajioOperator.OperatorManageSellerScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OperatorManageSellerScreenWeb extends OperatorManageSellerScreen {
    private static final Logger LOGGER = Logger.getLogger(OperatorManageSellerScreenWeb.class.getName());
    private static final String SCREEN_NAME = OperatorManageSellerScreenWeb.class.getSimpleName();
    private final Visual visually;
    private Driver driver;
    private static final By profileBtnByXpath = By.xpath("//p[contains(@title,'.com')]");
    private static final By logoutBtnByXpath = By.xpath("//div[@class='jss455 jss199']");
    private static final String  actionsItemBtnByXpath = "//li[text()='%s']";
    private static final By actionsMenuByXpath = By.xpath("//div[@class='rt-tr-group']//button");
    private static final By statusFilterBtnByXpath = By.xpath("//div[@class='rt-thead -filters']//div[@role='button']");
    private static final String pendingStatusBtnByXpath = "//li[@data-value='[object Object]']//span[text()='%s']";
    private static final By firstSellerMailByXpath = By.xpath("//div[@role='rowgroup'][1]//div[contains(text(),'@autotest.com')]");
    private static final By emailTxtByXpath = By.xpath("(//input[@type='text'])[2]");
    private static final By managerSellerScreenTitleByXapth = By.xpath("//h6[contains(text(),'Manage Sellers')]");
    private static final By searchFilterFieldByXpath = By.xpath("//div[@class='rt-tr-group'][1]");
    private final By sellerDataByCss = By.cssSelector(".rt-tr-group");
    private static final String  SellerEmailTextByXpath = "//div[text()='%s']";
    private static final String  SellerStatusTextByXpath = "//div[text()='%s']";

    public OperatorManageSellerScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public OperatorLoginScreen logOutFromHomePage() {
        LOGGER.info("Operator on Home Page");
        driver.waitTillElementIsPresent(profileBtnByXpath).click();
        visually.checkWindow(SCREEN_NAME, "Operator on profile button to logout");
        driver.waitTillElementIsPresent(logoutBtnByXpath, 5).click();
        return OperatorLoginScreen.get();
    }

    @Override
    public OperatorManageSellerScreen searchSellerEmailAddress() {
        driver.waitTillElementIsPresent(firstSellerMailByXpath,15);
        String sellerMailAddress = driver.waitTillElementIsVisible(firstSellerMailByXpath, 5).getText();
        driver.waitTillElementIsPresent(emailTxtByXpath).sendKeys(sellerMailAddress);
        visually.checkWindow(SCREEN_NAME, "search result for seller email");
        return this;
    }

    @Override
    public OperatorManageSellerScreen verifyTheSellerDetails() {
        LOGGER.info("Search result for seller's email");
        visually.check(SCREEN_NAME, "search result for seller by email ",
                Target.region(searchFilterFieldByXpath).fully().layout(driver.findElement(searchFilterFieldByXpath)));
        return this;
    }

    @Override
    public boolean isManageSellerTitleDisplayed() {
        return driver.waitTillElementIsPresent(managerSellerScreenTitleByXapth, 10).isDisplayed();
    }

    @Override
    public boolean isSearchFilterFieldDisplayed() {
        return driver.waitTillElementIsPresent(searchFilterFieldByXpath, 10).isDisplayed();
    }

    @Override
    public boolean VerifySellerRegistrationAndApprovalStatus(String actionItems) {
        driver.waitTillElementIsPresent(actionsMenuByXpath).click();
        visually.checkWindow(SCREEN_NAME,"SellerRegistration and ApprovalStatus is displayed");
        By actionsItem = By.xpath(String.format(actionsItemBtnByXpath,actionItems));
        return driver.waitTillElementIsPresent(actionsItem).isDisplayed();
    }

    @Override
    public boolean statusFilterIsEnabledOnPendingStatus(String statusItems) {
        driver.waitTillElementIsPresent(statusFilterBtnByXpath, 10).click();
        By pendingStatusBtn = By.xpath(String.format(pendingStatusBtnByXpath, statusItems));
        driver.waitTillElementIsPresent(pendingStatusBtn,5).click();
        LOGGER.info(String.format("%s is enabled on status Filter",statusItems));
        visually.checkWindow(SCREEN_NAME, String.format("%s is enabled on status Filter",statusItems));
        return driver.findElement(statusFilterBtnByXpath).getText().equals(statusItems);
    }

    @Override
    public OperatorManageSellerScreen verifyThePreRegisterSellerStatusOnManageSeller(String preRegisterSellerMail,String sellerStatus)  {
       driver.waitTillPresenceOfAllElements(sellerDataByCss);
        List<WebElement> sellerDataElements =  driver.findElements(sellerDataByCss);
        LOGGER.info(String.format("checking the new preRegister seller by email: %s on manage seller screen",preRegisterSellerMail));
        for(WebElement preRegisterSellerDataElement :sellerDataElements){
            preRegisterSellerDataElement.findElement(By.xpath(String.format(SellerEmailTextByXpath,preRegisterSellerMail)));
            preRegisterSellerDataElement.findElement(By.xpath(String.format(SellerStatusTextByXpath,sellerStatus))).isDisplayed();
        }
        return this;
    }
}

