package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.PaymentScreen;
import com.znsio.sample.e2e.screen.web.indigo.PaymentScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class PaymentScreenAndroid extends PaymentScreen {
    private final Driver driver ;
    private final Visual visually;
    private static final String SCREEN_NAME = PaymentScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byPaymentScreenXpath = By.xpath("//android.widget.TextView[contains(@text,'Payment Information')]");
    public PaymentScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
    @Override
    public boolean landOnPaymentPage() {
        LOGGER.info("Landed on Payment Screen");
        visually.checkWindow(SCREEN_NAME,"Moved to Payment Screen");
        return driver.findElements(byPaymentScreenXpath).size() > 0;
    }
}
