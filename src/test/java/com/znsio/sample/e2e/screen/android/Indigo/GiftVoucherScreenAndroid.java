package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.e2e.tools.Wait;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;


public class GiftVoucherScreenAndroid extends GiftVoucherScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private final By bySelectDenominationXpath = By.xpath("//android.view.View[@resource-id='SelectedVoucherValue']");
    private final By bySelectQuantityXpath = By.xpath("//android.view.View[@resource-id='SelectedVoucherQuantity']");
    private final By byPersonaliseXpath = By.xpath("//android.widget.CheckBox[@text, 'chkPersonal']");
    private final By byNameInputId = By.xpath("//android.widget.EditText[@resource-id='Per_Fname']");
    private final By byMessageInputId = By.xpath("//android.widget.EditText[@resource-id='Message']");
    private final By byPreviewButtonXpath = By.xpath("//android.widget.Button[contains(@text,'Preview')]");
    private final By byPreviewTitleXpath = By.xpath("//android.widget.TextView[@index=2]");
    private final By byPreviewMessageXpath = By.xpath("//android.widget.TextView[@index=3]");
    private final By byVoucherPriceInfoXapth = By.xpath("//android.widget.TextView[@index=4]");
    private final By byProceedButtonXpath = By.xpath("//android.widget.Button[contains(@text,'Proceed')]");
    private final By byLandOnPurchaseScreenXpath = By.xpath("//android.widget.TextView[@text='Your Gift Voucher']");
    private final By byPayableAmountXpath = By.xpath("//android.widget.TextView[@resource-id='lblTotal']");
    private final By byPromoCodeInputXpath = By.xpath("//android.widget.EditText[@resource-id='PromoCode']");
    private final By byApplyButtonXpath = By.xpath("//android.widget.Button[@resource-id='btnApplyPromoCode']");
    private final By byErrorXpath = By.xpath("//android.widget.TextView[contains(@text,'Invalid Promo Code')]");
    private final By byReceiverFirstNameXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Fname']");
    private final By byReceiverLastNameXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Lname']");
    private final By byReceiverEmailIDXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_EmailID']");
    private final By byReceiverPhoneNumberXpath = By.xpath("//android.widget.EditText[@resource-id='Rec_Phone']");
    private final By bySenderLastNameXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Lname']");
    private final By bySenderEmailIDXpath = By.xpath("//android.widget.EditText[@resource-id='Per_EmailID']");
    private final By bySenderPhoneNumberXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Phone']");
    private final By byTermsAndConditionsCheckboxXpath = By.xpath("//android.widget.CheckBox[@resource-id='chkTnC']");
    private final By byPayNowButtonXpath = By.xpath("//android.widget.Button[contains(@text,'Pay Now')]");


    public GiftVoucherScreenAndroid(Driver driver, Visual visually) {
        LOGGER.info("Setting up driver");
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public GiftVoucherScreen addDenominationAndQuantity(String denomination, String quantity) {
        String denominationXpath = "//android.widget.CheckedTextView[contains(@text,'%s')]";
        String quantityXpath = "//android.widget.CheckedTextView[contains(@text,'%s')]";
        visually.checkWindow(SCREEN_NAME, "GiftVoucher section");
        LOGGER.info("Selecting Denomination and Quantity");
        driver.scrollDownByScreenSize();
        driver.waitForClickabilityOf(bySelectDenominationXpath).click();
        driver.waitForClickabilityOf(By.xpath(String.format(denominationXpath, denomination))).click();
        driver.waitTillElementIsPresent(bySelectQuantityXpath).click();
        driver.waitForClickabilityOf(By.xpath(String.format(quantityXpath, quantity))).click();
        return this;
    }

    @Override
    public GiftVoucherScreen personaliseGiftVoucher(String titleName, String message) {
        LOGGER.info("Personalise gift voucher");
        driver.scrollDownByScreenSize();
        driver.waitForClickabilityOf(byPersonaliseXpath).click();
        driver.scrollToAnElementByText("Dear");
        driver.waitForClickabilityOf(byNameInputId).clear();
        driver.waitTillElementIsPresent(byNameInputId).sendKeys(titleName);
        driver.scrollToAnElementByText("Message");
        driver.waitForClickabilityOf(byMessageInputId).clear();
        driver.waitTillElementIsPresent(byMessageInputId).sendKeys(message);

        return this;
    }

    @Override
    public GiftVoucherScreen previewVoucher() {
        LOGGER.info("Navigating to Preview");
        driver.waitForClickabilityOf(byPreviewButtonXpath).click();
        visually.checkWindow(SCREEN_NAME, "Preview for voucher");
        return this;
    }

    @Override
    public boolean verifyVoucherDetails(String titleName, String message, int voucherPrice) {
        String previewTitle = driver.findElement(byPreviewTitleXpath).getText();
        String previewMessage = driver.findElement(byPreviewMessageXpath).getText();
        String voucherPriceInfo = driver.findElement(byVoucherPriceInfoXapth).getText();
        LOGGER.info("Verifying Voucher Details");
        return titleName.equals(previewTitle.replace(",", "")) &&
                message.equals(previewMessage) &&
                voucherPriceInfo.contains(String.valueOf(voucherPrice));
    }

    @Override
    public boolean proceedToBuy() {
        LOGGER.info("Proceed to Purchase Screen and verify");
        driver.scrollDownByScreenSize();
        driver.findElement(byProceedButtonXpath).click();
        boolean landedOnPurchasePage = driver.findElement(byLandOnPurchaseScreenXpath).isDisplayed();
        visually.checkWindow(SCREEN_NAME, "moved to Buy gift voucher Purchase section");
        return landedOnPurchasePage;
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
    public GiftVoucherScreenAndroid giveDeliveryOptions() {

        LOGGER.info("Select delivery details");
        driver.scrollToAnElementByText("Receiver Details");
        driver.waitForClickabilityOf(byReceiverFirstNameXpath).sendKeys(RandomStringUtils.randomAlphabetic(5));
        driver.waitForClickabilityOf(byReceiverLastNameXpath).sendKeys(RandomStringUtils.randomAlphabetic(4));
        driver.waitForClickabilityOf(byReceiverEmailIDXpath).sendKeys(RandomStringUtils.randomAlphabetic(4) + "@gmail.com");
        driver.findElement(byReceiverPhoneNumberXpath).sendKeys(RandomStringUtils.randomNumeric(10));
        driver.scrollToAnElementByText("Sender Details");
        driver.findElement(bySenderLastNameXpath).sendKeys(RandomStringUtils.randomAlphabetic(4));
        driver.findElement(bySenderEmailIDXpath).sendKeys(RandomStringUtils.randomAlphabetic(4) + "@gmail.com");
        driver.findElement(bySenderPhoneNumberXpath).sendKeys(RandomStringUtils.randomNumeric(10));
        driver.findElement(byTermsAndConditionsCheckboxXpath).click();
        return this;
    }

    @Override
    public PaymentScreen proceedToPaymentPage() {
        LOGGER.info("Navigating to Payment Screen");
        driver.scrollDownByScreenSize();
        driver.findElement(byPayNowButtonXpath).click();
        return PaymentScreen.get();
    }
}
