package com.znsio.sample.e2e.screen.web.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.INDIGO_VOUCHER_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class GiftVoucherScreenWeb extends GiftVoucherScreen {
    private final By byQuantityDropdownId = By.id("SelectedVoucherQuantity");
    private final By byAmountDropdownId = By.id("SelectedVoucherValue");
    private final By bytotalAmountXpath = By.xpath("//span[@id='lblTotal']");
    private final By byMakeItPersonalId = By.xpath("//input[@id='chkPersonal']");
    private final By byPersonalNameId = By.xpath("//input[@id='Per_Fname']");
    private final By byPersonalMessageId = By.xpath("//textarea[@id='Message']");
    private final By byPreviewXpath = By.xpath("//input[@value='Preview']");

    private final By byProceedXpath = By.xpath("//input[@value='Proceed']");
    private static final By byPaymentDetailsId = By.id("paymentinformation");


    private final By byFirstNameXpath = By.xpath("//div[@class=\"label-value\"]//input[@id='Per_Fname']");
    private final By byLastNameId = By.id("Per_Lname");
    private final By byEmailAddId = By.id("Per_EmailID");
    private final By byPhoneId = By.id("Per_Phone");
    private final By bySenderXpath = By.xpath("//label[@for=\"radio-2\"]");
    private final By byTncId = By.id("chkTnC");
    private final By byPayNowClass = By.className("preview-btn");
    private final By byPromoCodexpath = By.xpath(("//*[@id=\"PromoCode\"]"));
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = GiftVoucherScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public GiftVoucherScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }


    @Override
    public GiftVoucherScreen configureVoucher(String quantity, String amount){
        driver.scrollTillElementIntoView(byAmountDropdownId);
        LOGGER.info("select "+amount+" denominations in gift voucher screen");
        WebElement denominationDropdownWebElement = innerDriver.findElement(byAmountDropdownId);
        Select denominationDropdown = new Select(denominationDropdownWebElement);
        denominationDropdown.selectByValue(amount);

        LOGGER.info("select "+quantity+" quantities in gift voucher screen");
        WebElement quantityDropdownWebElement = innerDriver.findElement(byQuantityDropdownId);
        Select quantityDropdown = new Select(quantityDropdownWebElement);
        quantityDropdown.selectByValue(quantity);
        visually.checkWindow(SCREEN_NAME,"Configured Voucher");

        return this;
    }

    @Override
    public GiftVoucherScreen personaliseGiftVoucher(Map RecipientData) {

        LOGGER.info("Personalise gift voucher with details "+ RecipientData.toString());
        driver.scrollTillElementIntoView(byMakeItPersonalId);
        WebElement makeItPersonalWebElement = driver.waitTillElementIsPresent(byMakeItPersonalId);
        if(!makeItPersonalWebElement.isSelected())
            makeItPersonalWebElement.click();
        driver.waitTillElementIsPresent(byPersonalNameId).sendKeys((CharSequence) RecipientData.get(INDIGO_VOUCHER_CONTEXT.RECEIVER));

        driver.waitTillElementIsPresent(byPersonalMessageId).sendKeys((CharSequence) RecipientData.get(INDIGO_VOUCHER_CONTEXT.MESSAGE));
        visually.checkWindow(SCREEN_NAME,"Personalise Gift Voucher");
        return this;
    }
        @Override
         public boolean previewVoucher() {
            LOGGER.info("Click Preview and Proceed ");
            driver.scrollToBottom();
            driver.waitTillElementIsPresent(byPreviewXpath).click();
            visually.checkWindow(SCREEN_NAME,"Preview Voucher");
            driver.findElement(byProceedXpath).click();
            driver.waitTillElementIsPresent(byPromoCodexpath,10);
            if(driver.isElementPresent(byPromoCodexpath)){
                return true;
            }else
            {
                return false;
            }


    }



}

