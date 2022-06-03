package com.znsio.e2e.businessLayer.swiggynew;
import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.swiggynew.LandingScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class SearchLocationBL {


    private static final Logger LOGGER = Logger.getLogger(SearchLocationBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final SoftAssertions softly;
    private final Platform currentPlatform;

    public SearchLocationBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }


    public RestaurantsListBL searchForLocation(String deliveryLoc){
        LandingScreen.get().searchForDeliveryLocation(deliveryLoc);
        return new RestaurantsListBL();
    }


}
