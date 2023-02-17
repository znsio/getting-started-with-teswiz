package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.android.calculator.CalculatorScreenAndroid;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.IndigoHomeScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IndigoHomeScreenAndroid extends IndigoHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private final By continueAsGuestId = By.id("in.goindigo.android:id/button_as_guest");
    private final By homePageId = By.id("in.goindigo.android:id/home");
    private final By giftVoucherXpath = By.xpath("//android.widget.TextView[contains(@text, 'Gift')]//preceding-sibling::android.widget.ImageView");

    public IndigoHomeScreenAndroid(Driver driver, Visual visually) {
        LOGGER.info("Setting up driver");
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public GiftVoucherScreen goToGiftVoucherSection() {
        driver.waitForClickabilityOf(continueAsGuestId).click();
        visually.checkWindow(SCREEN_NAME, "Home screen for guest user");
        LOGGER.info("Navigating to Gift Voucher page");
        driver.waitForClickabilityOf(homePageId).click();
        driver.waitForClickabilityOf(giftVoucherXpath).click();
        visually.checkWindow(SCREEN_NAME, "Moved to GiftVoucher Section");
        return GiftVoucherScreen.get();
    }
}
