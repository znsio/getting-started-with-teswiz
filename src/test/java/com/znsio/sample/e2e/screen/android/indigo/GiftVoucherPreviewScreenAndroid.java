package com.znsio.sample.e2e.screen.android.indigo;

import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherPreviewScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public class GiftVoucherPreviewScreenAndroid extends GiftVoucherPreviewScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = GiftVoucherPreviewScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public GiftVoucherPreviewScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public GiftVoucherPreviewScreen previewAndProceed() {
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }
}
