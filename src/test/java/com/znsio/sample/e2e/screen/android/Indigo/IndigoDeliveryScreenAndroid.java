package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoDeliveryScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoPaymentScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IndigoDeliveryScreenAndroid extends IndigoDeliveryScreen {


    private static final By byPromoCodeTextboxXpath = By.xpath("//android.widget.EditText[@resource-id='PromoCode']");
    private static final By byPromoCodeApplyBtnXpath = By.xpath("//android.widget.Button[@resource-id='btnApplyPromoCode']");
    private static final By byReceiverNameXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Fname']");
    private static final By byReceiverLastNameXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Lname']");
    private static final By byReceiverMailXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_EmailID']");
    private static final By byReceiverPhoneXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Phone']");
    private static final By bySenderNameXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Fname']");
    private static final By bySenderLastNameXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Lname']");
    private static final By bySenderEmailXpath = By.xpath("//android.widget.EditText[@resource-id='Per_EmailID']");
    private static final By bySenderPhoneXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Phone']");
    private static final By byPayNowBtnXpath = By.xpath("//android.widget.Button[contains(@text,'Pay Now')]");
    private static final By byTermsConditionChkBoxXpath = By.xpath("//android.widget.CheckBox[@resource-id='chkTnC']");
    private static final By byErrorMessageXpath = By.xpath("//android.view.View[contains(@text, 'Invalid')]");
    private static final By byFinalAmountXpath = By.xpath("//android.view.View[@resource-id='lblTotal']");
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoDeliveryScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public IndigoDeliveryScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public IndigoDeliveryScreen enterInvalidPromoCode(String promoCode) {
        driver.scrollDownByScreenSize();
        driver.waitTillElementIsPresent(byPromoCodeTextboxXpath).clear();
        driver.findElement(byPromoCodeTextboxXpath).sendKeys(promoCode);
        LOGGER.info("Invalid Promo code entered in Delivery option page");
        driver.findElement(byPromoCodeApplyBtnXpath).click();
        driver.scrollDownByScreenSize();
        visually.checkWindow(SCREEN_NAME, "Invalid Promo code error message");
        return this;
    }

    @Override
    public String getErrorMessage() {
        String errorMessage =  driver.waitTillElementIsPresent(byErrorMessageXpath).getText();
        LOGGER.info("Invalid Promo Code error message " + errorMessage);
        return errorMessage;
    }

    @Override
    public int getFinalAmount() {
        driver.scrollDownByScreenSize();
        String amount = driver.waitTillElementIsPresent(byFinalAmountXpath).getText().substring(1).trim();
        LOGGER.info("Final amount after applying Promo Code " + amount);
        return Integer.parseInt(amount);
    }

    @Override
    public IndigoDeliveryScreen enterReceiverDetail(String receiverFirstName, String receiverLastName, String receiverMail, String receiverPhone) {
        driver.waitTillElementIsPresent(byReceiverNameXpath).clear();
        driver.findElement(byReceiverNameXpath).sendKeys(receiverFirstName);
        driver.waitTillElementIsPresent(byReceiverLastNameXpath).clear();
        driver.findElement(byReceiverLastNameXpath).sendKeys(receiverLastName);
        driver.waitTillElementIsPresent(byReceiverMailXpath).clear();
        driver.findElement(byReceiverMailXpath).sendKeys(receiverMail);
        driver.waitTillElementIsPresent(byReceiverPhoneXpath).clear();
        driver.findElement(byReceiverPhoneXpath).sendKeys(receiverPhone);
        LOGGER.info("Reciever Details entered " + receiverFirstName + " " + receiverLastName + " " + receiverMail + " " + receiverPhone);
        visually.checkWindow(SCREEN_NAME, "Reciver details entered");
        return this;
    }

    @Override
    public IndigoDeliveryScreen enterSenderDetails(String senderFirstName, String senderLastName, String senderrMail, String senderPhone) {
        driver.scrollDownByScreenSize();
        driver.waitTillElementIsPresent(bySenderNameXpath).clear();
        driver.findElement(bySenderNameXpath).sendKeys(senderFirstName);
        driver.waitTillElementIsPresent(bySenderLastNameXpath).clear();
        driver.findElement(bySenderLastNameXpath).sendKeys(senderLastName);
        driver.waitTillElementIsPresent(bySenderEmailXpath).clear();
        driver.findElement(bySenderEmailXpath).sendKeys(senderrMail);
        driver.waitTillElementIsPresent(bySenderPhoneXpath).clear();
        driver.findElement(bySenderPhoneXpath).sendKeys(senderPhone);
        LOGGER.info("Sender Details entered " + senderFirstName + " " + senderLastName + " " + senderrMail + " " + senderPhone);
        visually.checkWindow(SCREEN_NAME, "Sender details entered");
        return this;
    }

    @Override
    public IndigoDeliveryScreen selectTermsAndConditions() {
        driver.waitTillElementIsPresent(byTermsConditionChkBoxXpath).click();
        LOGGER.info("Terms and Condition CheckBox selected");
        return this;
    }

    @Override
    public IndigoPaymentScreen clickOnProceedBtn() {
        driver.scrollDownByScreenSize();
        driver.waitTillElementIsPresent(byPayNowBtnXpath).click();
        LOGGER.info("Procced Btn Clicked succesfully on Delivery Page");
        return IndigoPaymentScreen.get();
    }
}
