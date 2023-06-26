package com.znsio.sample.e2e.screen.web.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.screen.bookMyShow.BookMyShowLoginPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.SelectedDateScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.openqa.selenium.By;

import java.util.logging.Logger;

public class SelectedDateScreenWeb extends SelectedDateScreen {
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = BookMyShowLoginPageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By selectDated = By.xpath("//li[@class='date-details  slick-slide'][2]");

    public SelectedDateScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Movie Landing Page Screen");
    }

    @Override
    public SelectedDateScreen selectExpectedDate() {
        driver.waitForClickabilityOf(selectDated).click();
        LOGGER.info("Selected Today's Date + 2");
        return SelectedDateScreen.get();
    }
}
