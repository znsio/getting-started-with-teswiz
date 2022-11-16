package com.znsio.sample.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.zomato.ZomatoResturantPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class ZomatoResturantPageScreenWeb extends ZomatoResturantPageScreen {

    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = ZomatoResturantPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    Map<String,String> testData = Runner.getTestDataAsMap(System.getProperty("user.name"));
    String resturant = testData.get("resturant");

    public ZomatoResturantPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }


    @Override
    public boolean validateResturant() {
        String getResturantUrl = driver.getInnerDriver().getCurrentUrl();
        visually.checkWindow(SCREEN_NAME, "On Zomato Resturant Pages");
        if (getResturantUrl.contains(resturant)) {
            LOGGER.info("Successfully opened resturant page" +resturant);
            return true;
        } else {
            LOGGER.error("Expected resturant to be"+resturant+ "but getting"+getResturantUrl);
        }
    }
}
