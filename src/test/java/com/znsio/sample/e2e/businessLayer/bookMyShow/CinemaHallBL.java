package com.znsio.sample.e2e.businessLayer.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.bookMyShow.BOOKMYSHOW_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.bookMyShow.CinemaHallScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.assertj.core.api.SoftAssertions;

public class CinemaHallBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public CinemaHallBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = BOOKMYSHOW_SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public CinemaHallBL userSelectsSecondSlot(String cinemaHallTitle) {
        CinemaHallScreen.get().selectSecondSlot(cinemaHallTitle);
        return new CinemaHallBL();
    }

    public SeatSelectionBL userSelectsNumberOfPeople(int numberOfSeats) {
        CinemaHallScreen.get().selectNumberOfPeople(numberOfSeats);
        return new SeatSelectionBL();
    }
}
