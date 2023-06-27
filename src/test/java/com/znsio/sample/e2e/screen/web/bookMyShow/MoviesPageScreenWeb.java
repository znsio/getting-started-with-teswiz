package com.znsio.sample.e2e.screen.web.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.screen.bookMyShow.BookMyShowLoginPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.BookingPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.MoviesPageScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.openqa.selenium.By;

import java.util.logging.Logger;

public class MoviesPageScreenWeb extends MoviesPageScreen {
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = BookMyShowLoginPageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By bookTicketButton = By.xpath("//div[@class='styles__CtaText-sc-1vmod7e-2 bBLrVT']//span");
    private static final By screen = By.xpath("//div[@class='sc-vhz3gb-3 dRokFO']//*[text()='4DX']");

    public MoviesPageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Movie Page Home Screen");
    }

    @Override
    public BookingPageScreen userSelectsScreen() {
        driver.waitForClickabilityOf(bookTicketButton).click();
        driver.waitForClickabilityOf(screen).click();
        visually.takeScreenshot(SCREEN_NAME, "Selected Screen Type");
        LOGGER.info("Screen Type selected");
        return BookingPageScreen.get();
    }
}
