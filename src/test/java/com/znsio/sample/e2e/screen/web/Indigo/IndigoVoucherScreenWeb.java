package com.znsio.sample.e2e.screen.web.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.Indigo.IndigoPreviewVoucherScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class IndigoVoucherScreenWeb extends IndigoVoucherScreen {

    private static final By byDenominationDropdownId = By.id("SelectedVoucherValue");
    private static final By byDearTextBoxId = By.id("Per_Fname");
    private static final By byMessageTextBoxId = By.id("Message");
    private static final By byPreviewBtnClass = By.className("preview-btn");
    private static final By byQuatityDropdownId = By.id("SelectedVoucherQuantity");
    private static final By byPersonalCheckBoxID = By.id("chkPersonal");
    private static final By byTotalAmountId = By.id("lblTotal");
    private static final By byDenominatorTextXpath = By.xpath("(//span[@class='holder'])[1]");
    private static final By byQuantityTextXpath = By.xpath("(//span[@class='holder'])[2]");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = IndigoVoucherScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public IndigoVoucherScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public IndigoVoucherScreen selectDenomination() {
        WebElement denominationDropdownWebElement = driver.findElement(byDenominationDropdownId);
        Select denominatorDropdown = new Select(denominationDropdownWebElement);
        denominatorDropdown.selectByIndex(1);
        String getDenominationValue = driver.findElement(byDenominatorTextXpath).getText();
        context.addTestState(INDIGO_TEST_CONTEXT.DENOMINATION, getDenominationValue);
        LOGGER.info("Denomination selected for voucher " + getDenominationValue);
        return this;
    }

    @Override
    public IndigoVoucherScreen selectQuantity() {
        WebElement quantityDropdownWebElement = driver.findElement(byQuatityDropdownId);
        Select quantityDropdown = new Select(quantityDropdownWebElement);
        quantityDropdown.selectByIndex(1);
        String getQuantityValue = driver.findElement(byQuantityTextXpath).getText();
        context.addTestState(INDIGO_TEST_CONTEXT.QUANTITY, getQuantityValue);
        LOGGER.info("Quantity selected for voucher " + getQuantityValue);
        return this;
    }

    @Override
    public IndigoPreviewVoucherScreen personalizeVoucher(String name, String message) {
        driver.findElement(byPersonalCheckBoxID).click();
        if (driver.findElement(byPersonalCheckBoxID).isSelected()) {
            driver.findElement(byDearTextBoxId).sendKeys(name);
            context.addTestState(INDIGO_TEST_CONTEXT.DEAR, name);
            LOGGER.info("Data entered in Dear textbox" + name);
            driver.findElement(byMessageTextBoxId).sendKeys(message);
            context.addTestState(INDIGO_TEST_CONTEXT.MESSAGE, message);
            LOGGER.info("Data entered in Message textbox" + message);
            visually.checkWindow(SCREEN_NAME, "Indigo Voucher successfully personalised");
            driver.findElement(byPreviewBtnClass).submit();
        } else {
            LOGGER.error("Personalised checkbox not selected");
        }
        return IndigoPreviewVoucherScreen.get();
    }

    @Override
    public String getTotalAmount() {
        String getTotalAmount = driver.findElement(byTotalAmountId).getText();
        LOGGER.info("Total amount after selecting Denominator and quantity" +getTotalAmount);
        return getTotalAmount;
    }
}
