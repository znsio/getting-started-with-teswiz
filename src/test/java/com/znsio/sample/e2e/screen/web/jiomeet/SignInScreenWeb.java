package com.znsio.sample.e2e.screen.web.jiomeet;

import com.applitools.eyes.selenium.fluent.Target;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.jiomeet.InAMeetingScreen;
import com.znsio.sample.e2e.screen.jiomeet.LandingScreen;
import com.znsio.sample.e2e.screen.jiomeet.SignInScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class SignInScreenWeb
        extends SignInScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SignInScreenWeb.class.getSimpleName();
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final By byEnterMeetingId = By.id("meetingId");
    private final By byJoinMeetingButtonId = By.id("headerJoinMeetingButton");
    private final By byEnterPasswordId = By.id("pin");
    private final By byNameId = By.id("name");
    private final By byJoinMeetingButtonXpath = By.xpath("//button[contains(text(), 'Join')]");

    public SignInScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public LandingScreen signIn(String username, String password) {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public InAMeetingScreen joinAMeeting(String meetingId, String meetingPassword, String currentUserPersona) {
        WebElement joinMeetingElement = driver.waitTillElementIsPresent(byJoinMeetingButtonId);
        visually.checkWindow(SCREEN_NAME, "Landing screen");
        joinMeetingElement.click();

        WebElement enterMeetingIdElement = driver.waitTillElementIsPresent(byEnterMeetingId);
        enterMeetingIdElement.clear();
        enterMeetingIdElement.sendKeys(meetingId);

        WebElement enterPasswordElement = driver.waitTillElementIsPresent(byEnterPasswordId);
        enterPasswordElement.clear();
        enterPasswordElement.sendKeys(meetingPassword);

        WebElement enterNameElement = driver.waitTillElementIsPresent(byNameId);
        enterNameElement.clear();
        enterNameElement.sendKeys(currentUserPersona);

        visually.check(SCREEN_NAME, "After entering meeting details", Target.window()
                                                                            .strict()
                                                                            .layout(byEnterPasswordId)
                                                                            .layout(byNameId));

        visually.takeScreenshot(SCREEN_NAME, "Before clicking on Join button");
        ((JavascriptExecutor) driver.getInnerDriver()).executeScript("arguments[0].click()", driver.waitForClickabilityOf(byJoinMeetingButtonXpath));
        return this.waitForInAMeetingScreenToLoad();
    }

    private InAMeetingScreen waitForInAMeetingScreenToLoad() {
        InAMeetingScreen inAMeetingScreen = InAMeetingScreen.get();
        inAMeetingScreen.getMicLabelText();
        return inAMeetingScreen;
    }
}
