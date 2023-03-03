package com.znsio.sample.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class TeswizPublicMethodsBL {
    private static final Logger LOGGER = Logger.getLogger(TeswizPublicMethodsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public TeswizPublicMethodsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public TeswizPublicMethodsBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.getPlatform();
    }

    private void teswizPublicMethodsCheck() {
        long threadId = Thread.currentThread().getId();
        Runner.getPlatform();
        Runner.getApplitoolsConfiguration();
        Runner.getCloudKey();
        Runner.getCloudName();
        Runner.getCloudUser();
        Runner.getRemoteDriverGridPort();
        Runner.getMaxNumberOfAppiumDrivers();
        Runner.getMaxNumberOfWebDrivers();
        Runner.isVisualTestingEnabled();
        Runner.remove(threadId);
        Runner.getFromEnvironmentConfiguration("BASE_URL");
        Runner.getTestData("USERNAME");
        Runner.getTestDataAsMap("ENV");
        Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(currentUserPersona, currentPlatform, context);
        Runner.fetchPlatform(threadId);
        Runner.closeAllDrivers();
        Runner.getTargetEnvironment();
        Runner.getBaseURLForWeb();
        Runner.getAppPackageName();
        Runner.isRunningInCI();
        Runner.getBrowser();
        Runner.getProxyURL();
        Runner.getWebDriverManagerProxyURL();
        Runner.getBrowserConfigFileContents();
        Runner.getBrowserConfigFile();

        Drivers.setDriverFor(currentUserPersona, currentPlatform, context);
        Drivers.createDriverFor(currentUserPersona, currentPlatform, context);
        Drivers.createDriverFor(currentUserPersona, "appName", "browserName", currentPlatform,
                                context);
        Drivers.createDriverFor(currentUserPersona, "appNane", currentPlatform, context);
        Drivers.getDriverForUser(currentUserPersona);
        Drivers.assignNewPersonaToExistingDriver(currentUserPersona, "newPersona", context);
        Drivers.getDriverForCurrentUser(threadId);
        Drivers.getVisualDriverForCurrentUser(threadId);
        Drivers.getNameOfDeviceUsedByUser(currentUserPersona);
        Drivers.isDriverAssignedForUser(currentUserPersona);
        Drivers.getAvailableUserPersonas();
    }
}
