package com.znsio.sample.e2e.screen.android.indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPreviewScreen;
import com.znsio.sample.e2e.screen.indigo.HomeScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class HomeScreenAndroid
        extends HomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = HomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byContinueAsGuestBtnXpath = By.xpath("//android.widget.Button[contains(@resource-id,'button_as_guest')]");
    private final By byGiftVoucherXpath = By.xpath("//android.widget.TextView[@text='Gift voucher']/preceding-sibling::android.widget.ImageView");

    public HomeScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public GiftVoucherPreviewScreen selectGiftVoucher() {
        LOGGER.info("Launching Indigo Application");
        driver.waitTillElementIsPresent(byContinueAsGuestBtnXpath,40).click();
        visually.takeScreenshot(SCREEN_NAME,"Indigo Home Screen");
        LOGGER.info("Clicked on Continue As Guest button");
        driver.waitTillElementIsPresent(byGiftVoucherXpath,15).click();
        LOGGER.info("Clicked on Gift Voucher");
        return GiftVoucherPreviewScreen.get();
    }
}
