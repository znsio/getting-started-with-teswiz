package com.znsio.sample.e2e.screen.web.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.bookMyShow.BOOKMYSHOW_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.bookMyShow.BookMyShowLoginPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.MovieLandingPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.MoviesListPageScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;

public class MoviesListPageScreenWeb extends MoviesListPageScreen {

    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = BookMyShowLoginPageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byListOfMoviesXpath = By.xpath("//a[@class='sc-133848s-11 sc-1ljcxl3-1 eQiiBj']");
    private String secondMovieTitle;

    public MoviesListPageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Now Showing Page Screen");
    }

    @Override
    public List<WebElement> getNowShowingMoviewList() {
        List<WebElement> moviesList = driver.findElements(byListOfMoviesXpath);
        context.addTestState(BOOKMYSHOW_SAMPLE_TEST_CONTEXT.MOVIES_LIST, moviesList);
        return moviesList;
    }

    @Override
    public String getSecondMovieTittle(List<WebElement> moviesList) {
        secondMovieTitle = moviesList.get(2).getText();
        context.addTestState(BOOKMYSHOW_SAMPLE_TEST_CONTEXT.SECOND_MOVIE_TITLE, secondMovieTitle);
        return secondMovieTitle;
    }

    @Override
    public MovieLandingPageScreen userSelectsSecondMovie(List<WebElement> moviesList, int moviePosition) {
        moviesList.get(moviePosition).click();
        LOGGER.info("Movie selected at position: " + moviePosition);
        visually.takeScreenshot(SCREEN_NAME, "Movie Selected" );
        return MovieLandingPageScreen.get();
    }
}
