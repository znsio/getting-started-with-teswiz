[![0.0.3](https://jitpack.io/v/znsio/teswiz.svg)](https://jitpack.io/#znsio/teswiz)
[![0.0.3](https://jitci.com/gh/znsio/teswiz/svg)](https://jitci.com/gh/znsio/teswiz)
[![CI](https://github.com/znsio/teswiz/actions/workflows/CI.yml/badge.svg)](https://github.com/znsio/teswiz/actions/workflows/CI.yml)
[![CodeQL](https://github.com/znsio/teswiz/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/znsio/teswiz/actions/workflows/codeql-analysis.yml)

# ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png) Breaking changes in Latest teswiz ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png)

Below is the list of the breaking changes, and the corresponding new implementation starting from teswiz latest teswiz.

## Package name changes

The package naming has been made consistent - **com.znsio.teswiz**.

Accordingly, the following changes will need to be made in your existing tests.

| Purpose                              | ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png) Old ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png) | ![#c5f015](https://placehold.co/15x15/c5f015/c5f015.png) New ![#c5f015](https://placehold.co/15x15/c5f015/c5f015.png) |
|:-------------------------------------|:----------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------|

## Method name changes

There are some method name changes as listed below:

| Purpose                                        | ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png) Old ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png) | ![#c5f015](https://placehold.co/15x15/c5f015/c5f015.png) New ![#c5f015](https://placehold.co/15x15/c5f015/c5f015.png) |
|:-----------------------------------------------|:----------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------|
| To put App in Background for number of Seconds |    putAppInBackground                                                                                                                   | putAppInBackgroundFor                                                                                                 |
|          Method Selects Device Notification from Notification Drawer                                        |              selectNotification()	                                                                                                         |                    selectNotificationFromNotificationDrawer()                                                                                                   |

## Minor Enhancement

1. setWebViewContext() - Updated use of AppiumDriver to SupportsContextSwitching for switching to Web View context.
2. setNativeAppContext() - Updated use of AppiumDriver to SupportsContextSwitching for switching to Native App
   context.
3. scroll(Point fromPoint, Point toPoint) - Updated use of AppiumDriver using PointerInput and Sequence to scroll using 2 points 
4. tapOnMiddleOfScreenOnDevice() - Updated use of AppiumDriver using PointerInput to tap in the middle of the screen
5. scrollDownByScreenSize() - Updated use of AppiumDriver to perform scroll as per the screen size


## Logging to ReportPortal

To make it easy to log to ReportPortal, the following new methods have been added:

```
        ReportPortalLogger.logDebugMessage("debugMessage");
        ReportPortalLogger.logInfoMessage("infoMessage");
        ReportPortalLogger.logWarningMessage("warningMessage");
        ReportPortalLogger.attachFileInReportPortal("message", new File("fileName"));
```

[//]: # (```mermaid)

[//]: # (flowchart TD)

[//]: # (  id1[allDrivers.createDriverFor&#40;...&#41;]--has changed to---id2&#40;[Drivers.createDriverFor&#40;...&#41;]&#41;)

[//]: # (  style id1 fill:#f9f)

[//]: # (  style id2 fill:#bbf)

[//]: # (```)

[//]: # ()

[//]: # (```mermaid)

[//]: # (flowchart LR)

[//]: # (  [Runner.platform]--is now changed to---id2&#40;Runner.getPlatform&#40;&#41;&#41;;)

[//]: # (  style id1 fill:#f9f)

[//]: # (  style id2 fill:#bbf)

[//]: # (```)

[//]: # (```mermaid)

[//]: # (flowchart LR;)

[//]: # (  [Runner.platform] -->|is now changed to| [Runner.getPlatform&#40;&#41;])

[//]: # (  style id1 fill:#f9f)

[//]: # (  style id2 fill:#bbf)

[//]: # (```)

# What is this repository about?

This repository implements automated tests for Android & iOS apps, specified using cucumber-jvm and intelligently run
them against

* Android
* iOS
* Windows Apps
* Web

Applitools (https://applitools.com/) Visual AI, and Applitools Ultrafast Grid (https://applitools.com/product-ultrafast-test-cloud/) is integrated with this framework, to provide
Visual AI testing as part of functional automation.

Reports will be uploaded to reportportal.io, that you would need to setup separately, and provide the server details in
src/test/resources/reportportal.properties file or provide the path to the file using this environment
variable: `REPORT_PORTAL_FILE`

Test can run on local browsers / devices, or against any cloud provider, such as HeadSpin, BrowserStack, SauceLabs, pCloudy.

## Tech stack used

* cucumber-jvm (https://cucumber.io)
* AppiumTestDistribution (https://github.com/AppiumTestDistribution/AppiumTestDistribution) -manages Android and iOS
  devices, and Appium
* Appium (https://appium.io)
* WebDriver (https://selenium.dev)
* reportportal.io (https://reportportal.io)
* Applitools (https://applitools.com)
* Build tool: gradle 7.3.3
* cucumber-reporting (https://github.com/damianszczepanik/cucumber-reporting)

## Pre Requisites
* JDK
  * **Minimum JDK version: 11**
  * **Set JAVA_HOME environment variable**
  * You can install JDK from here: https://adoptopenjdk.net/
* Setup the Android environment for test execution:
  * **Set ANDROID_HOME environment variable**
  * **Refer to this post for instructions how to automatically setup your environment - https://applitools.com/blog/automatic-appium-setup/**
  * Additional References:
    * Setup Android Command-line tools and SDK - https://developer.android.com/studio#command-tools
    * Install appium - https://appium.io
* Appium Desktop App is a great way to identify locators, and the recorder is quite helpful to quickly identify multiple
  locators for your tests - https://github.com/appium/appium-desktop/releases/tag/v1.20.2. You can also use Katalon
  Studio for locator identification (especially helpful for Windows platform)
* To verify appium installation is successful, run
  `appium-doctor` - it should not report any errors
* To install reportportal on local machine, refer to https://reportportal.io/installation. (Docker setup is the easiest way to proceed).


## [Getting started using teswiz](docs/GettingStartedUsingTeswiz-README.md)

## [Configuring the test execution](docs/ConfiguringTestExecution-README.md)

## [Running the sample tests](docs/SampleTests-README.md)

## Additional configurations

### [Running Visual Tests using Applitools Visual AI](docs/RunningVisualTests-README.md)

### [Functional/Feature Coverage](docs/FeatureCoverage-README.md)

### [Configuration parameters](docs/ConfigurationParameters-README.md)

## [Troubleshooting / FAQs](docs/FAQs-README.md)

### Contact [Anand Bagmar](https://twitter.com/BagmarAnand) for help or if you face issues using teswiz