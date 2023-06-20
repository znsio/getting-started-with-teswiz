package com.znsio.sample.e2e.screen.android.vodqa;

import com.znsio.sample.e2e.screen.vodqa.VodqaScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import com.znsio.teswiz.tools.cmd.CommandLineExecutor;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;


public class VodqaScreenAndroid extends VodqaScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = VodqaScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(VodqaScreenAndroid.class.getName());
    private final By byLoginButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='login']/android.widget.Button");


    public VodqaScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public VodqaScreen login() {
        driver.waitTillElementIsPresent(byLoginButton);
        visually.checkWindow(SCREEN_NAME, "Login Screen");
        driver.findElement(byLoginButton).click();
        return this;
    }

    @Override
    public VodqaScreen putAppInTheBackground(int time) {
        driver.putAppInBackgroundFor(time);
        visually.checkWindow(SCREEN_NAME, "App screen should visible after putting app in background");
        return this;
    }

    @Override
    public boolean isAppWorkingInBackground() {
        LOGGER.info("Validating current app package to know app work in background");
        String adbCommand = "adb shell dumpsys window | grep -E 'mCurrentFocus'";
        LOGGER.info(adbCommand);
        String currentOpenApp = CommandLineExecutor.execCommand(new String[]{adbCommand}).toString();
        String currentAppPackageName = Runner.getAppPackageName();
        return currentOpenApp.contains(currentAppPackageName);
    }

}
