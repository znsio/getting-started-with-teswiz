package com.znsio.sample.e2e.screen.web.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.screen.bookMyShow.BookMyShowLoginPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.MovieLandingPageScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.openqa.selenium.By;

import java.util.logging.Logger;

public class MoviesLandingPageScreenWeb extends MovieLandingPageScreen {
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = BookMyShowLoginPageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By movieTitle = By.xpath("//h1[@class='sc-qswwm9-6 hxbOUw']");
    private String movieTitleOnMovieLandingPage;

    public MoviesLandingPageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Selected Movie Landing Page Screen");
    }

    @Override
    public boolean isMovieTitleCorrect(String secondMovieTitle) {
        movieTitleOnMovieLandingPage = driver.findElement(movieTitle).getText().trim();
        LOGGER.info(String.format("Movie Title on Movie Landing page: ", movieTitleOnMovieLandingPage));
        System.out.println(movieTitleOnMovieLandingPage);
        if (secondMovieTitle.trim().equals(movieTitleOnMovieLandingPage)) {
            return true;
        } else {
            return false;
        }
    }
}
