package com.znsio.sample.e2e.screen.web.linkedInWeb;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.linkedInScreen.PostSubmitScreen;
import com.znsio.sample.e2e.screen.linkedInScreen.TimeLineScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class PostSubmitWeb extends PostSubmitScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = PostSubmitWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byPostButton = By.cssSelector("button.share-actions__primary-action.artdeco-button.artdeco-button--2.artdeco-button--primary.ember-view");

    public PostSubmitWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "post screen");
    }

    public TimeLineScreen clickPostButton() {
        driver.waitForClickabilityOf(byPostButton);
        LOGGER.info("Clicking on Post button");
        driver.findElement(byPostButton).click();
        return TimeLineScreen.get();
    }
}
