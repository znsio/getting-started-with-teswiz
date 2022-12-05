package com.znsio.sample.e2e.screen.web.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPreviewScreen;
import com.znsio.sample.e2e.screen.indigo.PaymentDetailsScreen;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class GiftVoucherPreviewScreenWeb extends GiftVoucherPreviewScreen {

    private static final Logger LOGGER = Logger.getLogger(GiftVoucherPreviewScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = HomeScreenWeb.class.getSimpleName();
    private final WebDriver innerDriver;
    private final TestExecutionContext context;
    private static final By bySelectDenominationDdXpath = By.id("SelectedVoucherValue");
    private static final By bySelectQuantityDdXpath = By.id("SelectedVoucherQuantity");
    private static final By byAmountTfXpath = By.id("lblTotal");
    private static final By byPreviewBtnXpath = By.xpath("//input[@class='preview-btn']");
    private static final By byProceedBtnXpath = By.xpath("//input[@value='Proceed']");

    private static final By byPromoCodeTfXpath = By.id("PromoCode");

    private static final By byApplyBtnXpath = By.id("btnApplyPromoCode");
    private static final By byRecieverFirstNameTfXpath = By.id("Rec_Fname");
    private static final By byRecieverLastNameTfXpath = By.id("Rec_Lname");
    private static final By byRecieverEmailTfXpath = By.id("Rec_EmailID");
    private static final By byRecieverPhoneTfXpath = By.id("Rec_Phone");
    private static final By bySenderFirstNameTfXpath = By.id("Per_Fname");
    private static final By bySenderLastNameTfXpath = By.id("Per_Lname");
    private static final By bySenderEmailTfXpath = By.id("Per_EmailID");
    private static final By bySenderPhoneTfXpath = By.id("Per_Phone");
    private static final By byInvalidPromoCodeTxtXpath = By.xpath("//div[contains(text(),'Invalid Promo Code')]");
    private static final By byPayNowBtnXpath = By.xpath("//input[@class='preview-btn']");
    private static final By byTermsAndConditionsCbXpath = By.id("chkTnC");
    private static final By byTotalAmountAfterPromoCodeXpath = By.xpath("//label[text()='Payment Amount']/following-sibling::span[@id='lblTotal']");
    private static final By byMakeItPersonalCbXpath = By.id("chkPersonal");
    private static final By byDearTfXpath = By.id("Per_Fname");
    private static final By byMessageTfXpath = By.id("Message");
    private static final By byMessageTextXpath = By.xpath("//div[@class='doted-line']/preceding-sibling::p");
    private static final By byTitleTextXpath = By.xpath("//div[@class='title-name']/span");
    private final Map userDetails;
    private final String userSuffix;

    public GiftVoucherPreviewScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
         userSuffix=System.getProperty("user.name");
         userDetails = Runner.getTestDataAsMap(userSuffix);
    }

    @Override
    public int setAndFetchTheAmountBasedOnDenominationAnQuantity(String denomination,String qnty) {
        visually.takeScreenshot(SCREEN_NAME,"Gift Voucher Page");
        driver.waitTillElementIsPresent(bySelectDenominationDdXpath);
        Select sl = new Select(driver.findElement(bySelectDenominationDdXpath));
        sl.selectByValue(denomination);
        LOGGER.info("Selected " + denomination + " from Denomination dropdown");

        Select sl1 = new Select(driver.findElement(bySelectQuantityDdXpath));
        sl1.selectByValue(qnty);
        LOGGER.info("Selected " + qnty + " from Quantity dropdown");

        int totalAmount=Integer.parseInt(driver.findElement(byAmountTfXpath).getText().substring(1).trim());
        return totalAmount;

    }

    @Override
    public GiftVoucherPreviewScreen personalizeGiftVoucher() {
        String message= RandomStringUtils.randomAlphabetic(8);
        driver.waitForClickabilityOf(byMakeItPersonalCbXpath).click();
        LOGGER.info("Clicked on Make It Personal Check Box");
        driver.waitForClickabilityOf(byDearTfXpath).click();
        driver.waitForClickabilityOf(byDearTfXpath).clear();
        driver.waitTillElementIsPresent(byDearTfXpath).sendKeys(userSuffix);
        LOGGER.info("Entered "+userSuffix+" in Dear Text field");
        context.addTestState(SAMPLE_TEST_CONTEXT.NAME, userSuffix);


        driver.scrollTillElementIntoView(byMessageTfXpath);
        driver.waitForClickabilityOf(byMessageTfXpath).click();
        driver.waitForClickabilityOf(byMessageTfXpath).clear();
        driver.waitTillElementIsPresent(byMessageTfXpath).sendKeys(message);
        LOGGER.info("Entered "+message+" in Message Text field");
        context.addTestState(SAMPLE_TEST_CONTEXT.MESSAGE,message);

        return this;
    }

    @Override
    public GiftVoucherPreviewScreen clickOnPreview() {
        driver.waitForClickabilityOf(byPreviewBtnXpath).click();
        LOGGER.info("Clicked on Preview button");
      return this;
    }

    @Override
    public boolean checkPersonalizationOfGiftVoucher() {
        visually.takeScreenshot(SCREEN_NAME,"Gift Voucher Preview Page");
        String title=driver.waitTillElementIsVisible(byTitleTextXpath,3).getText().replace(",","");
        String message=driver.waitTillElementIsVisible(byMessageTextXpath,3).getText();

        return (title.equalsIgnoreCase(String.valueOf(context.getTestState(SAMPLE_TEST_CONTEXT.NAME)))&&
                message.equalsIgnoreCase(String.valueOf(context.getTestState(SAMPLE_TEST_CONTEXT.MESSAGE))));
    }

    @Override
    public GiftVoucherPreviewScreen clickOnProceed() {
        driver.waitForClickabilityOf(byProceedBtnXpath).click();
        LOGGER.info("Clicked on Proceed button");
        return this;
    }

    @Override
    public GiftVoucherPreviewScreen applyInvalidPromocode() {
        String promoCode=RandomStringUtils.randomAlphanumeric(6);
        driver.waitForClickabilityOf(byPromoCodeTfXpath).click();
        visually.takeScreenshot(SCREEN_NAME,"Apply promo code");
        driver.findElement(byPromoCodeTfXpath).clear();
        driver.findElement(byPromoCodeTfXpath).sendKeys(promoCode);
        driver.waitForClickabilityOf(byApplyBtnXpath).click();

        return this;
    }

    @Override
    public String fetchInvalidPromoCodeMessage() {
      return driver.waitTillElementIsVisible(byInvalidPromoCodeTxtXpath,3).getText();
    }

    @Override
    public int fetchTotalAmountAfterApplyingThePromoCode() {
        return Integer.parseInt(driver.findElement(byTotalAmountAfterPromoCodeXpath).getText().substring(1).trim());
    }

    @Override
    public GiftVoucherPreviewScreen setDeliveryOptions() {

        visually.takeScreenshot(SCREEN_NAME,"Set delivery details");
        driver.waitForClickabilityOf(byRecieverFirstNameTfXpath).click();

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

        driver.waitForClickabilityOf(bySenderFirstNameTfXpath).click();
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

        driver.waitForClickabilityOf(byTermsAndConditionsCbXpath).click();

        return this;
    }

    @Override
    public PaymentDetailsScreen clickOnPayNow() {
        driver.waitForClickabilityOf(byPayNowBtnXpath).click();
        LOGGER.info("Clicked on Pay Now Button");
        return new PaymentDetailsScreenWeb(driver,visually);
    }
}
