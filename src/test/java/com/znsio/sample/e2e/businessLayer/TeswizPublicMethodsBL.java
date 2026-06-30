package com.znsio.sample.e2e.businessLayer;

import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.appium.AppiumCheckSettings;
import com.applitools.eyes.selenium.fluent.SeleniumCheckSettings;
import com.znsio.teswiz.context.SessionContext;
import com.znsio.teswiz.context.TestExecutionContext;
import com.znsio.teswiz.entities.Direction;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Api;
import com.znsio.teswiz.runner.AppiumDeviceManager;
import com.znsio.teswiz.runner.AppiumServerManager;
import com.znsio.teswiz.runner.ConfigFileManager;
import com.znsio.teswiz.runner.CustomCapabilities;
import com.znsio.teswiz.runner.DesiredCapabilityBuilder;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.PluginCli;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import com.znsio.teswiz.services.UnirestService;
import com.znsio.teswiz.tools.*;
import com.znsio.teswiz.tools.cmd.AsyncCommandLineExecutor;
import com.znsio.teswiz.tools.cmd.CommandLineExecutor;
import com.znsio.teswiz.tools.cmd.CommandLineResponse;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TeswizPublicMethodsBL {
    private static final Logger LOGGER = LogManager.getLogger(TeswizPublicMethodsBL.class.getName());
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

//    @Test
    private void teswizPublicMethodsCheck() {
        long threadId = Thread.currentThread().getId();

        System.setProperty("teswiz.contract.string", "42");
        System.setProperty("teswiz.contract.int", "42");
        System.setProperty("teswiz.contract.boolean", "true");

        String osName = OsUtils.getOsName();
        String userDirectory = OsUtils.getUserDirectory();
        String userName = OsUtils.getUserName();
        boolean isWindows = OsUtils.isWindows();
        boolean isMac = OsUtils.isMac();
        boolean isLinux = OsUtils.isLinux();

        String notSet = Runner.NOT_SET;
        String aDefault = Runner.DEFAULT;
        String debug = Runner.DEBUG;
        String info = Runner.INFO;
        String warn = Runner.WARN;

        SensitiveDataMasker.setShowSensitiveData(false);
        SensitiveDataMasker.maskSecret("secret");
        SensitiveDataMasker.mask("https://user:password@example.com");

        OverriddenVariable.getOverriddenStringValue("teswiz.contract.string");
        OverriddenVariable.getOverriddenStringValue("teswiz.contract.missing", "default");
        OverriddenVariable.getOverriddenIntValue("teswiz.contract.int");
        OverriddenVariable.getOverriddenIntValue("teswiz.contract.int", 7);
        OverriddenVariable.getOverriddenBooleanValue("teswiz.contract.boolean");
        OverriddenVariable.getOverriddenBooleanValue("teswiz.contract.boolean", false);

        String prettyPrinted = JsonPrettyPrinter.prettyPrint("{\"hello\":\"teswiz\"}");
        String scenarioName = StringUtils.normaliseScenarioName("Teswiz Public Methods");

        FileUtils.createDirectory("build/teswiz-contract");
        FileUtils.createDirectoryIn("build", "teswiz-contract-child");
        FileUtils.copyFile(new File("build.gradle"), new File("build/teswiz-contract/build.gradle.copy"));

        ScreenShotManager screenShotManager = new ScreenShotManager();
        screenShotManager.takeScreenShot(null, "teswiz-public-methods");

        CommandLineResponse commandLineResponse = new CommandLineResponse();
        commandLineResponse.setCommand("echo teswiz");
        commandLineResponse.setExitCode(0);
        commandLineResponse.setStdOut("ok");
        commandLineResponse.setErrOut("");
        commandLineResponse.setTimedOut(false);
        commandLineResponse.setDurationMillis(1);
        commandLineResponse.getCommand();
        commandLineResponse.getExitCode();
        commandLineResponse.getStdOut();
        commandLineResponse.getErrOut();
        commandLineResponse.isTimedOut();
        commandLineResponse.getDurationMillis();
        commandLineResponse.toString();

        CommandLineExecutor.execCommand(new String[]{"echo", "teswiz"});
        CommandLineExecutor.execCommand(new String[]{"echo", "teswiz"}, 5);
        CommandLineExecutor.execCommand(Arrays.asList("echo", "teswiz"), 5);
        CommandLineExecutor.isCommandAvailable("echo");
        CommandLineExecutor.toolWorks("echo");

        AsyncCommandLineExecutor asyncCommandLineExecutor = new AsyncCommandLineExecutor();
        asyncCommandLineExecutor.sendCommand("echo teswiz", 1);
        asyncCommandLineExecutor.close();
        new AsyncCommandLineExecutor.CommandResult("output").getOutput();

        String configFile = ConfigFileManager.CAPS.get();
        CustomCapabilities customCapabilities = CustomCapabilities.getInstance();
        customCapabilities.getCapabilities();
        customCapabilities.getCapabilityObjectFromKey("android");
        customCapabilities.createInstance(configFile);

        new DesiredCapabilityBuilder().buildDesiredCapability(configFile, 0);

        AppiumDeviceManager.getAppiumDevice();
        AppiumServerManager appiumServerManager = new AppiumServerManager();
        appiumServerManager.getRemoteWDHubIP();
        appiumServerManager.startAppiumServer("127.0.0.1");
        AppiumServerManager.destroyAppiumNode();

        PluginCli pluginCli = PluginCli.getInstance();
        pluginCli.getPlugin();
        pluginCli.getPlatFormName();
        pluginCli.isCloudExecution();
        pluginCli.getCloudName();
        if (pluginCli.getPlugin() != null && pluginCli.getPlugin().getDeviceFarm() != null) {
            pluginCli.getPlugin().getDeviceFarm().getPlatform();
            pluginCli.getPlugin().getDeviceFarm().getCloud();
        }

        SessionContext.getTestExecutionContext(threadId);
        SessionContext.remove(threadId);
        SessionContext.getReportPortalLaunchURL();
        SessionContext.setReportPortalLaunchURL();

        Api.getResponse("http://localhost");

        new Runner();
        new Runner("configFilePath", "stepDefDirName", "featuresDirName");
        Runner.getPlatformForUser("me");
        Runner.getHostName();
        Runner.getPlatform();
        Runner.getApplitoolsConfiguration();
        Runner.getCloudName();
        Runner.getCloudUser();
        Runner.getCloudKey();
        Runner.getRemoteDriverGridPort();
        Runner.getRemoteDriverGridHostName();
        Runner.getMaxNumberOfAppiumDrivers();
        Runner.getMaxNumberOfWebDrivers();
        Runner.isVisualTestingEnabled();
        Runner.shouldFailTestOnVisualDifference();
        Runner.getFromEnvironmentConfiguration("BASE_URL");
        Runner.getTestData("USERNAME");
        Runner.getTestDataAsMap("ENV");
        Runner.main(new String[]{});
        Runner.getTestExecutionContext(1);
        Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(currentUserPersona, currentPlatform, context);
        Runner.fetchPlatform(threadId);
        Runner.getTargetEnvironment();
        Runner.getBaseURLForWeb();
        Runner.getAppPackageName();
        Runner.isRunningInCI();
        Runner.isCLI();
        Runner.isPDF();
        Runner.isAPI();
        Runner.getBrowser();
        Runner.getProxyURL();
        Runner.getBrowserConfigFileContents();
        Runner.getBrowserConfigFileContents("non-default-browserConfig.json");
        Runner.getBrowserConfigFile();

        Drivers.setDriverFor(currentUserPersona, currentPlatform, context);
        Drivers.isDriverAssignedForUser(currentUserPersona);
        Drivers.createDriverFor(currentUserPersona, currentPlatform, context);
        Drivers.createDriverFor(currentUserPersona, "appName", "browserName", currentPlatform,
                                context);
        Drivers.createDriverFor(currentUserPersona, "appNane", currentPlatform, context);
        Drivers.getDriverForUser(currentUserPersona);
        Drivers.assignNewPersonaToExistingDriver(currentUserPersona, "newPersona", context);
        Drivers.getDriverForCurrentUser(threadId);
        Drivers.getVisualDriverForCurrentUser(threadId);
        Drivers.getNameOfDeviceUsedByUser(currentUserPersona);
        Drivers.getAvailableUserPersonas();
        Drivers.createPDFDriverFor(currentUserPersona, currentPlatform, context, "sample.pdf");
        Scenario scenario = null;
        Drivers.attachLogsAndCloseAllDrivers(scenario);

        Driver driver = new Driver(currentUserPersona, currentPlatform, context, "sample.pdf");
        driver.waitForClickabilityOf("element-id");
        driver.waitForClickabilityOf("element-id", 1);
        driver.findElementByAccessibilityId("element-id");
        driver.waitForAlert();
        driver.waitForAlert(1);
        driver.findElement((By) null);
        driver.hideKeyboard();
        driver.findElements((By) null);
        driver.findElementById("element-id");
        driver.findElementByXpath("//element");
        driver.scroll(null, null);
        driver.scrollToAnElementByText("text");
        driver.scrollToAnElementByText("text", 1);
        driver.isElementPresent((By) null);
        driver.isElementPresentByAccessibilityId("element-id");
        driver.isElementPresentWithin((WebElement) null, (By) null);
        driver.scrollDownByScreenSize();
        driver.scrollVertically(10, 20, 30);
        driver.tapOnMiddleOfScreen();
        driver.swipeRight();
        driver.swipeLeft();
        driver.swipeByPassingPercentageAttributes(10, 20, 30);
        driver.openNotifications();
        driver.selectNotificationFromNotificationDrawer((By) null);
        driver.putAppInBackgroundFor(1);
        driver.bringAppInForeground();
        driver.goToDeepLinkUrl("scheme://path", "package.name");
        driver.getInnerDriver();
        driver.printAndSavePageSourceDump();
        driver.getType();
        driver.getVisual();
        driver.longPress((By) null);
        driver.longPress((By) null, 1);
        driver.pushFileToDevice("build.gradle", "/tmp/build.gradle");
        driver.allowPermission((By) null);
        driver.waitForClickabilityOf((By) null);
        driver.waitForClickabilityOf((By) null, 1);
        driver.findElementsByAccessibilityId("element-id");
        driver.waitTillElementIsPresent((By) null);
        driver.waitTillElementIsVisible((By) null);
        driver.waitTillElementIsPresent((By) null, 1);
        driver.waitTillElementIsVisible((By) null, 1);
        driver.waitTillVisibilityOfAllElements((By) null);
        driver.waitTillVisibilityOfAllElements((By) null, 1);
        driver.waitTillElementIsVisible("element-id");
        driver.waitTillElementIsVisible("element-id", 1);
        driver.waitTillPresenceOfAllElements((By) null);
        driver.waitTillPresenceOfAllElements((By) null, 1);
        driver.setWindowSize(800, 600);
        driver.moveToElement((By) null);
        driver.isDriverRunningInHeadlessMode();
        driver.setWebViewContext();
        driver.setNativeAppContext();
        driver.setNativeAppContext("NATIVE_APP");
        driver.switchFrameToDefault();
        driver.switchToFrame("frame-id");
        driver.scrollToBottom();
        driver.scrollTillElementIntoView((By) null);
        driver.scrollTillElementIntoView((WebElement) null);
        driver.switchToNextTab();
        driver.switchToParentTab();
        driver.uploadFileInBrowser("build.gradle", (By) null);
        driver.injectMediaToBrowserstackDevice("https://example.com/media");
        driver.injectMediaUrlToBrowserstackDevice("https://example.com/media");
        driver.isMobilePlatform();
        driver.scrollInDynamicLayer(Direction.UP, (WebElement) null);
        driver.setAttributeValue((WebElement) null, "attribute", "value");
        driver.dragAndDrop((By) null, (By) null);
        driver.doubleTap((WebElement) null);
        driver.flick();
        driver.horizontalSwipeWithGesture((WebElement) null, Direction.LEFT);
        driver.pinchAndZoomIn((WebElement) null);
        driver.pinchAndZoomOut((WebElement) null);
        driver.multiTouchOnElements((WebElement) null, (WebElement) null);
        driver.relaunchApp();
        driver.waitTillElementIsInvisible((By) null);
        driver.waitTillElementIsInvisible((By) null, 1);
        driver.isElementDisplayed((By) null);
        driver.isElementDisplayed((WebElement) null);
        driver.getClipboardText();
        driver.setClipboardText("clipboard");
        driver.click((By) null);
        driver.click((By) null, 1);
        driver.switchToWebViewContextSafely();
        driver.switchToNativeContextSafely();
        driver.clickAndWaitForElement((By) null, (By) null);
        driver.clickAndWaitForElement((By) null, (By) null, 1, 1);
        driver.clickWithFallbackAndWaitForDisappearance((By) null, (By) null, (By) null, 1);

        Visual visual = new Visual(Driver.PDF_DRIVER, currentPlatform, "teswizPublicMethodsCheck",
                                   currentUserPersona, "sample.pdf");
        visual.validatePdf();
        visual.validatePdf(new int[]{1});
        visual.validatePdf("sample.pdf");
        visual.validatePdf("sample.pdf", new int[]{1});
        visual.checkWindow("screen", "tag");
        visual.check("screen", "tag", (SeleniumCheckSettings) null);
        visual.check("screen", "tag", (AppiumCheckSettings) null);
        visual.checkWindow("screen", "tag", MatchLevel.LAYOUT);
        visual.handleTestResults(currentUserPersona, Driver.PDF_DRIVER);
        visual.takeScreenshot("screen", "tag");
        Visual.closeBatch();
        Visual.addMobileCapabilitiesToTestContextForVisualTesting(new DesiredCapabilities());

        new UnirestService();
        ReportPortalLogger.logDebugMessage("logDebugMessage");
        ReportPortalLogger.logInfoMessage("logInfoMessage");
        ReportPortalLogger.logWarningMessage("logWarningMessage");
        ReportPortalLogger.attachFileInReportPortal("attachFileInReportPortal", new File("build.gradle"));

        UnirestService.getHttpResponse("");
        UnirestService.getHttpResponseWithQueryParameter("", "", "");
        UnirestService.getHttpResponseWithQueryMap("", new HashMap<>());
        UnirestService.postHttpRequest("", "");
        UnirestService.patchHttpRequest("", "");
        UnirestService.deleteHttpRequest("");

        new DateTime();
        DateTime.getFormattedMeetingTime(5);

        new IPAddress();
        IPAddress.getPublicIPAddress();
        IPAddress.getPrivateIPAddress();

        JsonFile.saveJsonToFile(new HashMap<>(), "");
        JsonFile.getNodeValueAsMapFromJsonFile("", "");
        JsonFile.loadJsonFile("");
        String [] a = new String[0];
        JsonFile.getNodeValueAsStringFromJsonFile("", a);
        JsonFile.getValueFromLoadedJsonMap("", a, new HashMap<>());
        JsonFile.getNodeValueAsArrayListFromJsonFile("", "");
        JsonFile.convertToMap("");
        JsonFile.convertToArray("");
        JsonFile.compareFiles("", "");

        new JsonSchemaValidator();
        JsonSchemaValidator.validateJsonFileAgainstSchema("", "", "");

        Randomizer.randomize(5);
        Randomizer.randomize("5");
        Randomizer.randomizeAlphaNumericString(5);
        Randomizer.randomizeString(5);
        Randomizer.getRandomNumberBetween(1, 2);
        Randomizer.getRandomNumberBetween(1L, 2L);

        new Wait();
        Wait.waitFor(1);

        HeartBeat heartBeat = new HeartBeat("me", "teswiz", 1);
        heartBeat.getHeartBeatCounter();
        heartBeat.stopHeartBeat();

        YamlFile.compareFiles("", "");
    }
}
