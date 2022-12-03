package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoDeliveryScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoPaymentScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IndigoDeliveryScreenAndroid extends IndigoDeliveryScreen {


    public static final By byPromoCodeTextboxXpath = By.xpath("//android.widget.EditText[@resource-id='PromoCode']");
    public static final By byPromoCodeApplyBtnXpath = By.xpath("//android.widget.Button[@resource-id='btnApplyPromoCode']");
    public static final By byReceiverNameXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Fname']");
    public static final By byReceiverLastNameXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Lname']");
    public static final By byReceiverMailXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_EmailID']");
    public static final By byReceiverPhoneXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Phone");
    public static final By bySenderNameXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Fname']");
    public static final By bySenderLastNameXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Lname']");
    public static final By bySenderEmailXpath = By.xpath("//android.widget.EditText[@resource-id='Per_EmailID']");
    public static final By bySenderPhoneXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Phone']");
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoDeliveryScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    public static final String NOT_YET_IMPLEMENTED = "not yet implemented";

    public IndigoDeliveryScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public IndigoDeliveryScreen enterInvalidPromoCode(String promoCode) {
        driver.scrollDownByScreenSize();
        driver.findElement(byPromoCodeTextboxXpath).clear();
        driver.findElement(byPromoCodeTextboxXpath).sendKeys(promoCode);
        LOGGER.info("Invalid Promo code entered in Delivery option page");
        driver.findElement(byPromoCodeApplyBtnXpath).click();
        driver.scrollDownByScreenSize();
        visually.checkWindow(SCREEN_NAME, "Invalid Promo code error message");
        return this;
    }

    @Override
    public String getErrorMessage() {
        String errorMessage =  driver.findElement(By.xpath("//android.view.View[contains(@text, 'Invalid')]")).getText();
        LOGGER.info("Invalid Promo Code error message " + errorMessage);
        return errorMessage;
    }

    @Override
    public int getFinalAmount() {
        driver.scrollDownByScreenSize();
        String amount = driver.findElement(By.xpath("//android.view.View[@resource-id='lblTotal']")).getText().substring(1).trim();
        LOGGER.info("Final amount after applying Promo Code " + amount);
        int finalAmountAfterPromoCode = Integer.parseInt(amount);
        return finalAmountAfterPromoCode;
    }

    @Override
    public IndigoDeliveryScreen enterReceiverDetail(String receiverFirstName, String receiverLastName, String receiverMail, String receiverPhone) {
        driver.findElement(byReceiverNameXpath).clear();
        driver.findElement(byReceiverNameXpath).sendKeys(receiverFirstName);
        driver.findElement(byReceiverLastNameXpath).clear();
        driver.findElement(byReceiverLastNameXpath).sendKeys(receiverLastName);
        driver.findElement(byReceiverMailXpath).clear();
        driver.findElement(byReceiverMailXpath).sendKeys(receiverMail);
        driver.findElement(byReceiverPhoneXpath).clear();
        driver.findElement(byReceiverPhoneXpath).sendKeys(receiverPhone);
        LOGGER.info("Reciever Details entered " + receiverFirstName + " " + receiverLastName + " " + receiverMail + " " + receiverPhone);
        visually.checkWindow(SCREEN_NAME, "Reciver details entered");
        return this;
    }

    @Override
    public IndigoDeliveryScreen enterSenderDetails(String senderFirstName, String senderLastName, String senderrMail, String senderPhone) {
        driver.scrollDownByScreenSize();
        driver.findElement(bySenderNameXpath).clear();
        driver.findElement(bySenderNameXpath).sendKeys(senderFirstName);
        driver.findElement(bySenderLastNameXpath).clear();
        driver.findElement(bySenderLastNameXpath).sendKeys(senderLastName);
        driver.findElement(bySenderEmailXpath).clear();
        driver.findElement(bySenderEmailXpath).sendKeys(senderrMail);
        driver.findElement(bySenderPhoneXpath).clear();
        driver.findElement(bySenderPhoneXpath).sendKeys(senderPhone);
        LOGGER.info("Sender Details entered " + senderFirstName + " " + senderLastName + " " + senderrMail + " " + senderPhone);
        visually.checkWindow(SCREEN_NAME, "Sender details entered");
        return this;
    }

    @Override
    public IndigoDeliveryScreen selectTermsAndConditions() {
        driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id='chkTnC']")).click();
        LOGGER.info("Terms and Condition CheckBox selected");
        return this;
    }

    @Override
    public IndigoPaymentScreen clickOnProceedBtn() {
        driver.findElement(By.xpath("//android.widget.Button[contains(@text, 'Pay Now')]")).click();

        return IndigoPaymentScreen.get();
    }
}
