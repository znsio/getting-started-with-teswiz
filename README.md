# getting-started-with-teswiz

### This is a sample project to understand and start using [teswiz](https://github.com/anandbagmar/teswiz)

## teswiz
[![](https://badges.frapsoft.com/os/v3/open-source.svg)](https://github.com/anandbagmar/teswiz)
[![GitHub stars](https://img.shields.io/github/stars/anandbagmar/teswiz.svg?style=flat)](https://github.com/anandbagmar/teswiz/stargazers)
[ ![PRs Welcome](https://img.shields.io/badge/PRs-Welcome-brightgreen.svg?style=flat )](https://github.com/anandbagmar/teswiz/pulls)
[![GitHub forks](https://img.shields.io/github/forks/anandbagmar/teswiz.svg?style=social&label=Fork)](https://github.com/anandbagmar/teswiz/network)

## Latest teswiz release status:
[![0.0.86](https://jitpack.io/v/anandbagmar/teswiz.svg)](https://jitpack.io/#anandbagmar/teswiz)
[![CI](https://github.com/anandbagmar/teswiz/actions/workflows/Build_And_Run_Unit_Tests_CI.yml/badge.svg)](https://github.com/anandbagmar/teswiz/actions/workflows/Build_And_Run_Unit_Tests_CI.yml)
[![CodeQL](https://github.com/anandbagmar/teswiz/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/anandbagmar/teswiz/actions/workflows/codeql-analysis.yml)

## Latest successful teswiz build id:
[![Latest Commit](https://img.shields.io/badge/commit-f9aa68b661-blue.svg)](https://jitpack.io/#anandbagmar/teswiz)


## 🚨 Breaking Changes

### From Version `1.0.13` onward

As part of package restructuring, context-related classes have moved to a new package.

#### ❗ Required Update in Imports

Replace:

```java
import com.context.SessionContext;
import com.context.TestExecutionContext;
```

With:

```java
import com.znsio.teswiz.context.SessionContext;
import com.znsio.teswiz.context.TestExecutionContext;
```

# NOTE

    Use JDK v17 or higher

## Step to start using tewiz in your project:

* Clone/download the getting-started-with-teswiz repo
* For the android apk, find the package and activity
  aapt dump badging src/test/resources/sampleApps/<apkname>.apk | grep package
  aapt dump badging src/test/resources/sampleApps/<apkname>.apk | grep activity
* Create a copy of an existing configs file - ex: ./configs/myapp.properties
  Update `APP_PACKAGE_NAME` and `APP_NAME`, `BASE_URL`
* Create a copy of an existing capabilities file - ex: ./caps/myapp_capabilities.json
  * Update `android->app->local`, `android->appActivity` and `android->appPackage`
* Update `src\test\resources\reportportal.properties` file
* [Run the sample test](https://github.com/anandbagmar/teswiz/blob/main/docs/guides/SampleTests-README.md) 

## Additional information

# [Prerequisites](https://github.com/anandbagmar/teswiz/blob/main/docs/guides/Prerequisites-README.md)

# [Getting started using teswiz](https://github.com/anandbagmar/teswiz/blob/main/docs/guides/GettingStartedUsingTeswiz-README.md)

# [Running the sample tests](https://github.com/anandbagmar/teswiz/blob/main/docs/guides/SampleTests-README.md)

# [Setting up the Hard Gate](https://github.com/anandbagmar/teswiz/blob/main/docs/features/HardGate.md)

# [Visual Test Automation](https://github.com/anandbagmar/teswiz/blob/main/docs/features/RunningVisualTests-README.md)

# [Configure your test execution](https://github.com/anandbagmar/teswiz/blob/main/docs/guides/ConfiguringTestExecution-README.md)

# [Configuration options](https://github.com/anandbagmar/teswiz/blob/main/docs/features/ConfigurationParameters-README.md)
Test execution using teswiz is highly configurable. This enables you to control what type of tests you want to execute, and where (environment, local/cloud), etc, without making changes to your code.

# ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png) Breaking changes in teswiz v0.0.81 ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png)
Refer to the [breaking changes](https://github.com/anandbagmar/teswiz/blob/main/docs/internals/BreakingChanges-README.md) section in [teswiz](https://github.com/anandbagmar/teswiz) repo

# [Feature/Functional coverage](https://github.com/anandbagmar/teswiz/blob/main/docs/internals/FeatureCoverage-README.md) from your test execution

# Using teswiz for your automation?

Simply download the repo as a zip file, or clone it, and run the command:

    ./gradlew clean run

