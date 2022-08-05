package com.znsio.sample.e2e.screen.web.jiomeet;

import com.epam.reportportal.service.ReportPortal;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.jiomeet.InAMeetingScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Date;

public class InAMeetingScreenWeb
        extends InAMeetingScreen {
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = InAMeetingScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final By byMeetingInfoIconXpath = By.xpath("//div[@class='icon pointer']");
    private final By byMicLabelXpath = By.xpath("//div[contains(@class,'mic-section')]");

    public InAMeetingScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
    }

    @Override
    public boolean isMeetingStarted() {
        LOGGER.info("Has meeting started? " + getMicLabelText());
        return true;
    }

    @Override
    public String getMeetingId() {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public String getMeetingPassword() {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public InAMeetingScreen unmute() {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public InAMeetingScreen mute() {
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public String getMicLabelText() {
        LOGGER.info("getMicLabelText");
        enableInMeetingControls("getMicLabelText");
        String micLabelText = driver.waitTillElementIsPresent(byMicLabelXpath)
                                    .getText()
                                    .trim();
        visually.takeScreenshot(SCREEN_NAME, "in a meeting after micLabel text");
        LOGGER.info("getMicLabelText: mic label text : " + micLabelText);
        return micLabelText;
    }

    private void enableInMeetingControls(String calledFrom) {
        try {
            LOGGER.info(String.format("enableInMeetingControls: Called from: '%s'%n", calledFrom));
            Actions actions = new Actions(innerDriver);
            actions.moveToElement(driver.waitForClickabilityOf(byMeetingInfoIconXpath))
                   .moveByOffset(25, 25)
                   .perform();
        } catch(Exception e) {
            String logMessage = String.format("Exception occurred : enableInMeetingControls%nException: %s", e.getLocalizedMessage());
            LOGGER.info(logMessage);
            ReportPortal.emitLog(logMessage, "DEBUG", new Date());
        }
    }
}
