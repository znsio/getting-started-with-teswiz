package com.znsio.sample.e2e.businessLayer.Yatra;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.Yatra.HomeScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

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
                .selectTripType(tripType).selectFirstSourceCity(sourceCity)
                .selectFirstDestinationCity(destinationCity);
        assertThat(homeScreen.getTitle()).as("Unable to see yatra home title").isNotNull();
        Map cityCode = Runner.getTestDataAsMap(SAMPLE_TEST_CONTEXT.CITIES_CODE);
        softly.assertThat(homeScreen.getFirstSourceCity())
                .as("First source city found to be different").isEqualToIgnoringCase(String.valueOf(cityCode.get(sourceCity)));
        softly.assertThat(homeScreen.getFirstDestinationCity())
                .as("First destination city found to be different").isEqualToIgnoringCase(String.valueOf(cityCode.get(destinationCity)));
        return this;
    }

    public HomeBL addSecondTrip(String sourceCity, String destinationCity) {
        LOGGER.info(String.format("addSecondTrip: Select source city '%s' and destination city '%s' for second trip", sourceCity, destinationCity));
        HomeScreen homeScreen = HomeScreen.get().selectSecondSourceCity(sourceCity)
                .selectSecondDestinationCity(destinationCity)
                .selectTravelDate();
        Map cityCode = Runner.getTestDataAsMap(SAMPLE_TEST_CONTEXT.CITIES_CODE);
        softly.assertThat(homeScreen.getSecondSourceCity())
                .as("Second source city found to be different").isEqualToIgnoringCase(String.valueOf(cityCode.get(sourceCity)));
        softly.assertThat(homeScreen.getSecondDestinationCity())
                .as("Second destination city found to be different").isEqualToIgnoringCase(String.valueOf(cityCode.get(destinationCity)));
        return this;
    }


    public HomeBL addPassengers(int adultCount, int childrenCount, int infantCount) {
        LOGGER.info(String.format("addPassenger: Add passenger with adult count: '%d', children count: '%d' and infant count: '%d'", adultCount, childrenCount, infantCount));
        HomeScreen homeScreen = HomeScreen.get().selectTravellerOption()
                .addAdults(adultCount).addChildren(childrenCount).addInfants(infantCount);
        assertThat(adultCount + childrenCount + infantCount).as("The count of passenger is different than selected").isEqualTo(homeScreen.getTravelCount());
        return this;
    }

    public ResultBL selectCategory(String flightClass, String flightType) {
        LOGGER.info(String.format("selectCategory: select '%s' class and '%s' flight type", flightClass, flightType));
        HomeScreen.get().selectClass(flightClass).selectNonStop().selectSearch();
        return new ResultBL();
    }
}
