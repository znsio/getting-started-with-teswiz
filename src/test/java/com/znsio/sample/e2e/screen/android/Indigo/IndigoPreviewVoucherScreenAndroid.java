package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoPreviewVoucherScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IndigoPreviewVoucherScreenAndroid extends IndigoPreviewVoucherScreen {
    public static final By byDearTextBoxXpath = By.xpath("//android.view.View[@resource-id='body']//android.view.View[2]//android.view.View[4]");
    public static final By byMessageTextBoxXpath = By.xpath("//android.view.View[@resource-id='body']//android.view.View[2]//android.view.View[5]");
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoPreviewVoucherScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public IndigoPreviewVoucherScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String previewVoucher() {
        String dearText = driver.findElement(byDearTextBoxXpath).getText().trim();
        String message = driver.findElement(byMessageTextBoxXpath).getText().trim();
        visually.checkWindow(SCREEN_NAME, "Indigo Voucher Preview");
        driver.scrollDownByScreenSize();
        driver.findElement(By.xpath("//android.widget.Button[contains(@text, 'Proceed')]")).click();
        LOGGER.info("Proceed Button clicked on Preview page");
        return dearText + " " + message;
        }
}
