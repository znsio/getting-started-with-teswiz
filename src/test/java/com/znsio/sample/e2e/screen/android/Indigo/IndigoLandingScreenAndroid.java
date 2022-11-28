package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoLandingScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import org.apache.log4j.Logger;

public class IndigoLandingScreenAndroid extends IndigoLandingScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoLandingScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public IndigoLandingScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public IndigoVoucherScreen selectGiftVoucher(String option) {
        return null;
    }
}
