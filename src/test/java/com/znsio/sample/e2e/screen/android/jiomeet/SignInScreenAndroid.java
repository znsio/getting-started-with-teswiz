package com.znsio.sample.e2e.screen.android.jiomeet;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.jiomeet.InAMeetingScreen;
import com.znsio.sample.e2e.screen.jiomeet.LandingScreen;
import com.znsio.sample.e2e.screen.jiomeet.SignInScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.znsio.e2e.tools.Wait.waitFor;

public class SignInScreenAndroid
        extends SignInScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = SignInScreenAndroid.class.getSimpleName();
    private final By signInId = By.id("com.jio.rilconferences:id/signIn");
    private final By userNameId = By.id("com.jio.rilconferences:id/edit_mobile_number");
    private final By proceedButtonId = By.id("com.jio.rilconferences:id/btn_otp_next");
    private final By passwordId = By.id("com.jio.rilconferences:id/edt_otp_number");
    private final By welcomeMessageId = By.id("com.jio.rilconferences:id/textUserName");
    private final By joinMeetingId = By.id("com.jio.rilconferences:id/joinMeetingBtn");
    private final By enterMeetingId = By.id("com.jio.rilconferences:id/inputMeetingLink");
    private final By enterMeetingPasswordId = By.id("com.jio.rilconferences:id/inputMeetingPassword");
    private final By enterDisplayNameId = By.id("com.jio.rilconferences:id/inputUserName");
    private final By enterMeeting = By.id("com.jio.rilconferences:id/buttonNext");
    private final By joinMeeting = By.id("com.jio.rilconferences:id/buttonJoinMeeting");

    public SignInScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public LandingScreen signIn(String username, String password) {
        startSignInProcess().enterUsernameAndProceed(username)
                            .enterPasswordAndSignIn(password);

        driver.waitTillElementIsPresent(welcomeMessageId);
        return LandingScreen.get();
    }

    @Override
    public InAMeetingScreen joinAMeeting(String meetingId, String meetingPassword, String currentUserPersona) {
        joinAMeeting().enterMeetingDetails(meetingId, meetingPassword, currentUserPersona);
        return InAMeetingScreen.get();
    }

    private SignInScreenAndroid joinAMeeting() {
        driver.waitTillElementIsPresent(joinMeetingId)
              .click();
        return this;
    }

    private SignInScreenAndroid enterMeetingDetails(String meetingId, String meetingPassword, String currentUserPersona) {
        driver.waitTillElementIsPresent(enterMeetingId)
              .sendKeys(meetingId);
        driver.waitTillElementIsPresent(enterMeetingPasswordId)
              .sendKeys(meetingPassword);
        driver.waitTillElementIsPresent(enterDisplayNameId)
              .sendKeys(currentUserPersona);
        driver.waitTillElementIsPresent(enterMeeting)
              .click();
        driver.waitTillElementIsPresent(joinMeeting)
              .click();
        waitFor(8);
        return this;
    }

    private SignInScreenAndroid enterPasswordAndSignIn(String password) {
        WebElement passwordElement = driver.waitTillElementIsPresent(passwordId);
        passwordElement.click();
        passwordElement.clear();
        passwordElement.sendKeys(password);
        visually.checkWindow(SCREEN_NAME, "Password entered");
        driver.waitForClickabilityOf(proceedButtonId)
              .click();
        return this;
    }

    private SignInScreenAndroid enterUsernameAndProceed(String username) {
        WebElement usernameElement = driver.waitTillElementIsPresent(userNameId);
        usernameElement.click();
        usernameElement.clear();
        usernameElement.sendKeys(username);
        visually.checkWindow(SCREEN_NAME, "Username entered");
        driver.waitForClickabilityOf(proceedButtonId)
              .click();
        return this;
    }

    private SignInScreenAndroid startSignInProcess() {
        visually.checkWindow(SCREEN_NAME, "Start Sign In process");
        driver.waitTillElementIsPresent(signInId)
              .click();
        return this;
    }
}
