# getting-started-with-teswiz
Sample project to get started with teswiz - https://github.com/znsio/teswiz

Simply download the repo as a zip file, or clone it, and run the command:

    ./gradlew clean run

# Getting started

Refer to prerequisites mentioned here https://github.com/znsio/teswiz#prerequisites

# Running the sample tests

## Android tests
    CONFIG=./configs/jiomeet_config.properties TAG="@jiomeet and @single-user" PLATFORM=android ./gradlew run 

## Web tests
    CONFIG=./configs/jiomeet_config.properties TAG="@jiomeet and @single-user" PLATFORM=web ./gradlew run 

## Multiuser Android-web tests
    CONFIG=./configs/jiomeet_config.properties TAG="@jiomeet and @multiuser-android-web" ./gradlew run

## Multiuser Android tests
    CONFIG=./configs/jiomeet_config.properties TAG="@jiomeet and @multiuser-android and @single-app" ./gradlew run

## Multiuser-Multiapp Android tests
    CONFIG=./configs/jiomeet_config.properties TAG="@jiomeet and @multiuser-android and @multi-app" ./gradlew run

## Multiuser-Multiapp Android-web tests
    CONFIG=./configs/jiomeet_config.properties TAG="@jiomeet and @multiuser-android-web and @multi-app" ./gradlew run