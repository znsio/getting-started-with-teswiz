package com.znsio.sample.e2e.screen.indigo.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.HomeScreen;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentScreenWeb extends PaymentScreen {

    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;

    private By byPaymentAmountXpath = By.xpath("//div[contains(@class,'content-active')]//div[@id='amount']//strong");

    public PaymentScreenWeb(Driver driver, Visual visually) {

        this.driver = driver;
        this.visually = visually;
        innerDriver = this.driver.getInnerDriver();
    }

    @Override
    public int getPaymentAmount() {

        String amount =  driver.waitTillElementIsPresent(byPaymentAmountXpath).getText();
        return Integer.parseInt(amount.substring(4, amount.length() - 3));
    }
}
