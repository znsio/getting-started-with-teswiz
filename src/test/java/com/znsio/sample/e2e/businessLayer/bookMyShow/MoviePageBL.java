package com.znsio.sample.e2e.businessLayer.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.bookMyShow.BOOKMYSHOW_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.bookMyShow.MovieLandingPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.MoviesListPageScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MoviePageBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public MoviePageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = BOOKMYSHOW_SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public MovieLandingPageBL userOpenSecondMovie(int moviePosition) {
        List<WebElement> movieslist = MoviesListPageScreen.get().getNowShowingMoviewList();
        String secondMovieTitle = MoviesListPageScreen.get().getSecondMovieTittle(movieslist);
        context.addTestState(BOOKMYSHOW_SAMPLE_TEST_CONTEXT.SECOND_MOVIE_TITLE, secondMovieTitle);
        MoviesListPageScreen.get().userSelectsSecondMovie(movieslist, moviePosition);
        boolean isMovieTitleCorrect = MovieLandingPageScreen.get().isMovieTitleCorrect(secondMovieTitle);
        softly.assertThat(isMovieTitleCorrect).as(String.format("Mismatch in Movie Details on Movie Landing Page")).isTrue();
        return new MovieLandingPageBL();
    }
}
