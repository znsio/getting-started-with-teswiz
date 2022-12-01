package com.znsio.sample.e2e.screen.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.IndigoHomePage;
import com.znsio.sample.e2e.screen.web.jiomeet.InAMeetingScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class indigoHomePageWeb extends IndigoHomePage {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = InAMeetingScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byBookMenuXpath = By.xpath("//a[@title='Book']");
    private final By byGiftVoucherMenuXpath = By.xpath("//a[@class='text-decoration-none']//div[text()='Gift Voucher']");


    public indigoHomePageWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public GiftVoucherScreen goToGiftVoucher() {
        LOGGER.info("Home Page is displayed");
        visually.checkWindow(SCREEN_NAME, "Indigo Home Page");
        driver.findElement(byBookMenuXpath).click();
        driver.findElement(byGiftVoucherMenuXpath).click();
        return GiftVoucherScreen.get();
    }
}
