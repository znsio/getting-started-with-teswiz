package com.znsio.sample.e2e.screen.indigo.android;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.GIFT_VOUCHER_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;
import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import java.util.Map;

public class GiftVoucherScreenAndroid extends GiftVoucherScreen {

    private static final Logger LOGGER = Logger.getLogger(GiftVoucherScreenAndroid.class.getName());
    private final Driver driver;
    private final Visual visually;

    private static final By byDenominationDropdownXpath = By.xpath("//android.view.View[@resource-id = 'SelectedVoucherValue']");

    private static final By byQuantityDropdownXpath = By.xpath("//android.view.View[@resource-id = 'SelectedVoucherQuantity']");

    private static final By byMakeItPersonalXpath = By.xpath("//android.widget.CheckBox[@resource-id = 'chkPersonal']");

    private static final By byPersonalNameXpath = By.xpath("//android.view.EditText[@resource-id = 'Per_Fname']");

    private static final By byPersonalMessageXpath = By.xpath("//android.view.EditText[@resource-id = 'Message']");

    private static final By byPreviewXpath = By.xpath("//android.widget.Button[@text='Preview']");

    private static final By byProceedXpath = By.xpath("//android.widget.Button[@text='Proceed']");

    private static final By byPromoCodeXpath = By.xpath(("//android.view.EditText[@resource-id = 'PromoCode']"));

    private static final By byApplyPromoCodeXpath = By.xpath("//android.widget.Button[@text='btnApplyPromoCode']");

    private static final By byPromoCodeMessageXpath = By.xpath("//android.widget.TextView[contains(@text,'Invalid Promo')]");

    private static final By byTotalAmountXpath = By.xpath("//android.widget.TextView[contains(@text,'lblTotal')]");

    private static final By byLastNameXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Lname']");

    private static final By byEmailAddXpath = By.xpath("//android.widget.EditText[@resource-id='Per_EmailID']");

    private static final By byPhoneXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Phone']");

    private static final By bySenderXpath = By.xpath("//android.view.View[contains(@text,'to the sender')]");

    private static final By byTncXpath = By.xpath("//android.widget.CheckBox[@resource-id='chkTnC']");

    private static final By byPayNowXpath = By.xpath("//android.widget.Button[@text='Pay Now']");

    public GiftVoucherScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public GiftVoucherScreen selectQuantity(String quantity) {

        LOGGER.info("select "+quantity+" quantities in gift voucher screen");
        driver.waitTillElementIsPresent(byQuantityDropdownXpath).click();

        driver.waitTillElementIsPresent(By.xpath("//android.widget.CheckedTextView[contains(@text,'"+quantity+"')]"))
                .click();

        return this;
    }

    @Override
    public GiftVoucherScreen selectDenomination(String denominations) {

        LOGGER.info("select "+denominations+" denominations in gift voucher screen");
        driver.waitTillElementIsPresent(byDenominationDropdownXpath).click();

        driver.waitTillElementIsPresent(By.xpath("//android.widget.CheckedTextView[contains(@text,'"+denominations+"')]"))
                .click();

        return this;
    }

    @Override
    public GiftVoucherScreen personaliseGiftVoucher(Map senderDerails) {

        driver.scrollDownByScreenSize();

        driver.waitTillElementIsPresent(byMakeItPersonalXpath).click();

        driver.waitTillElementIsPresent(byPersonalNameXpath).sendKeys((CharSequence) senderDerails.get(GIFT_VOUCHER_TEST_CONTEXT.FIRST_NAME));

        driver.waitTillElementIsPresent(byPersonalMessageXpath).sendKeys((CharSequence) senderDerails.get(GIFT_VOUCHER_TEST_CONTEXT.MESSAGE));

        return this;
    }

    @Override
    public GiftVoucherScreen previewGiftVoucher() {

        driver.findElement(byPreviewXpath).click();

        driver.scrollDownByScreenSize();

        driver.waitTillElementIsPresent(byProceedXpath).click();

        return this;
    }

    @Override
    public GiftVoucherScreen applyPromoCode(String promocode) {

        driver.waitTillElementIsPresent(byPromoCodeXpath).sendKeys(promocode);

        driver.findElement(byApplyPromoCodeXpath).click();

        return this;
    }

    @Override
    public int getTotalAmount() {
        String amount = driver.findElement(byTotalAmountXpath).getText().split(" ")[2];
        LOGGER.info("Total Amount Of Gift Voucher is "+amount+" rupees");
        return Integer.parseInt(amount);
    }

    @Override
    public String getPromoCodeMessage() {
        return driver.findElement(byPromoCodeMessageXpath).getText();
    }

    @Override
    public GiftVoucherScreen selectDeliveryOption(String option) {
        driver.scrollDownByScreenSize();

        LOGGER.info("Select delivery option - "+option);

        if(option.equals("sender")){
            driver.waitTillElementIsPresent(bySenderXpath).click();
        }
        else{
            throw new NotImplementedException("Not implemented for other options");
        }

        return this;
    }

    @Override
    public GiftVoucherScreen enterSenderDetails(Map sendersDetails) {
        LOGGER.info("Enter the delivery details for sender");
        driver.findElement(byLastNameXpath).sendKeys((CharSequence) sendersDetails.get(GIFT_VOUCHER_TEST_CONTEXT.LAST_NAME));
        driver.findElement(byPhoneXpath).sendKeys((CharSequence) sendersDetails.get(GIFT_VOUCHER_TEST_CONTEXT.PHONE));
        driver.findElement(byEmailAddXpath).sendKeys((CharSequence) sendersDetails.get(GIFT_VOUCHER_TEST_CONTEXT.EMAIL));
        return this;
    }

    @Override
    public GiftVoucherScreen checkTermsAndConditions(boolean check) {
        LOGGER.info("Check tnc checkbox");
        if(check != driver.waitTillElementIsPresent(byTncXpath).isSelected()){
            driver.findElement(byTncXpath).click();
        }
        return this;
    }

    @Override
    public PaymentScreen clickPayNow() {
        LOGGER.info("Click on Pay Now");
        driver.findElement(byPayNowXpath).click();
        return new PaymentScreenAndroid(this.driver, this.visually);
    }
}
