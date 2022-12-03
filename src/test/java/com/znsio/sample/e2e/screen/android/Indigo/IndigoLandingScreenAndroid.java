package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoLandingScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IndigoLandingScreenAndroid extends IndigoLandingScreen {
    private static final By byGiftVoucherXpath = By.xpath("//android.widget.TextView[contains(@text, 'Gift voucher')]/..");
    private static final By byClickOnGuestUserId = By.id("button_as_guest");
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoLandingScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public IndigoLandingScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public IndigoVoucherScreen selectGiftVoucher() {
        LOGGER.info("Indigo Site opened");
        driver.waitTillElementIsVisible(byClickOnGuestUserId).click();
        visually.checkWindow(SCREEN_NAME, "Indigo Landing Screen");
        driver.waitTillElementIsPresent(byGiftVoucherXpath).click();
        LOGGER.info("Gift voucher option selected successfully");
        visually.checkWindow(SCREEN_NAME, "Indigo Gift Voucher Selection Screen");
        return IndigoVoucherScreen.get();
    }
}
