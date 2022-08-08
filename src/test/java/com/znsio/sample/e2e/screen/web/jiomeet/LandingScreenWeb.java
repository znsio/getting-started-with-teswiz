package com.znsio.sample.e2e.screen.web.jiomeet;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.android.jiomeet.LandingScreenAndroid;
import com.znsio.sample.e2e.screen.jiomeet.InAMeetingScreen;
import com.znsio.sample.e2e.screen.jiomeet.LandingScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class LandingScreenWeb
        extends LandingScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = LandingScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byHeadingXpath = By.xpath("//h3[contains(@class,'heading')]");
    private final By byWelcomeTextDescriptionXpath = By.xpath("//p[@class='desc']");
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    public LandingScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String getSignedInWelcomeMessage() {
        visually.checkWindow(SCREEN_NAME, "get signedin welcome message");
        String welcomeText = driver.waitTillElementIsPresent(byHeadingXpath)
                                   .getText();
        welcomeText += " " + driver.waitTillElementIsPresent(byWelcomeTextDescriptionXpath)
                                   .getText();
        return welcomeText;
    }

    @Override
    public InAMeetingScreen startInstantMeeting() {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public LandingScreen waitTillWelcomeMessageIsSeen() {
        driver.waitTillElementIsPresent(byHeadingXpath);
        visually.checkWindow(SCREEN_NAME, "signedin successfully");
        return this;
    }
}
