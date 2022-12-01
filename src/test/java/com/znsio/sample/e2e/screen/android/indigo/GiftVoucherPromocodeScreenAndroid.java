package com.znsio.sample.e2e.screen.android.indigo;

import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPromocodeScreen;
import com.znsio.sample.e2e.screen.web.GiftVoucherPromocodeScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public class GiftVoucherPromocodeScreenAndroid extends GiftVoucherPromocodeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = GiftVoucherPreviewScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public GiftVoucherPromocodeScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public GiftVoucherPromocodeScreen applyInvalidPromocode() {
        return null;
    }

    @Override
    public GiftVoucherPromocodeScreenWeb enterDeliveryOptionDetails() {
        return null;
    }

    @Override
    public String getAmountOnPromocodeScreen() {
        return null;
    }

    @Override
    public String getPromocodeErrMsg() {
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }
}
