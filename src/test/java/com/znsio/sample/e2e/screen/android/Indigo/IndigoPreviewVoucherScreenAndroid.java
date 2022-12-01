package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoPreviewVoucherScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IndigoPreviewVoucherScreenAndroid extends IndigoPreviewVoucherScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoPreviewVoucherScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = "not yet implemented";


    public IndigoPreviewVoucherScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String previewVoucher() {
        String dearText = driver.findElement(By.xpath("//android.view.View[@resource-id='body']//android.view.View[2]//android.view.View[4]")).getText().trim();
        String message = driver.findElement(By.xpath("//android.view.View[@resource-id='body']//android.view.View[2]//android.view.View[5]")).getText().trim();
        driver.scrollDownByScreenSize();
        driver.findElement(By.xpath("//android.widget.Button[2]\\n"));
        return  dearText;
        }
}
