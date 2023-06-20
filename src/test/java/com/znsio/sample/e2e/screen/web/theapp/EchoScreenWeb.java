package com.znsio.sample.e2e.screen.web.theapp;

import com.znsio.sample.e2e.screen.theapp.EchoScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;

public class EchoScreenWeb
        extends EchoScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = EchoScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    public EchoScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public EchoScreen echoMessage(String message) {
        LOGGER.info("Skipping this step for Web");
        return this;
    }
}
