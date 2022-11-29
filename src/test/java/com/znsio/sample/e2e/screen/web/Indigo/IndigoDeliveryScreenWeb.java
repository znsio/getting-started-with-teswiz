package com.znsio.sample.e2e.screen.web.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoDeliveryScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndigoDeliveryScreenWeb extends IndigoDeliveryScreen {

    private static final By byPromoCodeTextBoxId = By.id("PromoCode");
    private static final By byPromoCodeApplyBtnXpath = By.xpath("//div[@class='row price-section']//input[@id='btnApplyPromoCode']");
    private static final By byErrorMessageXpath = By.xpath("//div[contains(text(),'Invali')]");
    private static final By byFinalAmountXpath = By.xpath("//label[contains(text(),'Payment')]/following-sibling::span");
    private static final By bySenderNameId = By.id("Per_Fname");
    private static final By bySenderLastNameId = By.id("Per_Lname");
    private static final By byReceiverNameId = By.id("Rec_Fname");
    private static final By byReceiverLastNameId = By.id("Rec_Lname");
    private static final By byReceiverMailId = By.id("Rec_EmailID");
    private static final By byReceiverPhoneId = By.id("Rec_Phone");
    private static final By bySenderMailId = By.id("Per_EmailID");
    private static final By bySenderPhone = By.id("Per_Phone");
    private static final By byTermsConditionChkBoxId = By.id("chkTnC");
    private static final By byPayNowBtnXpath = By.xpath("//input[@value='Pay Now']");
    private static final By byPaymentDetailsId = By.id("paymentinformation");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = IndigoDeliveryScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public IndigoDeliveryScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public IndigoDeliveryScreen enterInvalidPromoCode(String promoCode) {
        driver.findElement(byPromoCodeTextBoxId).sendKeys(promoCode);
        LOGGER.info("Invalid Promo code entered in Delivery option page");
        visually.checkWindow(SCREEN_NAME, "Invalid Promo code entered");
        driver.findElement(byPromoCodeApplyBtnXpath).click();
        return this;
    }

    @Override
    public String getErrorMessage() {
        String errorMessage = driver.findElement(byErrorMessageXpath).getText();
        LOGGER.info("Invalid Promo Code error message " + errorMessage);
        return errorMessage;
    }

    @Override
    public String getFinalAmount() {
        String amount = driver.findElement(byFinalAmountXpath).getText();
        LOGGER.info("Final amount after applying Promo Code " + amount);
        String finalAmountAfterPromoCode = amount.substring(1).trim();
        return finalAmountAfterPromoCode;
    }

    @Override
    public IndigoDeliveryScreen enterReceiverDetail(String receiverFirstName, String receiverLastName, String receiverMail, String receiverPhone) {
        driver.findElement(byReceiverNameId).sendKeys(receiverFirstName);
        driver.findElement(byReceiverLastNameId).sendKeys(receiverLastName);
        driver.findElement(byReceiverMailId).sendKeys(receiverMail);
        driver.findElement(byReceiverPhoneId).sendKeys(receiverPhone);
        LOGGER.info("Reciever Details entered " + receiverFirstName + " " + receiverLastName + " " + receiverMail + " " + receiverPhone);
        visually.checkWindow(SCREEN_NAME, "Reciver details entered");
        return this;
    }

    @Override
    public IndigoDeliveryScreen enterSenderDetails(String senderFirstName, String senderLastName, String senderrMail, String senderPhone) {
        driver.findElement(bySenderNameId).sendKeys(senderFirstName);
        driver.findElement(bySenderLastNameId).sendKeys(senderLastName);
        driver.findElement(bySenderMailId).sendKeys(senderrMail);
        driver.findElement(bySenderPhone).sendKeys(senderPhone);
        LOGGER.info("Sender Details entered " + senderFirstName + " " + senderLastName + " " + senderrMail + " " + senderPhone);
        visually.checkWindow(SCREEN_NAME, "Sender details entered");
        return this;
    }

    @Override
    public IndigoDeliveryScreen selectTermsAndConditions() {
        driver.findElement(byTermsConditionChkBoxId).click();
        LOGGER.info("Terms and Condition CheckBox selected");
        return this;
    }

    @Override
    public String clickOnProceedBtn() {
        driver.findElement(byPayNowBtnXpath).click();
        driver.waitTillElementIsPresent(byPaymentDetailsId, 10);
        String paymentPageUrl = driver.getInnerDriver().getCurrentUrl();
        LOGGER.info("Payment page url" + paymentPageUrl);
        visually.checkWindow(SCREEN_NAME, "Payment Page");
        return paymentPageUrl;
    }
}
