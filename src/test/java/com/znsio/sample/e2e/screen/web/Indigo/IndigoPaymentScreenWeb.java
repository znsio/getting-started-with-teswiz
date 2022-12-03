package com.znsio.sample.e2e.screen.web.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoPaymentScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class IndigoPaymentScreenWeb extends IndigoPaymentScreen {
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = IndigoPaymentScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public IndigoPaymentScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread().getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public boolean validatePaymentScreen() {
        String paymentPageUrl = driver.getInnerDriver().getCurrentUrl();
        visually.checkWindow(SCREEN_NAME, "On Payment Page");
        LOGGER.info("On Payment Page");
        return (paymentPageUrl.contains("transaction"));
    }
}
