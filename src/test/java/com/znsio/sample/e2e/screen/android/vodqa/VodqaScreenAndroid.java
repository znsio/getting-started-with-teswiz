package com.znsio.sample.e2e.screen.android.vodqa;

import com.znsio.sample.e2e.screen.vodqa.NativeViewScreen;
import com.znsio.sample.e2e.screen.vodqa.VodqaScreen;
import com.znsio.sample.e2e.screen.vodqa.WebViewScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import io.appium.java_client.AppiumBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class VodqaScreenAndroid extends VodqaScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = VodqaScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(VodqaScreenAndroid.class.getName());

    private final By byLoginButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='login']/android.widget.Button");
    private final By byWebViewSectionOptionXpath = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='webView']");
    private final By byNativeViewSectionXpath = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='chainedView']");

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
    public WebViewScreen enterIntoNewsWebViewSection() {
        LOGGER.info("Enter into news web view section");
        visually.checkWindow(SCREEN_NAME, "Sample List Screen");
        driver.waitTillElementIsVisible(byWebViewSectionOptionXpath).click();
        return WebViewScreen.get();
    }

    @Override
    public NativeViewScreen enterIntoNativeViewSection() {
        LOGGER.info("Enter into native view section");
        visually.checkWindow(SCREEN_NAME, "Sample List Screen");
        driver.waitTillElementIsVisible(byNativeViewSectionXpath).click();
        return NativeViewScreen.get();
    }
}
