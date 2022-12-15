package com.znsio.sample.e2e.screen.indigo.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.HomePageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class HomePageScreenWeb
        extends HomePageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = HomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byBookDropdownXpath = By.xpath("//a[@title='Book']");
    private static final By byGiftVoucherXpath = By.xpath("//a[@class='text-decoration-none']//div[@class='flight'][normalize-space()='Gift Voucher']");

    public HomePageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }


    @Override
    public GiftVoucherScreen navigateToGiftVoucherScreen() {

        LOGGER.info("User click on gift voucher from dropdown and navigating to gift voucher screen");
        visually.checkWindow(SCREEN_NAME, "Home Page Screen");

        driver.moveToElement(byBookDropdownXpath);
        driver.waitTillElementIsPresent(byGiftVoucherXpath).click();
        return GiftVoucherScreen.get();
    }
}
