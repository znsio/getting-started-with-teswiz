package com.znsio.sample.e2e.screen.web.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoPromoCodeScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndigoPromoCodeScreenWeb extends IndigoPromoCodeScreen {

    public static final By byPromoCodeTextBoxId = By.id("PromoCode");
    public static final By byPromoCodeProceedBtnId = By.id("btnApplyPromoCode");
    public static final By byErrorMessageXpath = By.xpath("//div[contains(text(),'Invali')]");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = IndigoPromoCodeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public IndigoPromoCodeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public IndigoPromoCodeScreen enterInvalidPromoCode() {
        driver.findElement(byPromoCodeTextBoxId).sendKeys("00000");
        LOGGER.info("Invalid Promo code entered in Delivery option page");
        driver.findElement(byPromoCodeProceedBtnId).submit();
        return this;
    }

    @Override
    public String getErrorMessage() {
        String errorMessage  = driver.findElement(byErrorMessageXpath).getText();
        return errorMessage;
    }
}
