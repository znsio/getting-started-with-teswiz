package com.znsio.sample.e2e.screen.web.indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.IndigoHomeScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IndigoHomeScreenWeb extends IndigoHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    //locator - static
    private final By bookDropdownXpath = By.xpath("//a[@title='Book']");
    private final By giftVoucherOptionXpath = By.xpath("//a[@class='text-decoration-none']//div[text()='Gift Voucher']");

    public IndigoHomeScreenWeb(Driver driver, Visual visually) {
        LOGGER.info("Setting up Driver");
        this.driver = driver;
        this.visually = visually;
    }
    @Override
    public GiftVoucherScreen goToGiftVoucherSection() {
        LOGGER.info("Navigating to Gift Voucher page");
        driver.waitForClickabilityOf(bookDropdownXpath).click();
        driver.findElement(giftVoucherOptionXpath).click();
        visually.checkWindow(SCREEN_NAME,"Moved to GiftVoucher Section");
        return GiftVoucherScreen.get();
    }
}
