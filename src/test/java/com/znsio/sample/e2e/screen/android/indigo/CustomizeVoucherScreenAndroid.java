package com.znsio.sample.e2e.screen.android.indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.CustomizeVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.PreviewVoucherScreen;
import org.apache.log4j.Logger;

public class CustomizeVoucherScreenAndroid extends CustomizeVoucherScreen {

    private static final String SCREEN_NAME = CustomizeVoucherScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    public CustomizeVoucherScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    // Methods needed to be implemented.

    @Override
    public PreviewVoucherScreen customizeVoucher(String amount, String quantity) {
        return null;
    }
}
