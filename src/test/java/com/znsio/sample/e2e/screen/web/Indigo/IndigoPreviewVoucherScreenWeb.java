package com.znsio.sample.e2e.screen.web.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoPreviewVoucherScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndigoPreviewVoucherScreenWeb extends IndigoPreviewVoucherScreen {

    private static final By byProceedBtnXpath = By.xpath("//input[@value='Proceed']");
    private static final By byDearTextBoxxpath = By.xpath("//div[@class='title-name']");
    private static final By byMessageTextBoxxpath = By.xpath("//div[@class='doted-line']/preceding-sibling::p");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = IndigoPreviewVoucherScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public IndigoPreviewVoucherScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public String previewVoucher() {
        String dearText = driver.findElement(byDearTextBoxxpath).getText().trim();
        String message = driver.findElement(byMessageTextBoxxpath).getText().trim();
        visually.checkWindow(SCREEN_NAME, "Indigo Voucher Preview");
        driver.findElement(byProceedBtnXpath).click();
        LOGGER.info("Proceed Button clicked on Preview page");
        return dearText + " " + message;
    }
}
