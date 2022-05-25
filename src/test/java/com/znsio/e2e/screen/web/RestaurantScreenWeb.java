package com.znsio.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.RestaurantScreen;
import com.znsio.e2e.screen.SwiggyHomeScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class RestaurantScreenWeb extends RestaurantScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SwiggyHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By locationTextbox = By.xpath("//input[@id=\'location\']");
    private final By fistSuggestion = By.id("//div[@class='_1oLDb']//span[starts-with(.,'Mumbai')]");
    private final TestExecutionContext context;
    public RestaurantScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Home screen for Swiggy");
    }

    @Override
    public RestaurantScreen addItem() {
        return this;
    }

    @Override
    public RestaurantScreen removeItem() {
        return this;
    }
}
