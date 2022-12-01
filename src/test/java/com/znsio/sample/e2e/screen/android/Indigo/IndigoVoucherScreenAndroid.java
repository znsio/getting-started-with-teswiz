package com.znsio.sample.e2e.screen.android.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.Indigo.IndigoPreviewVoucherScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.List;

public class IndigoVoucherScreenAndroid extends IndigoVoucherScreen {
    public static final By byDenominatorDropdownXpath = By.xpath("//android.view.View[@resource-id='SelectedVoucherValue']");
    public static final By byDenominationValueIndexXpath = By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1']");
    public static final By byQuantityDropdownXpath = By.xpath("//android.view.View[@resource-id='SelectedVoucherQuantity']");
    public static final By byQuantityValueIndexXpath = By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1']");
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
        driver.waitTillElementIsVisible(byDenominatorDropdownXpath,25);
        driver.findElement(byDenominatorDropdownXpath).click();
        driver.waitTillElementIsVisible(byDenominationValueIndexXpath,10);
        (driver.findElements(byDenominationValueIndexXpath)).get(denomination).click();
        String denominationValue = driver.findElement(byDenominatorDropdownXpath).getText();
        int getDenominationValue = Integer.parseInt(denominationValue);
        context.addTestState(INDIGO_TEST_CONTEXT.DENOMINATION, getDenominationValue);
        LOGGER.info("Denomination selected for voucher " + getDenominationValue);
        return this;
    }

    @Override
    public IndigoVoucherScreen selectQuantity(int quantity) {
        driver.waitTillElementIsVisible(byQuantityDropdownXpath,25);
        driver.findElement(byQuantityDropdownXpath).click();
        driver.waitTillElementIsVisible(byQuantityValueIndexXpath,10);
        (driver.findElements(byQuantityValueIndexXpath)).get(quantity).click();
        String denominationValue = driver.findElement(byQuantityDropdownXpath).getText();
        int getQuantityValue = Integer.parseInt(denominationValue);
        context.addTestState(INDIGO_TEST_CONTEXT.QUANTITY, getQuantityValue);
        LOGGER.info("Quantity selected for voucher " + getQuantityValue);
        return this;
    }

    @Override
    public IndigoPreviewVoucherScreen personalizeVoucher(String dear, String meesage) {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public String getTotalAmount() {
        driver.scrollDownByScreenSize();
        return "hello";
    }
}
