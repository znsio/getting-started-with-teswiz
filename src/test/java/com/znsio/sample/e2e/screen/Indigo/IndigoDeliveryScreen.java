package com.znsio.sample.e2e.screen.Indigo;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.android.Indigo.IndigoDeliveryScreenAndroid;
import com.znsio.sample.e2e.screen.web.Indigo.IndigoDeliveryScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class IndigoDeliveryScreen {

    private static final String SCREEN_NAME = IndigoDeliveryScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static IndigoDeliveryScreen get() {
        Driver driver = fetchDriver(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread().getId());

        switch (platform) {
            case android:
                return new IndigoDeliveryScreenAndroid(driver, visually);
            case web:
                return new IndigoDeliveryScreenWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract IndigoDeliveryScreen enterInvalidPromoCode(String promoCode);

    public abstract String getErrorMessage();

    public abstract int getFinalAmount();

    public abstract IndigoDeliveryScreen enterReceiverDetail(String receiverFirstName, String recoverLastName, String receiverMail, String receiverPhone);

    public abstract IndigoDeliveryScreen enterSenderDetails(String senderFirstName, String senderLastName, String senderrMail, String senderPhone);

    public abstract IndigoDeliveryScreen selectTermsAndConditions();

    public abstract String clickOnProceedBtn();
}
