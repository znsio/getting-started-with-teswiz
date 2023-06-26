package com.znsio.sample.e2e.screen.web.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.screen.bookMyShow.BookMyShowLoginPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.BookingPageScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.openqa.selenium.By;

import java.util.logging.Logger;

public class BookingPageScreenWeb extends BookingPageScreen {
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = BookMyShowLoginPageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By movieTitle = By.xpath("//div[@class='cinema-name-wrapper']/@herf");

    public BookingPageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Booking Page Screen");
    }

    @Override
    public boolean bookingForSelectedMovie(String secondMovieTitle) {
        String bookingForMovie = driver.findElement(movieTitle).getText();
        LOGGER.info(String.format("Booking For Movie: ", bookingForMovie));
        if (bookingForMovie.contains(secondMovieTitle))
        {
            return true;
        }else {
            return false;
        }
    }
}
