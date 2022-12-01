package com.znsio.sample.e2e.screen.indigo.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.GIFT_VOUCHER_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;
import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class GiftVoucherScreenWeb extends GiftVoucherScreen {

    private static final Logger LOGGER = Logger.getLogger(GiftVoucherScreen.class.getName());

    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private final By byQuantityDropdownId = By.id("SelectedVoucherQuantity");

    private final By byDenominationDropdownId = By.id("SelectedVoucherValue");

    private final By byMakeItPersonalId = By.id("chkPersonal");

    private final By byPersonalNameId = By.id("Per_Fname");

    private final By byPersonalMessageId = By.id("Message");

    private final By byPreviewXpath = By.xpath("//input[@value='Preview']");

    private final By byProceedXpath = By.xpath("//input[@value='Proceed']");

    private final By byPromoCodeId = By.id(("PromoCode"));

    private final By byApplyPromoCodeId = By.id("btnApplyPromoCode");

    private final By byPromoCodeMessageXpath = By.xpath("//input[@id=\"PromoCode\"]//following-sibling::div");

    private final By byTotalAmountId = By.id("lblTotal");


    private final By byFirstNameXpath = By.xpath("//div[@class=\"label-value\"]//input[@id='Per_Fname']");

    private final By byLastNameId = By.id("Per_Lname");

    private final By byEmailAddId = By.id("Per_EmailID");

    private final By byPhoneId = By.id("Per_Phone");

    private final By bySenderXpath = By.xpath("//label[@for=\"radio-2\"]");
    private final By byTncId = By.id("chkTnC");
    private final By byPayNowClass = By.className("preview-btn");

    public GiftVoucherScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
    }

    @Override
    public GiftVoucherScreen selectQuantity(String quantity) {

        LOGGER.info("select "+quantity+" quantities in gift voucher screen");
        WebElement quantityDropdownWebElement = driver.waitTillElementIsPresent(byQuantityDropdownId);
        Select quantityDropdown = new Select(quantityDropdownWebElement);
        quantityDropdown.selectByValue(quantity);

        return this;
    }

    @Override
    public GiftVoucherScreen selectDenomination(String denominations) {

        LOGGER.info("select "+denominations+" denominations in gift voucher screen");
        WebElement denominationDropdownWebElement = driver.waitTillElementIsPresent(byDenominationDropdownId);
        Select denominationDropdown = new Select(denominationDropdownWebElement);
        denominationDropdown.selectByValue(denominations);

        return this;
    }

    @Override
    public GiftVoucherScreen personaliseGiftVoucher(Map senderDetails) {

        LOGGER.info("Personalise gift voucher with details "+senderDetails.toString());
        WebElement makeItPersonalWebElement = driver.waitTillElementIsPresent(byMakeItPersonalId);
        if(!makeItPersonalWebElement.isSelected()) {
            makeItPersonalWebElement.click();
        }

        driver.waitTillElementIsPresent(byPersonalNameId).sendKeys((CharSequence) senderDetails.get(GIFT_VOUCHER_TEST_CONTEXT.FIRST_NAME));

        driver.waitTillElementIsPresent(byPersonalMessageId).sendKeys((CharSequence) senderDetails.get(GIFT_VOUCHER_TEST_CONTEXT.MESSAGE));

        return this;
    }

    @Override
    public GiftVoucherScreen previewGiftVoucher() {

        LOGGER.info("Click Preview and Proceed ");
        driver.waitTillElementIsPresent(byPreviewXpath).submit();

        driver.waitTillElementIsPresent(byProceedXpath).click();

        return this;
    }

    @Override
    public GiftVoucherScreen applyPromoCode(String promocode) {

        LOGGER.info("Apply a "+promocode+" promocode in gift voucher screen");
        driver.waitTillElementIsPresent(byPromoCodeId).sendKeys(promocode);

        driver.findElement(byApplyPromoCodeId).click();

        return this;
    }

    @Override
    public int getTotalAmount() {
        return Integer.parseInt(driver.findElement(byTotalAmountId).getText().substring(2));
    }

    @Override
    public String getPromoCodeMessage() {
        return driver.waitTillElementIsPresent(byPromoCodeMessageXpath).getText();
    }

    @Override
    public GiftVoucherScreen selectDeliveryOption(String option) {

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
        driver.findElement(byLastNameId).sendKeys((CharSequence) sendersDetails.get(GIFT_VOUCHER_TEST_CONTEXT.LAST_NAME));
        driver.findElement(byPhoneId).sendKeys((CharSequence) sendersDetails.get(GIFT_VOUCHER_TEST_CONTEXT.PHONE));
        driver.findElement(byEmailAddId).sendKeys((CharSequence) sendersDetails.get(GIFT_VOUCHER_TEST_CONTEXT.EMAIL));

        return this;
    }

    @Override
    public GiftVoucherScreen checkTermsAndConditions(boolean check) {

        LOGGER.info("Check tnc checkbox");
        if(check != driver.waitTillElementIsPresent(byTncId).isSelected()){
            driver.findElement(byTncId).click();
        }
        return this;
    }

    @Override
    public PaymentScreen clickPayNow() {

        LOGGER.info("Click on Pay Now");
        driver.findElement(byPayNowClass).click();
        return new PaymentScreenWeb(this.driver, this.visually);
    }
}
