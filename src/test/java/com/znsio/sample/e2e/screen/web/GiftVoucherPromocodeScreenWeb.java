package com.znsio.sample.e2e.screen.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPromocodeScreen;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class GiftVoucherPromocodeScreenWeb extends GiftVoucherPromocodeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = GiftVoucherScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byPromocodeId = By.id("PromoCode");
    private final By byApplyButtonId = By.id("btnApplyPromoCode");
    private final By bySenderDetailsFirstNameId = By.id("Per_Fname");
    private final By bySenderDetailsLastNameId = By.id("Per_Lname");
    private final By bySenderEmailId = By.id("Per_EmailID");
    private final By bySenderPhoneId = By.id("Per_Phone");
    private final By byPayNowBtnXpath = By.xpath("//input[@class='preview-btn' and @value='Pay Now']");
    private final By byTermsNConditionsId = By.id("chkTnC");
    private final By byReceiverFirstNameId = By.id("Rec_Fname");
    private final By byReceiverLastNameId = By.id("Rec_Lname");
    private final By byReceiverEmailId = By.id("Rec_EmailID");
    private final By byReceiverPhoneId = By.id("Rec_Phone");
    private final By byAmountAfterPromocodeId = By.id("lblTotal");
    private final By byErrorMssgXpath = By.xpath("//div[contains(text(), 'Invalid Promo Code.')]");

    public GiftVoucherPromocodeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public GiftVoucherPromocodeScreen applyInvalidPromocode() {
        LOGGER.info("Applying Invalid promocode");
        visually.checkWindow(SCREEN_NAME,"Promocode Screen");
        driver.waitTillElementIsPresent(byPromocodeId);
        String promoCode = RandomStringUtils.randomAlphabetic(6);
        driver.findElement(byPromocodeId).sendKeys(promoCode);
        driver.findElement(byApplyButtonId).click();
        visually.takeScreenshot(SCREEN_NAME, "Entered invalid promocode");
        return this;
    }



    public String getAmountOnPromocodeScreen(){
        LOGGER.info("Amount After Applying Promocode");
        return driver.findElement(byAmountAfterPromocodeId).getText();
    }

    @Override
    public String getPromocodeErrMsg() {
        String errMsg = driver.findElement(byErrorMssgXpath).getText();
        visually.checkWindow(SCREEN_NAME, "Screen with invalid promocode");
        return errMsg;
    }


    @Override
    public GiftVoucherPromocodeScreenWeb enterDeliveryOptionDetails() {
        LOGGER.info("Entering the Sender details");
        visually.checkWindow(SCREEN_NAME,"Promocode Screen");
        String random = RandomStringUtils.randomAlphanumeric(10);
        String randomPhone = RandomStringUtils.randomNumeric(10);
        driver.scrollToBottom();
        driver.findElement(byReceiverFirstNameId).sendKeys(random);
        driver.findElement(byReceiverLastNameId).sendKeys(random);
        driver.findElement(byReceiverEmailId).sendKeys(random+"@gmail.com");
        driver.findElement(byReceiverPhoneId).sendKeys(randomPhone);
        driver.findElement(bySenderDetailsFirstNameId).sendKeys(random);
        driver.findElement(bySenderDetailsLastNameId).sendKeys(random);
        driver.findElement(bySenderEmailId).sendKeys(random+"@gmail.com");
        driver.findElement(bySenderPhoneId).sendKeys(randomPhone);
        driver.findElement(byTermsNConditionsId).click();
        driver.findElement(byPayNowBtnXpath).click();
        return this;
    }
}
