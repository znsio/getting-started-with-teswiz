package com.znsio.sample.e2e.screen.android;

import com.znsio.sample.e2e.screen.ScreenShotScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;

public class ScreenShotScreenAndroid
        extends ScreenShotScreen {
    private static final String SCREEN_NAME = ScreenShotScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    public ScreenShotScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public ScreenShotScreen takeScreenshot() {
        visually.checkWindow(SCREEN_NAME, "Take Screenshot");
        return this;
    }
}
