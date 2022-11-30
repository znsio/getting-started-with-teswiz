package com.znsio.sample.e2e.screen.web.indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.PreviewVoucherScreen;
import com.znsio.sample.e2e.screen.web.theapp.AppLaunchScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class PreviewVoucherScreenWeb extends PreviewVoucherScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(PreviewVoucherScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = AppLaunchScreenWeb.class.getSimpleName();
    private final By byPreviewVoucherXpath = By.xpath("//h2");

    private final By byProceedButtonXpath = By.xpath("//input[@value='Proceed']");
    private final By byPromoCodeInputId = By.id("PromoCode");
    private final By byApplyButtonId = By.id("btnApplyPromoCode");

    private final By byErrorMessageXpath = By.xpath("//div[text()='Invalid Promo Code.']");
    private final By byRecFNameId = By.id("Rec_Fname");
    private final By byRecLNameId = By.id("Rec_Lname");
    private final By byRecEmailId = By.id("Rec_EmailID");
    private final By byRecPhoneId = By.id("Rec_Phone");
    private final By byPerFNameId = By.id("Per_Fname");
    private final By byPerLNameId = By.id("Per_Lname");
    private final By byPerEmailId = By.id("Per_EmailID");
    private final By byPerPhoneId = By.id("Per_Phone");
    private final By byTnCCheckBoxXpath = By.xpath("//input[@id='chkTnC']");

    private final By byPayNowBtnXpath = By.xpath("//input[@value='Pay Now']");

    private final By byOrderTotalXpath = By.xpath("//div[@id='amount']/span/strong");



    public PreviewVoucherScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Indigo Voucher Preview Screen");
    }


    @Override
    public String voucherDetails() {
        return driver.findElement(byPreviewVoucherXpath).getText();
    }

    @Override
    public String applyAndVerifyPromoCode() {
        driver.waitForClickabilityOf(byProceedButtonXpath).click();
        driver.findElement(byPromoCodeInputId).sendKeys("xsxxx");
        driver.findElement(byApplyButtonId).click();
        return driver.findElement(byErrorMessageXpath).getText();

    }

    @Override
    public String fillDetailsAndPay() {

        driver.findElement(byRecFNameId).sendKeys("RCFname");
        driver.findElement(byRecLNameId).sendKeys("RCLname");
        driver.findElement(byRecEmailId).sendKeys("RcEmail@xyz.com");
        driver.findElement(byRecPhoneId).sendKeys("9911000112");

        driver.findElement(byPerFNameId).sendKeys("");
        driver.findElement(byPerLNameId).sendKeys("SDLname");
        driver.findElement(byPerEmailId).sendKeys("SdEmail@xyz.com");
        driver.findElement(byPerPhoneId).sendKeys("9911000114");
        driver.findElement(byTnCCheckBoxXpath).click();
        driver.findElement(byPayNowBtnXpath).click();

        return driver.waitTillElementIsPresent(byOrderTotalXpath).getText();

    }
}
