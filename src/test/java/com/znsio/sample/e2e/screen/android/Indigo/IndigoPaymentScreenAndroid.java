package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoPaymentScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IndigoPaymentScreenAndroid extends IndigoPaymentScreen {
    private static final By byPaymentPageDetailsXpath = By.xpath("//android.view.View[@resource-id='sectionheading']");
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoPaymentScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public IndigoPaymentScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public boolean validatePaymentScreen() {
        String getPaymentPageDetails =  driver.waitTillElementIsPresent(byPaymentPageDetailsXpath,5).getText();
        visually.checkWindow(SCREEN_NAME, "On Payment Page");
        return (getPaymentPageDetails.contains("Payment"));
    }
}
