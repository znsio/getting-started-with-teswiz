package com.znsio.sample.e2e.screen.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class PaymentScreenWeb extends PaymentScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = PaymentScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byPageHeadingId = By.id("sectionheading");
    private final By byPaymentAmountId = By.id("amount");


    public PaymentScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }


    @Override
    public String getPaymentPageHeading() {
        LOGGER.info("Payment Page is Displayed");
        String paymentPageHeading = driver.findElement(byPageHeadingId).getText();
        return paymentPageHeading;
    }

    public PaymentScreen getPaymentAmount(){
        LOGGER.info("Payment Page is Displayed");
        visually.checkWindow(SCREEN_NAME, "Indigo Home Page");
        String paymentAmount = driver.findElement(byPaymentAmountId).getText();
        System.out.println(paymentAmount);
        return this;
    }
}
