package com.znsio.sample.e2e.screen.linkedInScreen;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.web.linkedInWeb.CreatingPostWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.awt.*;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class  CreatingPostScreen {
    private static final String SCREEN_NAME = CreatingPostScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static CreatingPostScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch (platform) {
            case web:
                return new CreatingPostWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract String isLoginToLinkedInForUser1();
    public abstract boolean isTextAndDocumentPresentBeforePosting() throws AWTException;
    public abstract boolean isTextAndVideoPresentBeforePosting() throws AWTException;
    public abstract boolean isPollPresentBeforePosting();
    public abstract boolean isViewPermissionOnlyToUser1ConnectionsPresentBeforePosting();
    public abstract boolean isCommentPermissionOnlyToUser1ConnectionsPresentBeforePosting();
}
