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

    public static final By byDenominationDropdownId = By.id("SelectedVoucherValue"); //private locator
    public static final By byDearTextBoxId = By.id("Per_Fname");
    public static final By byMessageTextBoxId = By.id("Message");
    public static final By byPreviewBtnClass = By.className("preview-btn");
    public static final By byQuatityDropdownId = By.id("SelectedVoucherQuantity");
    public static final By byPersonalCheckBoxID = By.id("chkPersonal");
    public static final By byTotalAmountId = By.id("lblTotal");
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
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public IndigoVoucherScreen selectDenomination() {
        WebElement denominationDropdownWebElement = driver.findElement(byDenominationDropdownId);
        Select denominatorDropdown = new Select (denominationDropdownWebElement);
        denominatorDropdown.selectByIndex(0);
        String getDenominationValue = driver.findElement(byDenominationDropdownId).getText();
        context.addTestState(INDIGO_TEST_CONTEXT.DENOMINATION,getDenominationValue);
        LOGGER.info("Denomination selected for voucher " +getDenominationValue);
        return this;
    }

    @Override
    public String selectQuantity() {
        WebElement quantityDropdownWebElement = driver.findElement(byQuatityDropdownId);
        Select quantityDropdown = new Select (quantityDropdownWebElement);
        quantityDropdown.selectByIndex(0);
        String getQuantityValue = driver.findElement(byQuatityDropdownId).getText();
        context.addTestState(INDIGO_TEST_CONTEXT.QUANTITY,getQuantityValue);
        LOGGER.info("Quantity selected for voucher " +getQuantityValue);
        String getTotalAmount = driver.findElement(byTotalAmountId).getText();
        return getTotalAmount;
    }

    @Override
    public IndigoPreviewVoucherScreen personalizeVoucher(String name, String message) {
        driver.findElement(byPersonalCheckBoxID).click();
        if(driver.findElement(byPersonalCheckBoxID).isSelected()) {
            driver.findElement(byDearTextBoxId).sendKeys(name);
            LOGGER.info("Data entered in Dear textbox" +name);
            driver.findElement(byMessageTextBoxId).sendKeys(message);
            LOGGER.info("Data entered in Message textbox" +message);
            visually.checkWindow(SCREEN_NAME, "Indigo Voucher successfully personalised");
            driver.findElement(byPreviewBtnClass).submit();
        } else {
            LOGGER.error("Personalised checkbox not selected");
        }
        return IndigoPreviewVoucherScreen.get();
    }
}
