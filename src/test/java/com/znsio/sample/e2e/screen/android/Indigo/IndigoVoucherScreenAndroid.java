package com.znsio.sample.e2e.screen.android.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.Indigo.IndigoPreviewVoucherScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IndigoVoucherScreenAndroid extends IndigoVoucherScreen {
    private static final By byDenominatorDropdownXpath = By.xpath("//android.view.View[@resource-id='SelectedVoucherValue']");
    private static final By byDenominationValueIndexXpath = By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1']");
    private static final By byQuantityDropdownXpath = By.xpath("//android.view.View[@resource-id='SelectedVoucherQuantity']");
    private static final By byQuantityValueIndexXpath = By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1']");
    private static final By byTotalAmountXpath = By.xpath("//android.view.View[@resource-id='lblTotal']");
    private static final By byPersonalCheckBoxXpath = By.xpath("//android.widget.CheckBox[@resource-id='chkPersonal']");
    private static final By byDearTextBoxXpath = By.xpath("//android.widget.EditText[@resource-id='Per_Fname']");
    private static final By byMessageTextBoxXpath = By.xpath("//android.widget.EditText[@resource-id='Message']");
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoVoucherScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = "not yet implemented";
    private final TestExecutionContext context;


    public IndigoVoucherScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);

    }

    @Override
    public IndigoVoucherScreen selectDenomination(int denomination) {
        driver.waitTillElementIsVisible(byDenominatorDropdownXpath, 25);
        driver.findElement(byDenominatorDropdownXpath).click();
        driver.waitTillElementIsVisible(byDenominationValueIndexXpath, 10);
        (driver.findElements(byDenominationValueIndexXpath)).get(denomination).click();
        String denominationValue = driver.findElement(byDenominatorDropdownXpath).getText();
        int getDenominationValue = Integer.parseInt(denominationValue);
        context.addTestState(INDIGO_TEST_CONTEXT.DENOMINATION, getDenominationValue);
        LOGGER.info("Denomination selected for voucher " + getDenominationValue);
        return this;
    }

    @Override
    public IndigoVoucherScreen selectQuantity(int quantity) {
        driver.waitTillElementIsVisible(byQuantityDropdownXpath, 25);
        driver.findElement(byQuantityDropdownXpath).click();
        driver.waitTillElementIsVisible(byQuantityValueIndexXpath, 10);
        (driver.findElements(byQuantityValueIndexXpath)).get(quantity).click();
        String denominationValue = driver.findElement(byQuantityDropdownXpath).getText();
        int getQuantityValue = Integer.parseInt(denominationValue);
        context.addTestState(INDIGO_TEST_CONTEXT.QUANTITY, getQuantityValue);
        LOGGER.info("Quantity selected for voucher " + getQuantityValue);
        return this;
    }

    @Override
    public IndigoPreviewVoucherScreen personalizeVoucher(String name, String message) {
        driver.findElement(byPersonalCheckBoxXpath).click();
        visually.checkWindow(SCREEN_NAME, "Personalise check box");
        driver.findElement(byDearTextBoxXpath).clear();
        driver.findElement(byDearTextBoxXpath).sendKeys(name);
        context.addTestState(INDIGO_TEST_CONTEXT.DEAR, name);
        LOGGER.info("Data entered in Dear textbox" + name);
        driver.scrollDownByScreenSize();
        driver.findElement(byMessageTextBoxXpath).clear();
        driver.findElement(byMessageTextBoxXpath).sendKeys(message);
        context.addTestState(INDIGO_TEST_CONTEXT.MESSAGE, message);
        LOGGER.info("Data entered in Message textbox" + message);
        visually.checkWindow(SCREEN_NAME, "Indigo Voucher successfully personalised");
        driver.findElement(By.xpath("//android.widget.Button[contains(@text, 'Preview')]")).click();
        return IndigoPreviewVoucherScreen.get();
    }

    @Override
    public int getTotalAmount() {
        driver.scrollDownByScreenSize();
        String totalAmount = driver.findElement(byTotalAmountXpath).getText();
        String totalSum = totalAmount.substring(1).trim();
        LOGGER.info("Total amount after selecting Denominator and quantity" + totalSum);
        int getTotalAmount = Integer.parseInt(totalSum);
        return getTotalAmount;
    }
}
