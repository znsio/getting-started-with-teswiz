package com.znsio.sample.e2e.screen.web.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.IndigoVoucherDeliveryDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IndigoVoucherDeliveryDetailsWebScreen extends IndigoVoucherDeliveryDetailsScreen {

    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = IndigoVoucherDeliveryDetailsWebScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private final By byPromoCodeTxtBoxId = By.id("PromoCode");
    private final By byApplyPCBtnId = By.id("btnApplyPromoCode");
    private final By byCheckBoxEmailSenderXpath = By.xpath("//label[contains(text(),'Email it to the sender')]/.");
    private final By bySenderFNameTxtBoxId = By.id("Per_Fname");
    private final By bySenderLNameTxtBoxId = By.id("Per_Lname");
    private final By bySenderEmailTxtBoxId = By.id("Per_EmailID");
    private final By bySenderPhonetxtBoxId = By.id("Per_Phone");
    private static final By byErrorMessageXpath = By.xpath("//div[contains(text(),'Invalid')]");
    private static final By byFinalAmountXpath = By.xpath("//label[contains(text(),'Payment')]/following-sibling::span");
    private static final By byTermsConditionChkBoxId = By.id("chkTnC");
    private final By byPayNowBtnXpath = By.xpath("//input[@value='Pay Now']");
    private static final By byPaymentDetailsId = By.id("paymentinformation");

    public IndigoVoucherDeliveryDetailsWebScreen(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    public IndigoVoucherDeliveryDetailsScreen enterInvalidPromoCode(String promoCode)
    {
        WebElement promoCodeTxtbox = driver.findElement(byPromoCodeTxtBoxId);
        promoCodeTxtbox.clear();
        promoCodeTxtbox.sendKeys(promoCode);
        driver.findElement(byApplyPCBtnId).click();
        LOGGER.info("Invalid promo code applied");
        visually.checkWindow(SCREEN_NAME,"Invalid promo code entered");
        return this;
    }

    public String getInvalidVoucherMessage()
    {
        String invalidVoucherMessage = driver.findElement(byErrorMessageXpath).getText();
        LOGGER.info("Invalid Promo code error message is "+ invalidVoucherMessage);
        return invalidVoucherMessage;
    }

    public String fetchFinalAmount()
    {
        String amount = driver.findElement(byFinalAmountXpath).getText();
        LOGGER.info("Final amount after applying Promo Code " + amount);
        String finalAmountAfterPromoCode = amount.substring(1).trim();
        return finalAmountAfterPromoCode;
    }

    public IndigoVoucherDeliveryDetailsScreen enterSenderDetails(String senderFName, String senderLName, String senderMail, String senderPhone)
    {

        LOGGER.info("Email sender checkBox selected");
        driver.findElement(bySenderFNameTxtBoxId).clear();
        driver.findElement(bySenderFNameTxtBoxId).sendKeys(senderFName);
        LOGGER.info("Entered sender first name is "+ senderFName);
        driver.findElement(bySenderLNameTxtBoxId).sendKeys(senderLName);
        LOGGER.info("Entered sender last name is "+ senderLName);
        driver.findElement(bySenderEmailTxtBoxId).sendKeys(senderMail);
        LOGGER.info("Entered sender email is "+ senderMail);
        driver.findElement(bySenderPhonetxtBoxId).sendKeys(senderPhone);
        LOGGER.info("Entered sender phone number is "+ senderPhone);
        visually.checkWindow(SCREEN_NAME, "Sender details entered successfully");
        driver.findElement(byCheckBoxEmailSenderXpath).click();
        LOGGER.info("Clicked sender SEND EMAIL TO SENDER checkbox successfully");
        return this;
    }

    public IndigoVoucherDeliveryDetailsScreen checkTermsAndConditions()
    {
        LOGGER.info("Checking Terms and Condition CheckBox");
        driver.findElement(byTermsConditionChkBoxId).click();
        LOGGER.info("Terms and Condition CheckBox selected");
        return this;
    }

    public void proceedToPayment()
    {
        driver.findElement(byPayNowBtnXpath).click();
        LOGGER.info("Pay Now btn clicked");
        driver.waitTillElementIsPresent(byPaymentDetailsId, 10);
        visually.checkWindow(SCREEN_NAME, "Payment btn clicked succesfully");
    }
}
