package com.znsio.sample.e2e.businessLayer.Yatra;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.Yatra.HomeScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeBL {
    private static final Logger LOGGER = Logger.getLogger(HomeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public HomeBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public HomeBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public HomeBL addFirstTrip(String tripType, String sourceCity, String destinationCity) {
        LOGGER.info(String.format("addFirstTrip: Select '%s' trip with source city '%s' and destination city '%s' ", tripType, sourceCity, destinationCity));
        HomeScreen homeScreen = HomeScreen.get().closeAdvertisement()
                .selectTripType(tripType)
                .selectSourceCity(sourceCity, "1")
                .selectDestinationCity(destinationCity, "1");
        assertThat(homeScreen.getTitle()).as("").isNotNull();
//        softly.assertThat(yatraHomeScreen.getSourceCity("1"))
//                .as("First source city found to be different").isEqualToIgnoringCase(sourceCity);
//        softly.assertThat(yatraHomeScreen.getDestinationCity("1"))
//                .as("First destination city found to be different").isEqualToIgnoringCase(destinationCity);
        return this;
    }

    public HomeBL addSecondTrip(String sourceCity, String destinationCity) {
        LOGGER.info(String.format("addSecondTrip: Select source city '%s' and destination city '%s' for second trip", sourceCity, destinationCity));
        HomeScreen homeScreen = HomeScreen.get()
                .selectSourceCity(sourceCity, "2")
                .selectDestinationCity(destinationCity, "2").selectTravelDate();
//        softly.assertThat(yatraHomeScreen.getSourceCity("2"))
//                .as("First source city found to be different").isEqualToIgnoringCase(sourceCity);
//        softly.assertThat(yatraHomeScreen.getDestinationCity("2"))
//                .as("First destination city found to be different").isEqualToIgnoringCase(destinationCity);
        return this;
    }

    public HomeBL addPassenger(int adultCount, int childrenCount, int infantCount) {
        LOGGER.info(String.format("addPassenger: Add passenger with adult count: '%d', children count: '%d' and infant count: '%d'", adultCount, childrenCount, infantCount));
        HomeScreen homeScreen = HomeScreen.get().selectTravellerOption()
                .addAdults(adultCount).addChildren(childrenCount).addInfants(infantCount);
        assertThat(adultCount + childrenCount + infantCount).as("").isEqualTo(homeScreen.getTravelCount());
        return this;
    }

    public ResultBL selectCategory(String flightClass, String flightType) {
        LOGGER.info(String.format("selectCategory: select '%s' class and '%s' flight type", flightClass, flightType));
        HomeScreen homeScreen = HomeScreen.get().selectFlightClass(flightClass).selectNonStopFlight().selectSearchFight();

        return new ResultBL();
    }
}
