package com.znsio.sample.e2e.screen.indigo.android;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.android.calculator.CalculatorScreenAndroid;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.HomeScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class HomeScreenAndroid extends HomeScreen {

    private static final Logger LOGGER = Logger.getLogger(HomeScreenAndroid.class.getName());
    private final Driver driver;
    private final Visual visually;

    private static final By byContinueAsGuest = By.id("in.goindigo.android:id/button_as_guest");
    private static final By byGiftVoucherId = By.xpath("//android.widget.TextView[contains(@text,'Gift Voucher')]//preceding-sibling::android.widget.ImageView");
    public HomeScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public GiftVoucherScreen navigateToGiftVouchers() {
        LOGGER.info("Navigate to gift voucher screen");

        driver.waitTillElementIsPresent(byContinueAsGuest).click();

        driver.waitTillElementIsPresent(byGiftVoucherId).click();

        return new GiftVoucherScreenAndroid(this.driver, this.visually);
    }
}
