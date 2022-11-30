package com.znsio.sample.e2e.screen.web.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.PaymentDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentDetailsScreenWeb extends PaymentDetailsScreen {

    private static final Logger LOGGER = Logger.getLogger(PaymentDetailsScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private final TestExecutionContext context;

    private final By byPaymentInfoTextXpath = By.xpath("//div[@id='sectionheading']/span[text()='Payment Information']");

    public PaymentDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }


    @Override
    public boolean checkingUserPaymentInformationPage() {
        driver.waitTillElementIsVisible(byPaymentInfoTextXpath,2);
        return driver.findElement(byPaymentInfoTextXpath).isDisplayed();
    }
}
