package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import java.util.Map;

public class LinkedinCreatePostSteps {
    private static final Logger LOGGER = Logger.getLogger(LinkedinCreatePostSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;
    private String userSuffix="User";

    public LinkedinCreatePostSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread()
                .getId());
        LOGGER.info("context: " + context.getTestName());

        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);

        LOGGER.info("allDrivers: " + (null == allDrivers));
    }


}
