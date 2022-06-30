package com.znsio.sample.e2e.screen.theapp;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.android.theapp.LoginScreenAndroid;
import com.znsio.sample.e2e.screen.web.theapp.AmazonLoginScreenWeb;
import com.znsio.sample.e2e.screen.web.theapp.LoginScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class AmazonLoginScreen {

    private static final String SCREEN_NAME = AmazonLoginScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static AmazonLoginScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch (platform) {
            case web:
                return new AmazonLoginScreenWeb(driver, visually);
            case android:
                return new AmazonLoginScreenWeb(driver, visually);
            //Declared to implement later if needed
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract AmazonLoginScreen AmazonEnterLoginDetails(String username, String password);

    public abstract AmazonResultsScreen login();

    public abstract String GetLoginMessage();


}
