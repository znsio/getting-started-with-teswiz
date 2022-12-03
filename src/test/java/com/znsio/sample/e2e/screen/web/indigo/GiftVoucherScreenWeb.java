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
    //tag name need to be changed
    private static final By bySelectDenominationId = By.id("SelectedVoucherValue");
    private static final By bySelectQuantityId = By.id("SelectedVoucherQuantity");
    private static final By byPersonaliseChekboxId = By.id("chkPersonal");
    private static final By byPersonNameInputName = By.name("Per_Fname");
    private static final By byMessageInputId = By.id("Message");
    private static final By byPreviewButtonXpath = By.xpath("//input[@class='preview-btn']");
    private static final By byPreviewTitleXpath = By.xpath("//div[@class='title-name']");
    private static final By byPreviewMessageXpath = By.xpath("//div[@class='message-box']/p");
    private static final By byVoucherPriceInfoXapth = By.xpath("//div[@class='doted-line']/following-sibling::p");
    private static final By byProceedButtonXpath = By.xpath("//input[@type='submit' and @value='Proceed']");
    private static final By byLandOnPurchaseScreenXpath = By.xpath("//div[@class='row delivery-option']");
    private static final By byPromoCodeInputName = By.name("PromoCode");
    private static final By byApplyButtonXpath = By.xpath("//*[@type='submit' and @value='Apply']");
    private static final By byPayableAmountXpath = By.xpath("//label[contains(text(),'Payment Amount')]/following-sibling::*[@id='lblTotal']");
    private static final By byErrorXpath = By.xpath("//input[@id='PromoCode']/following-sibling::div[contains(text(),'Invalid Promo Code')]");
    private static final By byTermsAndConditionsCheckboxXpath = By.xpath("//input[@type='checkbox']");
    private static final By byPayNowButtonXpath = By.xpath("//input[@type='submit' and @value='Pay Now']");
    private static final By byReceiverFirstNameId = By.id("Rec_Fname");
    private static final By byReceiverLastNameId = By.id("Rec_Lname");
    private static final By byReceiverEmailIDId = By.id("Rec_EmailID");
    private static final By byReceiverPhoneNumberId = By.id("Rec_Phone");
    private static final By bySenderLastNameId = By.id("Per_Lname");
    private static final By bySenderEmailIDId = By.id("Per_EmailID");
    private static final By bySenderPhoneNumberId = By.id("Per_Phone");

    public GiftVoucherScreenWeb(Driver driver, Visual visually) {
        LOGGER.info("Setting up Driver");
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public GiftVoucherScreen addDenominationAndQuantity(String denomination, String quantity) {
        LOGGER.info("Adding denomination and quantity for gift voucher");
        driver.moveToElement(bySelectDenominationId);
        Select select = new Select(driver.findElement(bySelectDenominationId));
        select.selectByValue(denomination);
        select = new Select(driver.findElement(bySelectQuantityId));
        select.selectByValue(quantity);
        return this;
    }
    @Override
    public GiftVoucherScreen personaliseGiftVoucher(String titleName, String message) {
        LOGGER.info("Personalising Gift Voucher");
        driver.waitForClickabilityOf(byPersonaliseChekboxId).click();
        driver.waitForClickabilityOf(byPersonNameInputName).sendKeys(titleName);
        driver.waitForClickabilityOf(byMessageInputId).sendKeys(message);
        driver.scrollTillElementIntoView(byPreviewButtonXpath);
        return this;
    }

    @Override
    public GiftVoucherScreen previewVoucher() {
        LOGGER.info("Navigating to Preview");
        driver.waitForClickabilityOf(byPreviewButtonXpath).click();
        visually.checkWindow(SCREEN_NAME,"Preview for voucher");
        return this;
    }

    @Override
    public boolean verifyVoucherDetails(String titleName, String message, int voucherPrice) {
        String previewTitle = driver.findElement(byPreviewTitleXpath).getText();
        String previewMessage = driver.findElement(byPreviewMessageXpath).getText();
        String voucherPriceInfo = driver.findElement(byVoucherPriceInfoXapth).getText();
        LOGGER.info("Verifying Voucher Details");
        return titleName.equals(previewTitle.replace(",","")) &&
                message.equals(previewMessage) &&
                voucherPriceInfo.contains(String.valueOf(voucherPrice));
    }

    @Override
    public boolean proceedToBuy() {
        LOGGER.info("Proceed to Purchase Screen and verify");
        driver.findElement(byProceedButtonXpath).click();
        boolean landedOnPurchasePAge = driver.findElement(byLandOnPurchaseScreenXpath).isDisplayed();
        visually.checkWindow(SCREEN_NAME,"moved to Buy gift voucher Purchase section");
        return landedOnPurchasePAge;
    }
    @Override
    public boolean applyPromoCode(String promoCode) {
        LOGGER.info("Applying PromoCode");
        String payableAmount = driver.findElement(byPayableAmountXpath).getText();
        driver.findElement(byPromoCodeInputName).sendKeys(promoCode);
        driver.findElement(byApplyButtonXpath).click();
        boolean errorThrown = driver.findElement(byErrorXpath).isDisplayed();
        return errorThrown && payableAmount.equals(driver.findElement(byPayableAmountXpath).getText());
    }
    @Override
    public GiftVoucherScreenWeb giveDeliveryOptions() {
        LOGGER.info("Select delivery details");
        driver.findElement(byReceiverFirstNameId).sendKeys(RandomStringUtils.randomAlphabetic(5));
        driver.findElement(byReceiverLastNameId).sendKeys(RandomStringUtils.randomAlphabetic(4));
        driver.findElement(byReceiverEmailIDId).sendKeys(RandomStringUtils.randomAlphabetic(4)+"@gmail.com");
        driver.findElement(byReceiverPhoneNumberId).sendKeys(RandomStringUtils.randomNumeric(10));
        driver.findElement(bySenderLastNameId).sendKeys(RandomStringUtils.randomAlphabetic(4));
        driver.findElement(bySenderEmailIDId).sendKeys(RandomStringUtils.randomAlphabetic(4)+"@gmail.com");
        driver.findElement(bySenderPhoneNumberId).sendKeys(RandomStringUtils.randomNumeric(10));
        driver.findElement(byTermsAndConditionsCheckboxXpath).click();
        return this;
    }
    @Override
    public PaymentScreen proceedToPaymentPage() {
        LOGGER.info("Navigating to Payment Screen");
        driver.findElement(byPayNowButtonXpath).click();
        return PaymentScreen.get();
    }
}
