package com.znsio.sample.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.zomato.ZomatoHomePageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ZomatoHomePageScreenWeb extends ZomatoHomePageScreen {

    public static final By byZomatoHeader = By.xpath("//div[@class='contents-wrapper']");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = AmazonCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public ZomatoHomePageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public boolean isHomepageVisible() {
        String homePageUrl = driver.getInnerDriver().getCurrentUrl();
        boolean isZomatoHomepageVisble = driver.findElement(byZomatoHeader).isDisplayed();
        if (isZomatoHomepageVisble) {
            LOGGER.info("Zomato Homepage is visible:-" + homePageUrl);
        } else {
            LOGGER.error("Zomato Homepage is not visible:-" + homePageUrl);
        }
        return false;
    }

    @Override
    public boolean selectLocationFromDropDown(String location) {

        return false;
    }
}
