package com.znsio.sample.e2e.screen.web;

import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import com.znsio.sample.e2e.screen.ScreenShotScreen;
import org.apache.log4j.Logger;

public class ScreenShotScreenWeb
        extends ScreenShotScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ScreenShotScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public ScreenShotScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public ScreenShotScreen takeScreenshot() {
        visually.checkWindow(SCREEN_NAME, "Take Screenshot");
        return this;
    }
}
