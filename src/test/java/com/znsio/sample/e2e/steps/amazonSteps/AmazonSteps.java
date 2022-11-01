package com.znsio.sample.e2e.steps.amazonSteps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.steps.CalculatorSteps;
import org.apache.log4j.Logger;

public class AmazonSteps {

    private static final Logger LOGGER = Logger.getLogger(CalculatorSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    Given("I sign in as a registered {string} on Amazon")
    public void iSignInAsARegisteredOnAmazon(String username, String password) {



    }
}
