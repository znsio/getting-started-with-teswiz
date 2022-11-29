package com.znsio.sample.e2e.screen.Indigo;

import com.znsio.sample.e2e.screen.android.Indigo.IndigoVoucherScreenAndroid;
import com.znsio.sample.e2e.screen.web.Indigo.IndigoVoucherScreenWeb;
import org.apache.log4j.Logger;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.e2e.entities.Platform;
import org.apache.commons.lang3.NotImplementedException;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class IndigoVoucherScreen {

    private static final String SCREEN_NAME = IndigoVoucherScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static IndigoVoucherScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch(platform) {
            case android:
                return new IndigoVoucherScreenAndroid(driver, visually);
            case web:
                return new IndigoVoucherScreenWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract IndigoVoucherScreen selectDenomination();

    public abstract String selectQuantity();

    public abstract IndigoPreviewVoucherScreen  personalizeVoucher(String dear, String meesage);
}
