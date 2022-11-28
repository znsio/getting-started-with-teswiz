package com.znsio.sample.e2e.screen.web.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoLandingScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import com.znsio.sample.e2e.screen.web.ZomatoCityPageScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IndigoLandingScreenWeb extends IndigoLandingScreen {

    public static final By byBookXpath = By.xpath("//a[@title='Book']");
    public static final By byBookDropdownXpath = By.xpath("//div[@class='col-nav-info']//div");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = ZomatoCityPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;


    public IndigoLandingScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public IndigoVoucherScreen selectGiftVoucher(String giftVoucherOption) {
        driver.findElement(byBookXpath).click();
        visually.checkWindow(SCREEN_NAME, "Book dropdown");
        List<WebElement> bookDropdown =driver.findElements(byBookDropdownXpath);
        for (WebElement bookDropDownlist : bookDropdown) {
            String bookData = bookDropDownlist.getText();
            if (bookData.equalsIgnoreCase(giftVoucherOption)) {
                bookDropDownlist.click();
                LOGGER.info(giftVoucherOption+ "clicked successfully");
                break;
            } else {
                LOGGER.error(giftVoucherOption+ "not available");
            }
        }
        return IndigoVoucherScreen.get();
    }
}
