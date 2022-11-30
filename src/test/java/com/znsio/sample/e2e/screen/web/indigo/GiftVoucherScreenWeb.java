package com.znsio.sample.e2e.screen.web.indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class GiftVoucherScreenWeb extends GiftVoucherScreen {
    private final Driver driver ;
    private final Visual visually;

    private static final String SCREEN_NAME = IndigoHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By bySelectDenominationXpath = By.xpath("//*[@id='SelectedVoucherValue']");
    private final By bySelectQuantityXpath = By.xpath("//*[@id='SelectedVoucherQuantity']");
    private final By byPersonaliseChekboxXpath = By.xpath("//*[@id='chkPersonal']");
    private final By byPersonNameInputXpath = By.xpath("//*[@name='Per_Fname']");
    private final By byMessageInputXpath = By.xpath("//*[@id='Message']");
    private final By byPreviewButtonXpath = By.xpath("//input[@class='preview-btn']");
    private final By byPreviewTitleXpath = By.xpath("//*[@class='title-name']/span");
    private final By byPreviewMessageXpath = By.xpath("//*[@class='message-box']/p");
    private final By byProceedButtonXpath = By.xpath("//*[@type='submit' and @value='Proceed']");
    private final By byPromoCodeInputXpath = By.xpath("//input[@name='PromoCode']");
    private final By byApplyButtonXpath = By.xpath("//*[@type='submit' and @value='Apply']");
    private final By byPayableAmountXpath = By.xpath("//label[contains(text(),'Payment Amount')]/following-sibling::*[@id='lblTotal']");
    private final By byErrorXpath = By.xpath("//input[@id='PromoCode']/following-sibling::div");
    private final By byTermsAndConditionsCheckboxXpath = By.xpath("//input[@type='checkbox']");
    private final By byPayNowButtonXpath = By.xpath("//input[@type='submit' and @value='Pay Now']");
    private final By byReceiverFirstNameXpath = By.id("Rec_Fname");
    private final By byReceiverLastNameXpath = By.id("Rec_Lname");
    private final By byReceiverEmailIDXpath = By.id("Rec_EmailID");
    private final By byReceiverPhoneNumberXpath = By.id("Rec_Phone");
    private final By bySenderLastNameXpath = By.id("Per_Lname");
    private final By bySenderEmailIDXpath = By.id("Per_EmailID");
    private final By bySenderPhoneNumberXpath = By.id("Per_Phone");

    public GiftVoucherScreenWeb(Driver driver, Visual visually) {
        LOGGER.info("Setting up Driver");
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public GiftVoucherScreen addDenominationAndQuantity(String denomination, String quantity) {
        LOGGER.info("Adding denomination and quantity for gift voucher");
        driver.moveToElement(bySelectDenominationXpath);
        Select select = new Select(driver.findElement(bySelectDenominationXpath));
        select.selectByValue(denomination);
        select = new Select(driver.findElement(bySelectQuantityXpath));
        select.selectByValue(quantity);
        return this;
    }
    @Override
    public GiftVoucherScreen personaliseGiftVoucher(String titleName, String message) {
        LOGGER.info("Personalising Gift Voucher");
        driver.waitForClickabilityOf(byPersonaliseChekboxXpath).click();
        driver.waitForClickabilityOf(byPersonNameInputXpath).sendKeys(titleName);
        driver.waitForClickabilityOf(byMessageInputXpath).sendKeys(message);
        driver.scrollTillElementIntoView(byPreviewButtonXpath);
        driver.waitForClickabilityOf(byPreviewButtonXpath).click();
        return this;
    }
    @Override
    public boolean previewVoucherAndProceed(String title, String message) {
        LOGGER.info("Preview Voucher");
        String previewTitle = driver.findElement(byPreviewTitleXpath).getText();
        String previewMessage = driver.findElement(byPreviewMessageXpath).getText();
        driver.findElement(byProceedButtonXpath).click();
        return title.equals(previewTitle.replace(",","")) && message.equals(previewMessage);
    }
    @Override
    public boolean applyPromoCode(String promoCode) {
        LOGGER.info("Applying PromoCode");
        String payableAmount = driver.findElement(byPayableAmountXpath).getText();
        driver.findElement(byPromoCodeInputXpath).sendKeys(promoCode);
        driver.findElement(byApplyButtonXpath).click();
        boolean errorThrown = driver.findElement(byErrorXpath).isDisplayed();
        return errorThrown && payableAmount.equals(driver.findElement(byPayableAmountXpath).getText());
    }
    @Override
    public PaymentScreen giveDeliveryOptionsAndProceed() {
        LOGGER.info("Select delivery details");
        driver.findElement(byReceiverFirstNameXpath).sendKeys(RandomStringUtils.randomAlphabetic(5));
        driver.findElement(byReceiverLastNameXpath).sendKeys(RandomStringUtils.randomAlphabetic(4));
        driver.findElement(byReceiverEmailIDXpath).sendKeys(RandomStringUtils.randomAlphabetic(4)+"@gmail.com");
        driver.findElement(byReceiverPhoneNumberXpath).sendKeys(RandomStringUtils.randomNumeric(10));
        driver.findElement(bySenderLastNameXpath).sendKeys(RandomStringUtils.randomAlphabetic(4));
        driver.findElement(bySenderEmailIDXpath).sendKeys(RandomStringUtils.randomAlphabetic(4)+"@gmail.com");
        driver.findElement(bySenderPhoneNumberXpath).sendKeys(RandomStringUtils.randomNumeric(10));
        driver.findElement(byTermsAndConditionsCheckboxXpath).click();
        driver.findElement(byPayNowButtonXpath).click();
        return PaymentScreen.get();
    }
}
