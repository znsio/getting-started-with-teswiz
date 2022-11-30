package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoPreviewVoucherScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public class IndigoPreviewVoucherScreenAndroid extends IndigoPreviewVoucherScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoPreviewVoucherScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = "not yet implemented";


    public IndigoPreviewVoucherScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String previewVoucher() {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }
}
