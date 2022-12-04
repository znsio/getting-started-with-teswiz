package com.znsio.sample.e2e.screen.web.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.IndigoGiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.IndigoVoucherDeliveryDetailsScreen;
import com.znsio.sample.e2e.screen.web.jiomeet.InAMeetingScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class IndigoGiftVoucherWebScreen extends IndigoGiftVoucherScreen {

    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = IndigoGiftVoucherWebScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byDenominationDropdownId = By.id("SelectedVoucherValue");
    private final By byVoucherQuantityDropdownId = By.id("SelectedVoucherQuantity");
    private final By byVoucherAmountId = By.id("lblTotal");
    private final By bycheckBoxPersonalId = By.id("chkPersonal");
    private final By byDeartextBoxId = By.id("Per_Fname");
    private final By byVoucherMessageTextBoxId = By.id("Message");
    private final By byPreviewBtnXpath = By.xpath("//input[@value='Preview']");
    private final By byProceedBtnXpath = By.xpath("//input[@value='Proceed']");

    public IndigoGiftVoucherWebScreen(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    public IndigoGiftVoucherScreen addVoucherWithAmount(int denomination, int quantity) {

        LOGGER.info("Selecting denomination value " + denomination);
        WebElement denominationDropdownWebElement = driver.findElement(byDenominationDropdownId);
        Select denominationDropdown = new Select(denominationDropdownWebElement);
        denominationDropdown.selectByValue(String.valueOf(denomination));
        LOGGER.info("Denomination value selected successfully");
        LOGGER.info("Selecting quantity " + quantity);
        WebElement quantityDropdownWebElement = driver.findElement(byVoucherQuantityDropdownId);
        Select quantityDropdown = new Select(quantityDropdownWebElement);
        quantityDropdown.selectByValue(String.valueOf(quantity));
        LOGGER.info("Quantity selected successfully");
        visually.checkWindow(SCREEN_NAME, "Indigo Voucher amortised successfully");

        return this;
    }

    public int fetchVoucherTotalAmount()
    {
        int displayedVoucherTotalAmount = Integer.parseInt(driver.findElement(byVoucherAmountId).getText().substring(1).trim());
        LOGGER.info("Total voucher amount displayed after selecting Denomination and quantity is: " +displayedVoucherTotalAmount);
        return displayedVoucherTotalAmount;
    }

    public IndigoGiftVoucherScreen personalizeVoucher(String name, String personalisedMessage)
    {
        LOGGER.info("Clicking I PERSONALISE checkbox");
        driver.findElement(bycheckBoxPersonalId).click();
        LOGGER.info("I PERSONALISE checkbox selected");
        if(driver.findElement(bycheckBoxPersonalId).isSelected())
        {
            driver.findElement(byDeartextBoxId).sendKeys(name);
            LOGGER.info("Name entered in the textbox is " + name);
            driver.findElement(byVoucherMessageTextBoxId).sendKeys(personalisedMessage);
            LOGGER.info("Message entered in the textbox is +" + personalisedMessage);
            visually.checkWindow(SCREEN_NAME, "Indigo Voucher personalised successfully");
            driver.findElement(byPreviewBtnXpath).submit();
            LOGGER.info("Preview btn clicked successfully");
        }
        else {
            LOGGER.error("Could not select I PERSONALISE checkbox");
        }
        return this;
    }

    public IndigoVoucherDeliveryDetailsScreen previewVoucher(String name, String personalisedMessage)
    {
        WebElement senderName = driver.findElementByXpath("//span[contains(text(),'"+name+"')]");
        WebElement voucherMessage = driver.findElementByXpath("//p[contains(text(),'"+personalisedMessage+"')]");
        if(senderName.isDisplayed() && voucherMessage.isDisplayed())
        {
            LOGGER.info("Personalised details present correctly ");
            driver.findElement(byProceedBtnXpath).click();
            LOGGER.info("Proceed btn clicked successfully.");
        }
        else
        {
            LOGGER.error("Personalised voucher details mismatched");
        }
        return IndigoVoucherDeliveryDetailsScreen.get();
    }
}
