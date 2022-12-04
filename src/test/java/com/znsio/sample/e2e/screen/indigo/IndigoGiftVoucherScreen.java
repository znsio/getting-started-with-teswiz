package com.znsio.sample.e2e.screen.indigo;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.web.indigo.IndigoGiftVoucherWebScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class IndigoGiftVoucherScreen {

    private static final String SCREEN_NAME = IndigoGiftVoucherScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static IndigoGiftVoucherScreen get() {
        Driver driver = fetchDriver(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread().getId());

        switch(platform) {
            //case android:
                //return new IndigoGiftVoucherAndroidScreen(driver, visually);
            case web:
                return new IndigoGiftVoucherWebScreen(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract IndigoGiftVoucherScreen addVoucherWithAmount(int denomination, int quantity);//Improve method name
    public abstract int fetchVoucherTotalAmount();
    public abstract IndigoGiftVoucherScreen personalizeVoucher(String name, String personalisedMessage);
    public abstract IndigoVoucherDeliveryDetailsScreen previewVoucher(String name, String personalisedMessage);
}
