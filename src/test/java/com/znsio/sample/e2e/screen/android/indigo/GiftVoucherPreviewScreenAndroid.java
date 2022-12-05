package com.znsio.sample.e2e.screen.android.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPreviewScreen;
import com.znsio.sample.e2e.screen.indigo.PaymentDetailsScreen;
import com.znsio.sample.e2e.screen.web.indigo.PaymentDetailsScreenWeb;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Map;

public class GiftVoucherPreviewScreenAndroid
        extends GiftVoucherPreviewScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = GiftVoucherPreviewScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    private final By bySelectDenominationXpath = By.xpath("//android.view.View[@resource-id='SelectedVoucherValue']");
    private final By byQuantityXpath = By.xpath("//android.view.View[@resource-id='SelectedVoucherQuantity']");
    private final By byTotalTfXpath = By.xpath("//android.view.View[@resource-id='lblTotal']");
    private final By byMakeItPersonalTfXpath = By.xpath("//android.widget.CheckBox[@text, 'chkPersonal']");
    private final By byDearTfXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Fname']");
    private final By byMessageTfXpath = By.xpath("//android.widget.EditText[@resource-id='Message']");
    private final By byPreviewBtnXpath = By.xpath("//android.widget.Button[@text='Preview']");
    private final By byProceedBtnXpath = By.xpath("//android.widget.Button[@text='Proceed']");
    private final By byPromoCodeTfXpath = By.xpath("//android.widget.EditText[@resource-id='PromoCode']");
    private final By byApplyBtnXpath = By.xpath("//android.widget.Button[@resource-id='btnApplyPromoCode']");
    private final By byInvalidPromoCodeErrorXpath = By.xpath("//android.view.View[@text='Invalid Promo Code.']");
    private final By byPaymentAmountXpath = By.xpath("//android.view.View[@resource-id='lblTotal']");
    private final By byRecieverFirstNameTfXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Fname']");
    private final By byRecieverLastNameTfXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Lname']");
    private final By byRecieverEmailTfXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_EmailID']");
    private final By byRecieverPhoneTfXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Phone']");
    private final By bySenderFirstNameTfXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Fname']");
    private final By bySenderLastNameTfXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Lname']");
    private final By bySenderEmailTfXpath = By.xpath("//android.widget.EditText[@resource-id='Per_EmailID']");
    private final By bySenderPhoneTfXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Phone']");
    private final By byPayNowBtnXpath = By.xpath("//android.widget.Button[@text='Pay Now']");
    private final By byTermsAndConditionCbXpath = By.xpath("//android.widget.CheckBox[@resource-id='chkTnC']");

    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Map userDetails;
    private final String userSuffix;


    public GiftVoucherPreviewScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);

        userSuffix=System.getProperty("user.name");
        userDetails = Runner.getTestDataAsMap(userSuffix);
    }

    @Override
    public int setAndFetchTheAmountBasedOnDenominationAnQuantity(String denomination, String qnty) {
        driver.waitTillElementIsPresent(bySelectDenominationXpath,15).click();
        visually.takeScreenshot(SCREEN_NAME,"Gift Voucher Screen");
        LOGGER.info("Clicked on Select Denomination dropdown");
        driver.waitTillElementIsPresent(By.xpath("//android.widget.CheckedTextView[@text='"+denomination+"']")).click();
        LOGGER.info("Selected " + denomination + " from Select Denomination dropdown");

        driver.scrollDownByScreenSize();
        driver.waitForClickabilityOf(byQuantityXpath).click();
        LOGGER.info("Clicked on Quantity dropdown");
        driver.waitTillElementIsPresent(By.xpath("//android.widget.CheckedTextView[@text='"+qnty+"']")).click();
        LOGGER.info("Selected " + qnty + " from Quantity dropdown");

        driver.scrollDownByScreenSize();
        int totalAmount=Integer.parseInt(driver.findElement(byTotalTfXpath).getText().substring(1).trim());
        return totalAmount;
    }

    @Override
    public GiftVoucherPreviewScreen personalizeGiftVoucher() {
        String message= RandomStringUtils.randomAlphabetic(8);
        driver.waitForClickabilityOf(byMakeItPersonalTfXpath).click();
        visually.takeScreenshot(SCREEN_NAME,"Personalizing Gift Voucher");
        LOGGER.info("Clicked on Make It Personal Check Box");
        driver.waitForClickabilityOf(byDearTfXpath).clear();
        driver.waitTillElementIsPresent(byDearTfXpath).sendKeys(userSuffix);
        LOGGER.info("Entered "+userSuffix+" in Dear Text field");
        context.addTestState(SAMPLE_TEST_CONTEXT.NAME, userSuffix);

        driver.scrollDownByScreenSize();
        driver.waitForClickabilityOf(byMessageTfXpath).clear();
        driver.waitTillElementIsPresent(byMessageTfXpath).sendKeys(message);
        LOGGER.info("Entered "+message+" in Message Text field");
        context.addTestState(SAMPLE_TEST_CONTEXT.MESSAGE,message);

        return this;
    }

    @Override
    public GiftVoucherPreviewScreen clickOnPreview() {
        driver.scrollDownByScreenSize();
        driver.waitForClickabilityOf(byPreviewBtnXpath).click();
        LOGGER.info("Clicked on Preview button");
        return this;
    }

    @Override
    public boolean checkPersonalizationOfGiftVoucher() {
        driver.scrollDownByScreenSize();
        visually.takeScreenshot(SCREEN_NAME,"Check Personalization of Gift Voucher");
        return (driver.waitTillElementIsVisible(By.xpath("//android.view.View[contains(@text,'"+String.valueOf(context.getTestState(SAMPLE_TEST_CONTEXT.NAME))+"')]"),3).isDisplayed() && driver.waitTillElementIsVisible
                (By.xpath("//android.view.View[contains(@text,'"+String.valueOf(context.getTestState(SAMPLE_TEST_CONTEXT.MESSAGE))+"')]"),3).isDisplayed());
    }

    @Override
    public GiftVoucherPreviewScreen clickOnProceed() {
        driver.scrollDownByScreenSize();
        driver.waitForClickabilityOf(byProceedBtnXpath).click();
        LOGGER.info("Clicked on Proceed button");
        return this;
    }

    @Override
    public GiftVoucherPreviewScreen applyInvalidPromocode() {
        String promoCode=RandomStringUtils.randomAlphanumeric(6);
        driver.scrollDownByScreenSize();
        visually.takeScreenshot(SCREEN_NAME,"Apply promo code");
        driver.findElement(byPromoCodeTfXpath).clear();
        driver.findElement(byPromoCodeTfXpath).sendKeys(promoCode);
        LOGGER.info("Entered "+promoCode+" Promo code");
        driver.waitForClickabilityOf(byApplyBtnXpath).click();

        return this;
    }

    @Override
    public String fetchInvalidPromoCodeMessage() {
        LOGGER.info("Fetching Invalid Promo Code message");
        driver.scrollDownByScreenSize();
        visually.takeScreenshot(SCREEN_NAME,"Check invalid promo code message");
        return driver.waitTillElementIsPresent(byInvalidPromoCodeErrorXpath,10).getText();
    }

    @Override
    public int fetchTotalAmountAfterApplyingThePromoCode() {
        LOGGER.info("Fetching Total payment amount after applying promo code");
        driver.scrollDownByScreenSize();
        return Integer.parseInt(driver.findElement(byPaymentAmountXpath).getText().substring(1).trim());
    }

    @Override
    public GiftVoucherPreviewScreen setDeliveryOptions() {
        driver.scrollDownByScreenSize();
        visually.takeScreenshot(SCREEN_NAME,"Set receiver delivery details");
        driver.waitTillElementIsVisible(byRecieverFirstNameTfXpath,2).clear();
        String receiverFirstName=RandomStringUtils.randomAlphabetic(8);
        driver.findElement(byRecieverFirstNameTfXpath).sendKeys(receiverFirstName);
        LOGGER.info("Entered "+receiverFirstName+" into Receiver First name text field");

        driver.waitTillElementIsVisible(byRecieverLastNameTfXpath,2).clear();
        String receiverLastName=RandomStringUtils.randomAlphabetic(5);
        driver.findElement(byRecieverLastNameTfXpath).sendKeys(receiverLastName);
        LOGGER.info("Entered "+receiverLastName+" into Receiver Last name text field");

        driver.waitTillElementIsVisible(byRecieverEmailTfXpath,2).clear();
        String receiverEmail=RandomStringUtils.randomAlphabetic(8)+"@gmail.com";
        driver.findElement(byRecieverEmailTfXpath).sendKeys(receiverEmail);
        LOGGER.info("Entered "+receiverEmail+" into Receiver Email text field");

        driver.waitTillElementIsVisible(byRecieverPhoneTfXpath,2).clear();
        String receiverMobileNumber=RandomStringUtils.randomNumeric(10);
        driver.findElement(byRecieverPhoneTfXpath).sendKeys(receiverMobileNumber);
        LOGGER.info("Entered "+receiverMobileNumber+" into Receiver Mobile number text field");

        driver.scrollDownByScreenSize();
        driver.waitForClickabilityOf(byTermsAndConditionCbXpath).click();

        visually.takeScreenshot(SCREEN_NAME,"Set sender delivery details");
        driver.waitTillElementIsVisible(bySenderFirstNameTfXpath,2).clear();
        String senderFirstName=RandomStringUtils.randomAlphabetic(8);
        driver.findElement(bySenderFirstNameTfXpath).sendKeys(senderFirstName);
        LOGGER.info("Entered "+senderFirstName+" into Sender First name text field");

        driver.waitTillElementIsVisible(bySenderLastNameTfXpath,2).clear();
        String senderLastName=RandomStringUtils.randomAlphabetic(5);
        driver.findElement(bySenderLastNameTfXpath).sendKeys(senderLastName);
        LOGGER.info("Entered "+senderLastName+" into Sender Last name text field");

        driver.waitTillElementIsVisible(bySenderEmailTfXpath,2).clear();
        String senderEmail=RandomStringUtils.randomAlphabetic(8)+"@gmail.com";
        driver.findElement(bySenderEmailTfXpath).sendKeys(senderEmail);
        LOGGER.info("Entered "+senderEmail+" into Sender Email text field");

        driver.waitTillElementIsVisible(bySenderPhoneTfXpath,2).clear();
        String senderMobileNumber=RandomStringUtils.randomNumeric(10);
        driver.findElement(bySenderPhoneTfXpath).sendKeys(senderMobileNumber);
        LOGGER.info("Entered "+senderMobileNumber+" into Sender Mobile number text field");

        return this;
    }

    @Override
    public PaymentDetailsScreen clickOnPayNow() {
        driver.scrollDownByScreenSize();
        driver.waitForClickabilityOf(byPayNowBtnXpath).click();
        LOGGER.info("Clicked on Pay Now Button");
        return PaymentDetailsScreen.get();
    }
}
