package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoPreviewVoucherScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import org.apache.log4j.Logger;

public class IndigoVoucherScreenAndroid extends IndigoVoucherScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoVoucherScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public IndigoVoucherScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public IndigoVoucherScreen selectDenomination(String denomination) {
        return null;
    }

    @Override
    public IndigoVoucherScreen selectQuantity(String quantity) {
        return null;
    }

    @Override
    public IndigoPreviewVoucherScreen personalizeVoucher(String dear, String meesage) {
        return null;
    }
}
