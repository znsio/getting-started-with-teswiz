# getting-started-with-teswiz
### This is a sample project to understand and start using [teswiz](https://github.com/znsio/teswiz)

## teswiz
[![](https://badges.frapsoft.com/os/v3/open-source.svg)](https://github.com/znsio/teswiz)
[![GitHub stars](https://img.shields.io/github/stars/znsio/teswiz.svg?style=flat)](https://github.com/znsio/teswiz/stargazers)
[ ![PRs Welcome](https://img.shields.io/badge/PRs-Welcome-brightgreen.svg?style=flat )](https://github.com/znsio/teswiz/pulls)
[![GitHub forks](https://img.shields.io/github/forks/znsio/teswiz.svg?style=social&label=Fork)](https://github.com/znsio/teswiz/network)

## Latest teswiz release status:
[![0.0.86](https://jitpack.io/v/znsio/teswiz.svg)](https://jitpack.io/#znsio/teswiz)
[![CI](https://github.com/znsio/teswiz/actions/workflows/Build_And_Run_Unit_Tests_CI.yml/badge.svg)](https://github.com/znsio/teswiz/actions/workflows/Build_And_Run_Unit_Tests_CI.yml)
[![CodeQL](https://github.com/znsio/teswiz/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/znsio/teswiz/actions/workflows/codeql-analysis.yml)

## Latest successful teswiz build id:
[![Latest Commit](https://img.shields.io/badge/commit-null-blue.svg)](https://jitpack.io/#znsio/teswiz)

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
* [Run the sample test](https://github.com/znsio/teswiz/blob/main/docs/SampleTests-README.md) 

## Additional information

# [Prerequisites](https://github.com/znsio/teswiz/blob/main/docs/GettingStartedUsingTeswiz-README.md)

# [Getting started using teswiz](https://github.com/znsio/teswiz/blob/main/docs/GettingStartedUsingTeswiz-README.md)

# [Running the sample tests](https://github.com/znsio/teswiz/blob/main/docs/SampleTests-README.md)

# [Visual Test Automation](https://github.com/znsio/teswiz/blob/main/docs/RunningVisualTests-README.md)

# [Configure your test execution](https://github.com/znsio/teswiz/blob/main/docs/ConfiguringTestExecution-README.md)

# [Configuration options](https://github.com/znsio/teswiz/blob/main/docs/ConfigurationParameters-README.md)
Test execution using teswiz is highly configurable. This enables you to control what type of tests you want to execute, and where (environment, local/cloud), etc, without making changes to your code.

# ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png) Breaking changes in teswiz v0.0.81 ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png)
Refer to the [breaking changes](https://github.com/znsio/teswiz/blob/main/docs/BreakingChanges-README.md) section in [teswiz](https://github.com/znsio/teswiz) repo

# [Feature/Functional coverage](https://github.com/znsio/teswiz/blob/main/docs/FeatureCoverage-README.md) from your test execution

# Using teswiz for your automation?

Simply download the repo as a zip file, or clone it, and run the command:

    ./gradlew clean run

