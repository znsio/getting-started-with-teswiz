package com.znsio.sample.e2e.screen.web.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import com.znsio.sample.e2e.screen.web.ZomatoCityPageScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndigoVoucherScreenWeb extends IndigoVoucherScreen {

    public static final By byDenominationDropdownId = By.id("SelectedVoucherValue");
    public static final By byDearTextBoxId = By.id("Per_Fname");
    public static final By byMessageTextBoxId = By.id("Message");
    public static final By byPreviewBtnClass = By.className("preview-btn");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = IndigoVoucherScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public IndigoVoucherScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public IndigoVoucherScreen selectDenomination(String denomination) {
        driver.findElement(byDenominationDropdownId).click();
        driver.findElement(By.xpath("//option[@value='3000']")).click();
        return IndigoVoucherScreen.get();
    }

    @Override
    public IndigoVoucherScreen selectQuantity(String quantity) {
        driver.findElement(By.id("SelectedVoucherQuantity")).click();
        driver.findElement(By.xpath("")).click();
        return IndigoVoucherScreen.get();
    }

    @Override
    public IndigoVoucherScreen personalizeVoucher(String name, String message) {
        driver.findElement(byDearTextBoxId).sendKeys(name);
        driver.findElement(byMessageTextBoxId).sendKeys(message);
        driver.findElement(byPreviewBtnClass).click();
        return
    }
}
