package com.znsio.sample.e2e.screen.web.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPreviewScreen;
import com.znsio.sample.e2e.screen.indigo.HomeScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomeScreenWeb extends HomeScreen {

    private static final Logger LOGGER = Logger.getLogger(HomeScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private final TestExecutionContext context;

    private final By byBookingDdXpath = By.xpath("//a[@title='Book']");
    private final By byGiftVoucherOptXpath = By.xpath("//a[@class='text-decoration-none']//div[text()='Gift Voucher']");


    public HomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }


    @Override
    public GiftVoucherPreviewScreen selectGiftVoucher() {
        Actions actions = new Actions(innerDriver);
        actions.moveToElement(driver.findElement(byBookingDdXpath)).perform();
        driver.waitForClickabilityOf(byGiftVoucherOptXpath).click();
        LOGGER.info("Clicked on Gift Voucher option from Book Dropdown");
        return GiftVoucherPreviewScreen.get();
    }
}
