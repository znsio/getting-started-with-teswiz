package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.businessLayer.ajio.AjioSearchBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import org.apache.log4j.Logger;

public class AjioSteps {
    private static final Logger LOGGER = Logger.getLogger(AjioSteps.class.getName());
    private final TestExecutionContext context;

    public AjioSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                                                               .getId());
        LOGGER.info("context: " + context.getTestName());
    }


    @Given("I, a guest user, search for {string} products")
    public void iAGuestUserSearchForProducts(String product) {
        LOGGER.info(System.out.printf("iAGuestUserSearchForProducts - Persona:'%s', Platform: '%s'", SAMPLE_TEST_CONTEXT.ME, Runner.getPlatform()));
        Drivers.createDriverFor(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.getPlatform(), context);
        new AjioSearchBL(SAMPLE_TEST_CONTEXT.GUEST_USER, Runner.getPlatform()).searchFor(product);
    }
}
