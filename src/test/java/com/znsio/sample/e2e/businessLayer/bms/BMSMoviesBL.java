package com.znsio.sample.e2e.businessLayer.bms;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.bms.BMSHomeScreen;
import com.znsio.sample.e2e.screen.bms.BMSMoviesScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class BMSMoviesBL {

    private static final Logger LOGGER = Logger.getLogger(BMSHomeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public BMSMoviesBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public BMSMoviesBL selectMovie(String moviePosition) {
        BMSMoviesScreen.get().selectMovieAtPosition(moviePosition);
        return this;
    }

    public BMSMoviesBL selectDateAfter2Days() {

        BMSMoviesScreen.get().selectDateAfterTwoDays();
        return this;
    }

    public BMSMoviesBL selectSecondLastTimeSlotForCinemaHallAtPosition(String cinemaPosition) {

        BMSMoviesScreen.get().selectSecondLastTimeSlotForMovie(cinemaPosition);
        return this;
    }

    public BMSSelectSeatsBL selectNoOfSeats(String noOfSeats) {

        BMSMoviesScreen.get().selectNoOfSeatsToBook(noOfSeats);
        return new BMSSelectSeatsBL();
    }
}
