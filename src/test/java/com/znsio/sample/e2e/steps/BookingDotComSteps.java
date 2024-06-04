package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.businessLayer.bookingDotCom.BookingDotComBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class BookingDotComSteps {
    private static final Logger LOGGER = LogManager.getLogger(BookingDotComSteps.class.getName());
    private final TestExecutionContext context;

    public BookingDotComSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
    }

    @Given("I want to book a flight ticket")
    public void iWantToBookAFlightTicket() {
        LOGGER.info(System.out.printf("iSearchForATicketFromToForPassenger - Persona:'%s'", SAMPLE_TEST_CONTEXT.ME));
        Drivers.createDriverFor(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform(), context);
        new BookingDotComBL(SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()).bookAFlightTicket();
    }

    @Given("I search for a {string} ticket to {string}")
    public void iSearchForATicketFromToForPassenger(String journeyType, String destination) {
        LOGGER.info(System.out.printf("iSearchForATicketFromToForPassenger - Persona:'%s'", SAMPLE_TEST_CONTEXT.ME));
        new BookingDotComBL().searchForTicket(journeyType, destination);
    }
}
