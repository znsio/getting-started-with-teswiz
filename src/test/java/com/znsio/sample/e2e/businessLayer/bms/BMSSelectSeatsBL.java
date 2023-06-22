package com.znsio.sample.e2e.businessLayer.bms;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.bms.BMSSelectSeatsScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

public class BMSSelectSeatsBL {

    private static final Logger LOGGER = Logger.getLogger(BMSHomeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public BMSSelectSeatsBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    public BMSSelectSeatsBL validateAvailableSeatsPercentage(String validationPercentage) {

        int totalAvailableSeats = BMSSelectSeatsScreen.get().getTotalAvailableSeats();
        int totalSeats = BMSSelectSeatsScreen.get().getTotalSeats();

        double validatePercent = Double.parseDouble(validationPercentage);
        LOGGER.info("Vacant seats should be greater than " + validatePercent + " %");
        double actualPercentageOfVacantSeats = (totalAvailableSeats/totalSeats)*100;
        LOGGER.info("Actual vacant seats are " + actualPercentageOfVacantSeats + " %");

        softly.assertThat(actualPercentageOfVacantSeats).as("Actual vacant seat percentage is lower than Validation percentage").isGreaterThan(validatePercent);
        //Assert.assertTrue("No of vacant seats is less than " + validatePercent + " %", actualPercentageOfVacantSeats>validatePercent);

        return this;
    }
}
