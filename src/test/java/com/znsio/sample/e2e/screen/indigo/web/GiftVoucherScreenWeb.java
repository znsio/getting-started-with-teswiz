package com.znsio.sample.e2e.screen.indigo.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.HomePageScreen;
import com.znsio.sample.e2e.screen.indigo.PaymentPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GiftVoucherScreenWeb
        extends GiftVoucherScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = GiftVoucherScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byDenominationId = By.id("SelectedVoucherValue");
    private static final By byQuantityId = By.id("SelectedVoucherQuantity");
    private static final By byCheckPersonalId = By.id("chkPersonal");
    private static final By byFirstNameTextBoxId = By.id("Per_Fname");
    private static final By byMessageTextBoxId = By.id("Message");
    private static final By byTotalAmountId = By.id("lblTotal");
    private static final By byFirstNameSelector = By.cssSelector(".title-name span");
    private static final By byMessageSelector = By.cssSelector(".message-box p:nth-child(3)");
    private static final By byTotalAmountSelector = By.cssSelector(".message-box p:nth-child(5)");
    private static final By byPreviewButtonClass = By.className("preview-btn");
    private static final By byProceedButtonSelector = By.cssSelector(".preview-btn.pull-right");
    private static final By byPromoCodeTextFieldId = By.id("PromoCode");
    private static final By byBtnApplyPromoCodeId = By.id("btnApplyPromoCode");
    private static final By byErrorMessageXpath = By.xpath("//input[@id='PromoCode']//following-sibling::div");
    private static final By byClickEmailToSenderSelector = By.cssSelector(".radio-box [for='radio-2']");
    private static final By byLastNameId = By.id("Per_Lname");
    private static final By byEmailId = By.id("Per_EmailID");
    private static final By byPhoneId = By.id("Per_Phone");
    private static final By byChcTncId = By.id("chkTnC");
    private static final By byGiftVoucherPageClass = By.className("hero-banner");

    public GiftVoucherScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public GiftVoucherScreen selectQuantity(int quantity) {

        LOGGER.info("User select the quantity from dropdown as : "+ quantity);
        Select selectQuantity = new Select(driver.waitTillElementIsPresent(byQuantityId, 20));
        selectQuantity.selectByValue(Integer.toString(quantity));
        visually.checkWindow(SCREEN_NAME, "Gift Voucher Screen after selecting denomination and quantity");
        return this;
    }

    @Override
    public GiftVoucherScreen selectDenomination(int denomination) {

        LOGGER.info("User select the denomination from dropdown as : "+ denomination);
        visually.checkWindow(SCREEN_NAME, "Gift Voucher Screen");

        driver.waitTillElementIsVisible(byGiftVoucherPageClass, 20);
        driver.scrollToBottom();
        Select selectDenomination = new Select(driver.waitTillElementIsPresent(byDenominationId, 20));
        selectDenomination.selectByValue(Integer.toString(denomination));
        return this;
    }

    @Override
    public Boolean previewGiftVoucher(String firstName, String message, int denomination) {

        driver.scrollToBottom();
        driver.waitTillElementIsPresent(byPreviewButtonClass).click();

        LOGGER.info("User clicked on preview button");
        visually.checkWindow(SCREEN_NAME, "preview gift voucher screen");

        return driver.waitTillElementIsPresent(byFirstNameSelector, 20).getText().equalsIgnoreCase(firstName + ',')
                && driver.waitTillElementIsPresent(byMessageSelector, 20).getText().equalsIgnoreCase(message)
                && driver.waitTillElementIsPresent(byTotalAmountSelector, 20).getText().contains(Integer.toString(denomination));
    }

    @Override
    public GiftVoucherScreen personaliseMessage(String firstName, String message) {

        driver.waitTillElementIsPresent(byCheckPersonalId).click();
        driver.waitTillElementIsPresent(byFirstNameTextBoxId, 20).sendKeys(firstName);
        driver.waitTillElementIsPresent(byMessageTextBoxId, 20).sendKeys(message);
        LOGGER.info("User personalised the gift voucher with message");
        visually.checkWindow(SCREEN_NAME, "post personalised voucher screen");
        return this;
    }

    @Override
    public int getTotalAmountOfGiftCard() {

        LOGGER.info("Users fetching the total amount on preview page");
        return Integer.parseInt(driver.waitTillElementIsPresent(byTotalAmountId, 20).getText().split(" ")[1]);
    }

    @Override
    public GiftVoucherScreen applyPromoCode(String promoCode) {

        driver.scrollToBottom();
        driver.waitTillElementIsPresent(byProceedButtonSelector, 20).click();
        driver.waitTillElementIsPresent(byPromoCodeTextFieldId, 20).sendKeys(promoCode);
        driver.waitTillElementIsPresent(byBtnApplyPromoCodeId, 20).click();

        LOGGER.info("User applied the promo code");
        visually.checkWindow(SCREEN_NAME, "apply promo code page ");
        return this;
    }

    @Override
    public List<Integer> getFinalAmountOfGiftCard() {

        LOGGER.info("User is fetching the total actual amount and post applied invalid coupon");
        List<Integer> list = new ArrayList<>();
        driver.findElements(byTotalAmountId).forEach(webElement -> list.add(Integer.parseInt(webElement.getText().split(" ")[1])));
        return list;
    }

    @Override
    public String getErrorMessage() {
        String message = driver.waitTillElementIsPresent(byErrorMessageXpath, 20).getText();
        LOGGER.info("Error message for invalid applied coupon : "+ message);
        return message;
    }

    @Override
    public PaymentPageScreen fillTheSenderDetailsAndProceedToPaymentPage(TestExecutionContext context) {

        driver.waitTillElementIsPresent(byClickEmailToSenderSelector, 20).click();
        driver.waitTillElementIsPresent(byLastNameId, 20).sendKeys((String) context.getTestState(INDIGO_TEST_CONTEXT.LAST_NAME));
        driver.waitTillElementIsPresent(byEmailId, 20).sendKeys((String) context.getTestState(INDIGO_TEST_CONTEXT.EMAIL));
        driver.waitTillElementIsPresent(byPhoneId, 20).sendKeys((String) context.getTestState(INDIGO_TEST_CONTEXT.PHONE));
        driver.waitTillElementIsPresent(byChcTncId, 20).click();
        LOGGER.info("User proceed to payment page filling all the sender details");
        visually.checkWindow(SCREEN_NAME, "Preview page post filling all the details");

        driver.waitTillElementIsPresent(byPreviewButtonClass, 20).click();
        return PaymentPageScreen.get();
    }
}
