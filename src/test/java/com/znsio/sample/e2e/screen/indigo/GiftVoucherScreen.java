package com.znsio.sample.e2e.screen.indigo;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.web.GiftVoucherScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.Map;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class GiftVoucherScreen {
        private static final String SCREEN_NAME = GiftVoucherScreen.class.getSimpleName();
        private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static GiftVoucherScreen get() {
            Driver driver = fetchDriver(Thread.currentThread()
                    .getId());
            Platform platform = Runner.fetchPlatform(Thread.currentThread()
                    .getId());
            LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
            Visual visually = fetchEyes(Thread.currentThread()
                    .getId());

            switch(platform) {
                case web:
                    return new GiftVoucherScreenWeb(driver, visually);
            }
            throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract GiftVoucherScreen selectQuantity(String quantity);
    public abstract GiftVoucherScreen selectDenomination(String denominations);
    public abstract GiftVoucherScreen personaliseGiftVoucher(Map senderDerails);
    public abstract GiftVoucherScreen previewGiftVoucher();
    public abstract GiftVoucherScreen applyPromoCode(String promocode);
    public abstract int getTotalAmount();
    public abstract String getPromoCodeMessage();
    public abstract GiftVoucherScreen selectDeliveryOption(String option);
    public abstract GiftVoucherScreen enterSenderDetails(Map sendersDetails);
    public abstract GiftVoucherScreen checkTermsAndConditions(boolean check);
    public abstract PaymentScreen clickPayNow();
}
