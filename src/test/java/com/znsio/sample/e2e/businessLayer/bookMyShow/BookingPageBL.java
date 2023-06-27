package com.znsio.sample.e2e.businessLayer.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.bookMyShow.BOOKMYSHOW_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.bookMyShow.BookingPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.SelectedDateScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.assertj.core.api.SoftAssertions;

public class BookingPageBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public BookingPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = BOOKMYSHOW_SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public SelectedMovieBL selectDateAfterTomorrow(String secondMovieTitle) {
        boolean onCorrectBookingPage = BookingPageScreen.get().bookingForSelectedMovie(secondMovieTitle);
        softly.assertThat(onCorrectBookingPage).as(String.format("Mismatch in Movie Details on Movie Landing Page")).isTrue();
        SelectedDateScreen.get().selectExpectedDate();
        return new SelectedMovieBL();
    }
}
