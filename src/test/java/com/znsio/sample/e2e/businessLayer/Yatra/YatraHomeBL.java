package com.znsio.sample.e2e.businessLayer.Yatra;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.Yatra.YatraHomeScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class YatraHomeBL {
    private static final Logger LOGGER = Logger.getLogger(YatraHomeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public YatraHomeBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public YatraHomeBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public YatraHomeBL addFirstTrip(String tripType, String sourceCity, String destinationCity) {
        LOGGER.info(String.format("addFirstTrip: Select '%s' trip with source city '%s' and destination city '%s' ", tripType, sourceCity, destinationCity));
        YatraHomeScreen yatraHomeScreen = YatraHomeScreen.get()
                .selectTripType(tripType)
                .selectSourceCity(sourceCity)
                .selectDestinationCity(destinationCity);
        return this;
    }

    public YatraHomeBL addSecondTrip(String sourceCity, String destinationCity) {
        LOGGER.info(String.format("addSecondTrip: Select '%s' trip with source city '%s' and destination city '%s' ", tripType, sourceCity, destinationCity));
        YatraHomeScreen yatraHomeScreen = YatraHomeScreen.get()
                .selectSecondSourceCity(sourceCity)
                .selectSecondDestinationCity(destinationCity).selectTravelDate();
        return this;
    }
}
