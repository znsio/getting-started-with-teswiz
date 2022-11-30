package com.znsio.sample.e2e.screen.android.indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.PreviewVoucherScreen;
import org.apache.log4j.Logger;

public class PreviewVoucherScreenAndroid extends PreviewVoucherScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(PreviewVoucherScreenAndroid.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = PreviewVoucherScreenAndroid.class.getSimpleName();
    public PreviewVoucherScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Indigo Voucher Preview Screen");
    }

//    Methods needed to be implemented
    @Override
    public String voucherDetails() {
        return null;
    }

    @Override
    public String applyAndVerifyPromoCode() {
        return null;
    }

    @Override
    public String fillDetailsAndPay() {
        return null;
    }
}

