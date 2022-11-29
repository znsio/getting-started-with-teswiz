package com.znsio.sample.e2e.screen.Indigo;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.android.Indigo.IndigoPromoCodeScreenAndroid;
import com.znsio.sample.e2e.screen.web.Indigo.IndigoPromoCodeScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class IndigoPromoCodeScreen {

    private static final String SCREEN_NAME = IndigoPromoCodeScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static IndigoPromoCodeScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch(platform) {
            case android:
                return new IndigoPromoCodeScreenAndroid(driver, visually);
            case web:
                return new IndigoPromoCodeScreenWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract IndigoPromoCodeScreen enterInvalidPromoCode();

    public abstract String getErrorMessage();

    public abstract String getFinalAmount();

    public abstract IndigoPromoCodeScreen enterReceiverDetail(String receiverFirstName, String recoverLastName, String receiverMail, String receiverPhone);
    public abstract IndigoPromoCodeScreen enterSenderDetails(String senderFirstName, String senderLastName, String senderrMail, String senderPhone);

}
