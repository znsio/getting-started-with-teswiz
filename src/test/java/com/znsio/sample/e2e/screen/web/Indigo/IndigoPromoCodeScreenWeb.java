package com.znsio.sample.e2e.screen.web.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoPromoCodeScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndigoPromoCodeScreenWeb extends IndigoPromoCodeScreen {

    public static final By byPromoCodeTextBoxId = By.id("PromoCode");
    public static final By byPromoCodeApplyBtnXpath = By.xpath("//div[@class='row price-section']//input[@id='btnApplyPromoCode']");
    public static final By byErrorMessageXpath = By.xpath("//div[contains(text(),'Invali')]");
    public static final By byFinalAmountXpath = By.xpath("//label[contains(text(),'Payment')]/following-sibling::span");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = IndigoPromoCodeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public IndigoPromoCodeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public IndigoPromoCodeScreen enterInvalidPromoCode() {
        driver.findElement(byPromoCodeTextBoxId).sendKeys("00000");
        LOGGER.info("Invalid Promo code entered in Delivery option page");
        visually.checkWindow(SCREEN_NAME, "Invalid Promo code entered");
        driver.findElement(byPromoCodeApplyBtnXpath).click();
        return this;
    }

    @Override
    public String getErrorMessage() {
        String errorMessage  = driver.findElement(byErrorMessageXpath).getText();
        LOGGER.info("Invalid Promo Code error message " +errorMessage);
        return errorMessage;
    }

    @Override
    public String getFinalAmount() {
        String finalAmountAfterPromoCode = driver.findElement(byFinalAmountXpath).getText();
        LOGGER.info("Final amount after applying Promo Code " +finalAmountAfterPromoCode);
        return finalAmountAfterPromoCode;
    }

    @Override
    public IndigoPromoCodeScreen enterReceiverDetail(String receiverFirstName, String receiverLastName, String receiverMail, String receiverPhone) {
        driver.findElement(By.id("Rec_Fname")).sendKeys(receiverFirstName);
        driver.findElement(By.id("Rec_Lname")).sendKeys(receiverLastName);
        driver.findElement(By.id("Rec_EmailID")).sendKeys(receiverMail);
        driver.findElement(By.id("Rec_Phone")).sendKeys(receiverPhone);
        LOGGER.info("Reciever Details entered " +receiverFirstName+" "+receiverLastName+ " "+receiverMail+" "+receiverPhone);
        visually.checkWindow(SCREEN_NAME, "Reciver details entered");
        return this;
    }

    @Override
    public IndigoPromoCodeScreen enterSenderDetails(String senderFirstName, String senderLastName, String senderrMail, String senderPhone) {
        driver.findElement(By.id("Rec_Fname")).sendKeys(senderFirstName);
        driver.findElement(By.id("Rec_Lname")).sendKeys(senderLastName);
        driver.findElement(By.id("Rec_EmailID")).sendKeys(senderrMail);
        driver.findElement(By.id("Rec_Phone")).sendKeys(senderPhone);
        LOGGER.info("Reciever Details entered " +senderFirstName+" "+senderLastName+ " "+senderrMail+" "+senderPhone);
        visually.checkWindow(SCREEN_NAME, "Reciver details entered");
        return this;
    }
}
