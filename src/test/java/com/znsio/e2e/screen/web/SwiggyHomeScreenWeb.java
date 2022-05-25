package com.znsio.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.SwiggyHomeScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.znsio.e2e.tools.Wait.waitFor;

public class SwiggyHomeScreenWeb extends SwiggyHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SwiggyHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By locationTextbox = By.xpath("//input[@id=\'location\']");
    private final String fistSuggestion = "//div/span[2][contains(text(),'%s')]";
    private final TestExecutionContext context;

    public SwiggyHomeScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Home screen for Swiggy");
    }

    @Override
    public SwiggyHomeScreen setRestaurantLocation(String location) {
        driver.waitForClickabilityOf(locationTextbox).click();
//        driver.findElement(locationTextbox).clear();
        driver.findElement(locationTextbox).sendKeys(location);
        String selectLocation= String.format(fistSuggestion,location);
//        waitFor(3);
        driver.waitForClickabilityOf(By.xpath(selectLocation)).click();
        return this;
    }
}
