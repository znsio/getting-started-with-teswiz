package com.znsio.sample.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.tools.ReportPortalLogger;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.io.File;

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

        new Runner();
        new Runner("configFilePath", "stepDefDirName", "featuresDirName");
        Runner.getPlatformForUser("me");
        Runner.shouldFailTestOnVisualDifference();
        Runner.getTestExecutionContext(1);

        Runner.getPlatform();
        Runner.getApplitoolsConfiguration();
        Runner.getCloudKey();
        Runner.getCloudName();
        Runner.getCloudUser();

        Runner.getRemoteDriverGridPort();
        Runner.getMaxNumberOfAppiumDrivers();
        Runner.getMaxNumberOfWebDrivers();
        Runner.isVisualTestingEnabled();
        Runner.getFromEnvironmentConfiguration("BASE_URL");

        Runner.getTestData("USERNAME");
        Runner.getTestDataAsMap("ENV");
        Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(currentUserPersona, currentPlatform, context);
        Runner.fetchPlatform(threadId);

        Runner.getTargetEnvironment();
        Runner.getBaseURLForWeb();
        Runner.getAppPackageName();
        Runner.isRunningInCI();
        Runner.getBrowser();

        Runner.getProxyURL();
        Runner.getBrowserConfigFileContents();
        Runner.getBrowserConfigFile();
        Runner.getRemoteDriverGridHostName();
        Runner.getHostName();

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
        Drivers.attachLogsAndCloseAllDrivers();

        ReportPortalLogger.logDebugMessage("logDebugMessage");
        ReportPortalLogger.logInfoMessage("logInfoMessage");
        ReportPortalLogger.logWarningMessage("logWarningMessage");
        ReportPortalLogger.attachFileInReportPortal("attachFileInReportPortal", new File("build.gradle"));
    }
}
