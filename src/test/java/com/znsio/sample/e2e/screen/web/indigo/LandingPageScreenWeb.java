package com.znsio.sample.e2e.screen.web.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.LandingPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class LandingPageScreenWeb extends LandingPageScreen {
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = GiftVoucherScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private final By byBookDropdownXpath = By.xpath("//a[@title='Book']");
    private final By byGiftVoucherXpath = By.xpath("//div[@class='nav-inner-items']//a[contains(@href,\"giftvoucher\")]");

    public LandingPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }
    @Override
    public GiftVoucherScreenWeb navigateToGiftVoucher(){

        LOGGER.info("Navigate to Gift Voucher screen");
        Actions action = new Actions(innerDriver);
        action.moveToElement(driver.findElement(byBookDropdownXpath)).build().perform();

        driver.waitTillElementIsPresent(byGiftVoucherXpath).click();
        return new GiftVoucherScreenWeb(this.driver, this.visually);

    }
}
