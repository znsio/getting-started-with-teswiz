package com.znsio.sample.e2e.screen.web.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.IndigoGiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.IndigoHomePageScreen;
import com.znsio.sample.e2e.screen.web.jiomeet.InAMeetingScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndigoHomePageWebScreen extends IndigoHomePageScreen {

    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = IndigoHomePageWebScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private final By byBookBtnXpath = By.xpath("//a[@title='Book']");
    private final By byGiftVoucherOptionXpath = By.xpath("//div[@class='nav-inner-items']//a[contains(@href,\"giftvoucher\")]");;


    public IndigoHomePageWebScreen(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public IndigoGiftVoucherScreen clickGiftVoucher() {
        LOGGER.info("Indigo site opened");
        driver.findElement(byBookBtnXpath).click();
        LOGGER.info("Book btn clicked");
        visually.checkWindow(SCREEN_NAME, "Book dropdown opened successfully");
        driver.waitTillElementIsPresent(byGiftVoucherOptionXpath,10);
        driver.findElement(byGiftVoucherOptionXpath).click();
        LOGGER.info("Gift voucher option selected");
        visually.checkWindow(SCREEN_NAME, "Gift voucher page opened successfully");
        return IndigoGiftVoucherScreen.get();
    }
}
