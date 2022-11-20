package com.znsio.sample.e2e.screen.web.linkedInWeb;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.linkedInScreen.TimeLineScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TimeLineWeb extends TimeLineScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = TimeLineWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byUsername = By.cssSelector("input#session_key");
    private static final By byPassword = By.cssSelector("input#session_password");
    private static final By bySignInButton = By.className("sign-in-form__submit-button");
    private static final By byProfileName = By.cssSelector("div.t-16.t-black.t-bold");
    private static final By byIsTextForDocumentPresent = By.xpath("//span[contains(text(),'Sample Document 1 !!')]");
    private static final By byIsVideoTextPresent = By.xpath("//span[contains(text(),'It is a video!!')]");
    private static final By byIsPollPresent =
            By.cssSelector("div.update-components-poll-option__bar.update-components-poll-option__button.artdeco-button.artdeco-button--2.artdeco-button--secondary");
    private static final By byDeletePostDropDownButton =
            By.xpath("(//button[@class='feed-shared-control-menu__trigger artdeco-button artdeco-button--tertiary artdeco-button--muted artdeco-button--1 artdeco-button--circle artdeco-dropdown__trigger artdeco-dropdown__trigger--placement-bottom ember-view'])[1]");
    private static final By byDeletePostButton =
            By.cssSelector("button.feed-components-shared-decision-modal__confirm-button.artdeco-button.artdeco-button--primary.artdeco-button--2");
    private static final By bySearchUserName = By.cssSelector("input.search-global-typeahead__input.always-show-placeholder");
    private static final By byPostViewByUser2 = By.xpath("//span[contains(text(),'Sample Post 1!!')]");
    private static final By byUserNamePresent = By.xpath("//span[contains(text(),'Ishant Gupta')]");

    private static final By byClickOnPostOfUser1 = By.xpath("//span[contains(text(),'Sample Post 2!!')]");
    private static final By byClickOnViewFullProfile =
            By.cssSelector("a.app-aware-link.artdeco-button.artdeco-button--default.artdeco-button--2.artdeco-button--secondary");
    private static final By byClickOnShowAllActivity =
            By.cssSelector("a.optional-action-target-wrapper.artdeco-button.artdeco-button--tertiary.artdeco-button--3.artdeco-button--muted.inline-flex.justify-center.full-width.align-items-center.artdeco-button--fluid");
    private static final By byClickCommentButton =
        By.xpath("(//span[@class='comment feed-shared-social-action-bar__action-button'])[1]");
    private static final By byErrorMsgWhileCommenting = By.xpath("(//p[@class='t-12 t-black--light t-normal'])[1]");

    public TimeLineWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Timeline screen");
    }

    public String isLoginToLinkedInForUser2(){
        driver.findElement(byUsername).click();
        LOGGER.info("Enter username2");
        driver.findElement(byUsername).sendKeys("ishant20aug@gmail.com");
        driver.findElement(byPassword).click();
        LOGGER.info("Enter password2");
        driver.findElement(byPassword).sendKeys("20Aug1997#");
        LOGGER.info("Click on sign-in button");
        driver.findElement(bySignInButton).click();
        driver.waitTillElementIsPresent(byProfileName);
        LOGGER.info("Returning Sign-in profile username");
        return driver.findElement(byProfileName).getText();
    }
    public boolean isTextAndDocumentPresent() {
        driver.waitTillElementIsPresent(byIsTextForDocumentPresent);
        LOGGER.info("verifying the post containing text and document is present on the timeline screen");
        if (driver.isElementPresent(byIsTextForDocumentPresent))
            return true;
        else
            return false;
    }

    public boolean isTextAndVideoPresent() {
        visually.checkWindow(SCREEN_NAME, "Validating Post containing video and text");
        driver.waitTillElementIsPresent(byIsVideoTextPresent);
        LOGGER.info("verifying the post containing text and video is present on the timeline screen");
        if (driver.isElementPresent(byIsVideoTextPresent))
            return true;
        else
            return false;

    }

    public boolean isPollPresent() {
        driver.waitTillElementIsPresent(byIsPollPresent);
        LOGGER.info("verifying the post containing poll is present on the timeline screen");
        if (driver.isElementPresent(byIsPollPresent))
            return true;
        else
            return false;
    }

    public boolean isUser2AbleToSeeUser1Post() throws AWTException {
        driver.waitForClickabilityOf(bySearchUserName);
        LOGGER.info("click on the search box to find the user");
        driver.findElement(bySearchUserName).click();
        LOGGER.info("enter username in the search box");
        driver.findElement(bySearchUserName).sendKeys("Ishant Gupta ITC");
        Robot robot = new Robot();
        LOGGER.info("invoking robot class to press enter on the searched username in the search box");
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        LOGGER.info("successfully selected username in the search box");
        driver.waitTillElementIsPresent(byUserNamePresent);
        LOGGER.info("verifying the post created by user1 with view permission only, is present or not");
        if (driver.isElementPresent(byPostViewByUser2))
            return true;
        else
            return false;
    }

    public boolean isUser2AbleToCommentOnUser1Post() throws AWTException {
        driver.waitForClickabilityOf(bySearchUserName);
        LOGGER.info("click on the search box to find the user");
        driver.findElement(bySearchUserName).click();
        LOGGER.info("enter username in the search box");
        driver.findElement(bySearchUserName).sendKeys("Ishant Gupta ITC");
        Robot robot = new Robot();
        LOGGER.info("invoking robot class to press enter on the searched username in the search box");
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        LOGGER.info("successfully selected username in the search box");
        driver.waitTillElementIsPresent(byUserNamePresent);
        LOGGER.info("selecting the post from user1");
        if(driver.isElementPresent(byClickOnPostOfUser1)) {
            driver.findElement(byClickOnPostOfUser1).click();
        }
        else {
            driver.findElement(byClickOnViewFullProfile).click();
            driver.waitForClickabilityOf(byClickOnShowAllActivity);
            driver.findElement(byClickOnShowAllActivity).click();
        }
        driver.waitForClickabilityOf(byClickCommentButton);
        LOGGER.info("pressing the comment button");
        driver.findElement(byClickCommentButton).click();
        LOGGER.info("verifying the error msg as user2 is not allowed to comment");
        if (driver.isElementPresent(byErrorMsgWhileCommenting))
            return true;
        else
            return false;
    }

    public TimeLineScreen deletePost() throws AWTException {
        LOGGER.info("click on the dots for deleting the post");
        driver.waitForClickabilityOf(byDeletePostDropDownButton);
        visually.checkWindow(SCREEN_NAME, "Validating linkedin delete post");
        driver.findElement(byDeletePostDropDownButton).click();

        Robot robot = new Robot();
        LOGGER.info("invoking robot class for selecting the delete option from the drop down");
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyRelease(KeyEvent.VK_UP);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyRelease(KeyEvent.VK_UP);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyRelease(KeyEvent.VK_UP);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(500);
        driver.waitForClickabilityOf(byDeletePostButton);
        LOGGER.info("click on the delete option dialog box");
        driver.findElement(byDeletePostButton).click();
        LOGGER.info("successfully clicked on the delete option");
        return this;
    }
}
