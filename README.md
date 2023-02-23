# getting-started-with-teswiz

# Breaking changes in teswiz v0.0.72
Refer to the [breaking changes](https://github.com/znsio/teswiz/blob/cleanUp/README.md#breaking-changes-in-v0072) section in [teswiz](https://github.com/znsio/teswiz) repo

### This is a sample project to understand and start using [teswiz](https://github.com/znsio/teswiz)

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

## Additional information

# [Prerequisites](https://github.com/znsio/teswiz/blob/main/docs/GettingStartedUsingTeswiz-README.md)

# [Running the sample tests](https://github.com/znsio/teswiz/blob/main/docs/SampleTests-README.md)

# [Visual Test Automation](https://github.com/znsio/teswiz/blob/main/docs/RunningVisualTests-README.md)

# [Configure your test execution](https://github.com/znsio/teswiz/blob/main/docs/ConfiguringTestExecution-README.md)

# [Configuration options](https://github.com/znsio/teswiz/blob/main/docs/ConfigurationParameters-README.md)
Test execution using teswiz is highly configurable. This enables you to control what type of tests you want to execute, and where (environment, local/cloud), etc, without making changes to your code. 

# Know the [Feature/Functional coverage](https://github.com/znsio/teswiz/blob/main/docs/FeatureCoverage-README.md) from your test execution

# Using teswiz for your automation?

Simply download the repo as a zip file, or clone it, and run the command:

    ./gradlew clean run

