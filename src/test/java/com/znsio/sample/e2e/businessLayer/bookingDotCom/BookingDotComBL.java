package com.znsio.sample.e2e.businessLayer.bookingDotCom;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.indigo.FlightResultsBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.bookingDotCom.BookingDotComFlightsScreen;
import com.znsio.sample.e2e.screen.bookingDotCom.BookingDotComHomeScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class BookingDotComBL {
    private static final Logger LOGGER = LogManager.getLogger(BookingDotComBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public BookingDotComBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public BookingDotComBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public FlightResultsBL searchForTicket(String journeyType, String destination) {
        BookingDotComFlightsScreen.get()
                .selectJourneyType(journeyType)
                .selectTo(destination)
                .searchFlightOptions();
        return new FlightResultsBL();
    }

    public BookingDotComBL bookAFlightTicket() {
        BookingDotComHomeScreen.get()
                .dismissPopup()
                .goToFlights();
        return this;
    }
}
