package com.znsio.sample.e2e.screen.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPreviewScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class GiftVoucherPreviewScreenWeb extends GiftVoucherPreviewScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = GiftVoucherScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byProceedButtonXpath = By.xpath("//input[@class='preview-btn pull-right']");

    public GiftVoucherPreviewScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }


    @Override
    public GiftVoucherPreviewScreen previewAndProceed() {
        LOGGER.info("Previewing voucher");
        visually.takeScreenshot(SCREEN_NAME, "Personalised gift voucher preview");
        driver.findElement(byProceedButtonXpath).click();
        return this;
    }
}
