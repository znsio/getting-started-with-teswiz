package com.znsio.sample.e2e.screen.android.indigo;

import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPreviewScreen;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public class GiftVoucherScreenAndroid extends GiftVoucherScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    public GiftVoucherScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String getTotal() {
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    @Override
    public GiftVoucherPreviewScreen personaliseGiftVoucher(String amount, String quantity) {
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }
}
