package com.znsio.sample.e2e.screen.android.jiomeet;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.jiomeet.LandingScreen;
import org.openqa.selenium.By;

public class LandingScreenAndroid
        extends LandingScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = LandingScreenAndroid.class.getSimpleName();
    private final By welcomeMessageId = By.id("com.jio.rilconferences:id/textUserName");

    public LandingScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String getSignedInWelcomeMessage() {
        return driver.waitTillElementIsPresent(welcomeMessageId)
                     .getText();
    }
}
