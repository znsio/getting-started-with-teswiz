package com.znsio.sample.e2e.screen.web.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoLandingScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class IndigoLandingScreenWeb extends IndigoLandingScreen {

    private static final By byBookXpathDropdown = By.xpath("//a[@title='Book']");
    private static final By byGiftVoucherXpath = By.xpath("//div[@class='nav-inner-items']//a[contains(@href,\"giftvoucher\")]");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = IndigoLandingScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;


    public IndigoLandingScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public IndigoVoucherScreen selectGiftVoucher() {
        driver.findElement(byBookXpathDropdown).click();
        LOGGER.info("Indigo site opened");
        visually.checkWindow(SCREEN_NAME, "Book dropdown clicked successfully");
        driver.waitTillElementIsPresent(byGiftVoucherXpath).click();
        LOGGER.info("Gift voucher option selected");
        return IndigoVoucherScreen.get();
    }
}
