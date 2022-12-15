package com.znsio.sample.e2e.screen.indigo.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.PaymentPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
public class PaymentPageScreenWeb
        extends PaymentPageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = PaymentPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byTotalAmountSelector = By.cssSelector(".orderTotal.highlight-text");

    public PaymentPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }
    @Override
    public Double getFinalAmount() {
        visually.checkWindow(SCREEN_NAME, "Payment Page");
        LOGGER.info("User is on payment page");
        return Double.parseDouble(driver.waitTillElementIsPresent(byTotalAmountSelector).getText().split(" ")[1]);
    }
}
