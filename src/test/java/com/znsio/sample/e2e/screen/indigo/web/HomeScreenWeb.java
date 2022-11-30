package com.znsio.sample.e2e.screen.indigo.web;

import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.android.calculator.CalculatorScreenAndroid;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.HomeScreen;
import com.znsio.sample.e2e.steps.IndigoFlightSteps;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomeScreenWeb extends HomeScreen {

    private static final Logger LOGGER = Logger.getLogger(HomeScreen.class.getName());

    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;

    private final By byBookDropdownXpath = By.xpath("//a[@title='Book']");

    private final By byGiftVoucherXpath = By.xpath("//div[@class='nav-inner-items']//a[contains(@href,\"giftvoucher\")]");

    public HomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        innerDriver = this.driver.getInnerDriver();
    }

    @Override
    public GiftVoucherScreen navigateToGiftVouchers() {

        LOGGER.info("Navigate to Gift Voucher screen");
        Actions action = new Actions(innerDriver);
        action.moveToElement(driver.findElement(byBookDropdownXpath)).build().perform();

        driver.waitTillElementIsPresent(byGiftVoucherXpath).click();
        return new GiftVoucherScreenWeb(this.driver, this.visually);
    }
}
