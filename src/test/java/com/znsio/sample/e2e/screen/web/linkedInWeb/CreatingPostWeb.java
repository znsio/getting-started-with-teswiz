package com.znsio.sample.e2e.screen.web.linkedInWeb;

import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.linkedInScreen.CreatingPostScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Map;

public class CreatingPostWeb extends CreatingPostScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = CreatingPostWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static final By byUsername = By.cssSelector("input#session_key");
    private static final By byPassword = By.cssSelector("input#session_password");
    private static final By bySignInButton = By.className("sign-in-form__submit-button");
    private static final By byProfileName = By.cssSelector("div.t-16.t-black.t-bold");
    Map<String, String> testData = Runner.getTestDataAsMap(System.getProperty("user.name"));

    private static final By byClickOnStartAPost = By.xpath("//div[@class='display-flex align-items-center mt2 mr4 ml4']/button");
    private static final By byEnterText = By.cssSelector("div.ql-editor.ql-blank");
    private static final By byCheckText = By.cssSelector("div.ql-editor");

    private static final By byVideoButton = By.xpath("//div[@class='share-creation-state__detour-btn-container']/span[2]");
    private static final By byIsVideoPresent = By.cssSelector("a.app-aware-link.feed-shared-entity__content.display-flex");

    private static final By byDocumentButton = By.xpath("//div[@class='share-creation-state__detour-btn-container']/span[3]");
    private static final By byChooseFileButtonForDocument = By.cssSelector("span.artdeco-button.artdeco-button--secondary.artdeco-button--full.artdeco-button--2.document-cloud-upload__local-button");
    private static final By byDocumentTitle = By.xpath("//div[@class='document-title-form mh5 mv4']/input");
    private static final By byIsDocumentPresent = By.cssSelector("div.update-components-mini-update-v2.share-creaton-state__preview-container--as-box.feed-shared-update-v2--minimal-padding");
    private static final By byPollButton = By.xpath("//div[@class='share-creation-state__detour-btn-container']/span[6]");
    private static final By byYourQuestionInPoll = By.xpath("//textarea[@class='polls-detour__form-fields polls-detour__question-field artdeco-text-input--input artdeco-text-input__textarea']");
    private static final By byOption1InPoll = By.cssSelector("input#poll-option-1");
    private static final By byOption2InPoll = By.cssSelector("input#poll-option-2");
    private static final By byIsPollPresent = By.cssSelector("div.overflow-hidden.update-components-poll--preview.update-components-mini-update-v2__reshared-content");
    private static final By byDoneButtonForVideoDocumentAndPoll = By.cssSelector("button.ml2.artdeco-button.artdeco-button--2.artdeco-button--primary.ember-view");
    private static final By byViewPermissionButton = By.cssSelector("button.share-state-change-button__button.artdeco-button.artdeco-button--muted.artdeco-button--1.artdeco-button--secondary.mr1");
    private static final By byViewPermissionToConnectionsOnly = By.xpath("(//button[@class='share-generic-list__item-button t-14 t-bold t-black'])[3]");
    private static final By byViewPermissionToAnyone = By.xpath("(//button[@class='share-generic-list__item-button t-14 t-bold t-black'])[1]");
    private static final By bySaveButtonForViewAndCommentPermissionSelection = By.cssSelector("button.ml2.artdeco-button.artdeco-button--2.artdeco-button--primary.ember-view");

    private static final By byIsConnectionOnlyForViewSet = By.xpath("(//span[contains(text(),'Connections')])[1]");

    private static final By byConnectionOnlyForCommentButton =
            By.cssSelector("button.share-state-change-button__button.artdeco-button.artdeco-button--muted.artdeco-button--1.artdeco-button--tertiary.mr1");
    private static final By byChooseConnectionOnlyForCommentOption =
            By.xpath("(//button[@class='share-generic-list__item-button t-14 t-bold t-black'])[2]");
    private static final By byConnectionOnlyForCommentSet = By.xpath("(//span[@class='ph1'])[2]");
    public CreatingPostWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "creating post screen");
    }

    public String isLoginToLinkedInForUser1() {
        LOGGER.info("Enter username1");
        driver.findElement(byUsername).click();
        driver.findElement(byUsername).sendKeys("ishantaug75@gmail.com");
        LOGGER.info("Enter password1");
        driver.findElement(byPassword).click();
        driver.findElement(byPassword).sendKeys("Muks@123");
        LOGGER.info("Click on sign-in button");
        driver.findElement(bySignInButton).click();
        driver.waitTillElementIsPresent(byProfileName);
        LOGGER.info("Returning Sign-in profile username");
        return driver.findElement(byProfileName).getText();
    }

    public boolean isTextAndDocumentPresentBeforePosting() throws AWTException {
        Robot robot = new Robot();
        LOGGER.info("Click on Start a post button for creating post containing document and text");
        driver.findElement(byClickOnStartAPost).click();
        driver.waitForClickabilityOf(byDocumentButton);
        LOGGER.info("Select document button");
        driver.findElement(byDocumentButton).click();
        driver.waitForClickabilityOf(byChooseFileButtonForDocument);
        LOGGER.info("Select choose file button for file upload");
        driver.findElement(byChooseFileButtonForDocument).click();

        File file = new File("/Users/ishant.gupta/Downloads");
        StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        LOGGER.info("invoking robot class for document upload");
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_G);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        LOGGER.info("document successfully selected from system files");
        driver.waitForClickabilityOf(byDocumentTitle);
        driver.findElement(byDocumentTitle).click();
        LOGGER.info("enter document title");
        driver.findElement(byDocumentTitle).sendKeys("Sample Document 1 Title!!");
        driver.waitForClickabilityOf(byDoneButtonForVideoDocumentAndPoll);
        LOGGER.info("click on done button for document addition to post");
        driver.findElement(byDoneButtonForVideoDocumentAndPoll).click();
        driver.waitForClickabilityOf(byEnterText);
        driver.findElement(byEnterText).click();
        LOGGER.info("add text to post");
        driver.findElement(byEnterText).sendKeys("Sample Document 1 !!");
        driver.waitTillElementIsPresent(byIsDocumentPresent);
        LOGGER.info("verifying document and text both are added to post");
        if (driver.isElementPresent(byIsDocumentPresent) &&
                driver.findElement(byCheckText).getText().equalsIgnoreCase("Sample Document 1 !!")) {
            return true;
        } else
            return false;
    }

    public boolean isTextAndVideoPresentBeforePosting() throws AWTException {
        Robot robot = new Robot();
        LOGGER.info("Click on Start a post button for creating post containing video and text");
        driver.findElement(byClickOnStartAPost).click();
        LOGGER.info("Select video button");
        driver.waitForClickabilityOf(byVideoButton);
        driver.findElement(byVideoButton).click();
        File file = new File("/Users/ishant.gupta/Desktop");
        StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        LOGGER.info("invoking robot class for video upload");
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_G);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        LOGGER.info("video successfully selected from system files");
        driver.waitForClickabilityOf(byDoneButtonForVideoDocumentAndPoll);
        LOGGER.info("click on done button for video addition to post");
        driver.findElement(byDoneButtonForVideoDocumentAndPoll).click();
        driver.waitForClickabilityOf(byEnterText);
        driver.findElement(byEnterText).click();
        LOGGER.info("add text to post");
        driver.findElement(byEnterText).sendKeys("It is a video!!");
        driver.waitTillElementIsPresent(byIsVideoPresent);
        LOGGER.info("verifying video and text both are added to post");
        if (driver.isElementPresent(byIsVideoPresent) &&
                driver.findElement(byCheckText).getText().equalsIgnoreCase("It is a video!!"))
            return true;
        else return false;
    }

    public boolean isPollPresentBeforePosting() {
        LOGGER.info("Click on Start a post button for creating post containing poll");
        driver.findElement(byClickOnStartAPost).click();
        driver.waitForClickabilityOf(byPollButton);
        LOGGER.info("Select poll button");
        driver.findElement(byPollButton).click();
        LOGGER.info("enter question for poll");
        driver.findElement(byYourQuestionInPoll).sendKeys("Sample question");
        LOGGER.info("enter option 1 for poll");
        driver.findElement(byOption1InPoll).sendKeys("option1");
        LOGGER.info("enter option 2 for poll");
        driver.findElement(byOption2InPoll).sendKeys("option2");
        LOGGER.info("click on done button for poll addition to post");
        driver.findElement(byDoneButtonForVideoDocumentAndPoll).click();
        LOGGER.info("verifying poll is added to post");
        return driver.isElementPresent(byIsPollPresent);
    }

    public boolean isViewPermissionOnlyToUser1ConnectionsPresentBeforePosting() {
        LOGGER.info("Click on Start a post button for creating post where only user connections people can view the post");
        driver.findElement(byClickOnStartAPost).click();
        driver.waitForClickabilityOf(byViewPermissionButton);
        LOGGER.info("select view permission button");
        driver.findElement(byViewPermissionButton).click();
        driver.waitForClickabilityOf(byViewPermissionToConnectionsOnly);
        LOGGER.info("click on view permission to connections only option");
        driver.findElement(byViewPermissionToConnectionsOnly).click();
        driver.waitForClickabilityOf(bySaveButtonForViewAndCommentPermissionSelection);
        LOGGER.info("click on save button for view permission");
        driver.findElement(bySaveButtonForViewAndCommentPermissionSelection).click();
        driver.waitForClickabilityOf(byEnterText);
        driver.waitForClickabilityOf(byEnterText).click();
        LOGGER.info("enter text");
        driver.findElement(byEnterText).sendKeys("Sample Post 1!!");
        driver.waitTillElementIsPresent(byIsConnectionOnlyForViewSet);
        LOGGER.info("verifying the view permission and text in post");
        if (driver.isElementPresent(byIsConnectionOnlyForViewSet) &&
                driver.findElement(byCheckText).getText().equalsIgnoreCase("Sample Post 1!!"))
            return true;
        else
            return false;
    }

    public boolean isCommentPermissionOnlyToUser1ConnectionsPresentBeforePosting() {
        LOGGER.info("Click on Start a post button for creating post where only user connections people can comment on the post");
        driver.findElement(byClickOnStartAPost).click();
        driver.waitForClickabilityOf(byViewPermissionButton);
        LOGGER.info("Click on view permission button");
        driver.findElement(byViewPermissionButton).click();
        driver.waitForClickabilityOf(byViewPermissionToAnyone);
        LOGGER.info("Click on view permission to anyone option");
        driver.findElement(byViewPermissionToAnyone).click();
        driver.waitForClickabilityOf(bySaveButtonForViewAndCommentPermissionSelection);
        LOGGER.info("Click on save button after selecting the view permission");
        driver.findElement(bySaveButtonForViewAndCommentPermissionSelection).click();
        LOGGER.info("Click on comment permission button");
        driver.findElement(byConnectionOnlyForCommentButton).click();
        driver.waitForClickabilityOf(byChooseConnectionOnlyForCommentOption);
        LOGGER.info("Click on comment permission to connection only option");
        driver.findElement(byChooseConnectionOnlyForCommentOption).click();
        driver.waitForClickabilityOf(bySaveButtonForViewAndCommentPermissionSelection);
        LOGGER.info("Click on save button after selecting connection only option");
        driver.findElement(bySaveButtonForViewAndCommentPermissionSelection).click();
        driver.waitForClickabilityOf(byEnterText);
        driver.findElement(byEnterText).click();
        LOGGER.info("enter text");
        driver.findElement(byEnterText).sendKeys("Sample Post 2!!");
        LOGGER.info("verifying the comment permission and text in the post");
        if (driver.isElementPresent(byConnectionOnlyForCommentSet) &&
                driver.findElement(byCheckText).getText().equalsIgnoreCase("Sample Post 2!!"))
            return true;
        else
            return false;
    }
}

