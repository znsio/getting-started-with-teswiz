package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoPreviewVoucherScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IndigoVoucherScreenAndroid extends IndigoVoucherScreen {
    public static final By byDenominatorDropdownXpath = By.xpath("//android.view.View[@resource-id='SelectedVoucherValue']");
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoVoucherScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = "not yet implemented";


    public IndigoVoucherScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public IndigoVoucherScreen selectDenomination(int denomination) {
        driver.waitTillElementIsVisible(byDenominatorDropdownXpath,25);
        driver.findElement(byDenominatorDropdownXpath).click();
        return this;
    }

    @Override
    public IndigoVoucherScreen selectQuantity(int quantity) {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public IndigoPreviewVoucherScreen personalizeVoucher(String dear, String meesage) {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public String getTotalAmount() {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }
}
