package com.znsio.sample.e2e.screen.android.indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.PaymentDetailsScreen;
import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class PaymentDetailsScreenAndroid
        extends PaymentDetailsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = PaymentDetailsScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byPaymentInfoTextXpath = By.xpath("//android.view.View[@text='Payment Information']");

    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    public PaymentDetailsScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public boolean checkingUserPaymentInformation() {
        driver.waitTillElementIsPresent(byPaymentInfoTextXpath,10);
        visually.takeScreenshot(SCREEN_NAME,"Payment information screen");
        return driver.findElement(byPaymentInfoTextXpath).isDisplayed();
    }
}
