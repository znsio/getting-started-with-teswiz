package com.znsio.sample.e2e.screen.web.indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class PaymentScreenWeb extends PaymentScreen {
    private final Driver driver ;
    private final Visual visually;
    private static final String SCREEN_NAME = PaymentScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byPaymentScreenXpath = By.xpath("//*[contains(text(),'Payment Information')]");
    public PaymentScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
    @Override
    public boolean landOnPaymentPage() {
        LOGGER.info("Landed on Payment Screen");
        return driver.findElements(byPaymentScreenXpath).size() > 0;
    }
}
